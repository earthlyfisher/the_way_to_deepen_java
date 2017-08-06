package com.earthlyfish.spring;

import com.earthlyfish.spring.bean.StuController;
import com.earthlyfish.spring.stereotype.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringAnnoTestEntry {

    static final String SCAN_PATH = "com.earthlyfish.spring.bean";

    static final Map<String, Bean> BEAN_MAP = new ConcurrentHashMap<String, Bean>();

    private static String replacePath() {
        return SCAN_PATH.replace(".", File.separator);
    }

    /**
     * 获取main class的路径，必须main线程组的成员.
     *
     * @return
     */
    private static String getMainClass() {
        Thread thread = Thread.currentThread();
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        String mainClsPath = stes[stes.length - 1].getClassName();
        if (!thread.getName().equals("main")) {
            mainClsPath = mainClsPath.substring(0, mainClsPath.indexOf("$"));
        }
        return mainClsPath;
    }

    /**
     * 获取项目根目录.
     *
     * @return
     */
    private static String getProjectRootDir() {
        // method 1.
        String projectRootDir = "";
        String mainClass = getMainClass();
        try {
            projectRootDir = Class.forName(mainClass).getResource("/").getPath();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // method 2.
        // projectRootDir=ClassLoader.getSystemClassLoader().getResource("").getPath();
        return projectRootDir;
    }

    /**
     * 获取某个目录下的所有文件.
     *
     * @param rootPath
     * @param absolutePath
     * @return
     */
    private static List<String> getFilesUnderDir(String rootPath, String absolutePath) {
        List<String> urlLst = new ArrayList<String>();
        File file = new File(absolutePath);

        if (!file.exists()) {
            return urlLst;
        }

        if (file.isFile()) {
            String fileName = file.getName();
            urlLst.add(SCAN_PATH + "." + rootPath + fileName.substring(0, fileName.lastIndexOf(".")));
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (!file.getAbsolutePath().endsWith(replacePath())) {
                rootPath = rootPath + file.getName() + ".";
            }

            for (File tmpFile : files) {
                urlLst.addAll(getFilesUnderDir(rootPath, tmpFile.getAbsolutePath()));
            }
        }
        return urlLst;
    }

    /**
     * 分析类注解.
     *
     * @param cls
     * @param bean
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void checkTypeAnnotations(Class cls, Bean bean) {

        if (cls.isAnnotationPresent(Controller.class)) {
            setBeanInfo(cls, setCamelClsName(cls.getName().substring(cls.getName().lastIndexOf(".") + 1), 0), bean);
        }

        if (cls.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
            requestMapping.value();
        }

        if (cls.isAnnotationPresent(Service.class)) {
            Service service = (Service) cls.getAnnotation(Service.class);
            String beanName = service.value();
            if (beanName.trim().equals("")) {
                beanName = setCamelClsName(cls.getName().substring(cls.getName().lastIndexOf(".") + 1), 0);
            }
            setBeanInfo(cls, beanName, bean);
        }

        if (cls.isAnnotationPresent(Component.class)) {
            Component component = (Component) cls.getAnnotation(Component.class);
            String beanName = component.value();
            if (beanName.trim().equals("")) {
                beanName = setCamelClsName(cls.getName().substring(cls.getName().lastIndexOf(".") + 1), 0);
            }
            setBeanInfo(cls, beanName, bean);
        }
    }

    @SuppressWarnings("rawtypes")
    private static void setBeanInfo(Class cls, String beanName, Bean bean) {
        bean.setBeanName(beanName);
        HashMap<String, Object> instanceMap = new HashMap<String, Object>();
        try {
            instanceMap.put(beanName, cls.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        bean.setInstances(instanceMap);
    }

    private static void checkFieldAnnotations() {
        try {
            for (Bean bean : BEAN_MAP.values()) {
                Field[] fields = bean.getCls().getDeclaredFields();
                Map<String, Bean> fieldMap = new HashMap<>();
                if (fields.length > 0) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Qualifier.class)) {
                            String fieldName = (field.getAnnotation(Qualifier.class)).value();
                            if (fieldName.trim().equals("")) {
                                fieldName = field.getName();
                            }
                            Bean fieldBean = checkLoadBean(fieldName);
                            if (null == fieldBean) {
                                throw new RuntimeException("bean name:" + fieldName + " bean is not found!");
                            }
                            fieldMap.put(fieldName, fieldBean);
                            field.setAccessible(true);
                            field.set(bean.getInstances().get(bean.getBeanName()),
                                    fieldBean.getInstances().get(fieldName));
                        }
                    }
                }
                bean.setFieldMap(fieldMap);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void checkMethodAnnotations() {
        try {
            for (Bean bean : BEAN_MAP.values()) {
                Method[] methods = bean.getCls().getMethods();
                Map<String, Bean> urlMap = new HashMap<>();
                if (!urlMap.isEmpty()) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Qualifier.class)) {
                            String fieldName = ((Qualifier) method.getAnnotation(RequestMapping.class)).value();
                            if (fieldName.trim().equals("")) {
                                fieldName = method.getName();
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes"})
    private static void resolveClass(String className) {
        // load class and resolveClass by reflection.
        try {
            Class cls = Class.forName(className);
            Bean bean = new Bean();
            // 1.get type proper.
            checkTypeAnnotations(cls, bean);
            if (bean.getBeanName() == null || bean.getBeanName().trim().equals("")) {
                return;
            }

            bean.setClasspath(className);
            bean.setCls(cls);
            BEAN_MAP.put(bean.getBeanName(), bean);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class name:" + className + " is not found!");
        }
    }

    private static String setCamelClsName(String clsName, int index) {
        return clsName.substring(index, index + 1).toLowerCase() + clsName.substring(index + 1);
    }

    /**
     * 检验是否已加载beanName对应的bean.
     *
     * @param beanName
     * @return
     */
    private static Bean checkLoadBean(String beanName) {
        return BEAN_MAP.get(beanName);
    }


    public static void main(String[] args) throws IOException {

        String path = getProjectRootDir() + replacePath();
        List<String> pathLst = getFilesUnderDir("", path);

        // 1.resolve class type
        for (String childPath : pathLst) {
            resolveClass(childPath);
        }

        // 2.resolve class field type
        checkFieldAnnotations();

        Bean bean = BEAN_MAP.get("stuController");
        StuController obj = (StuController) bean.getInstances().get("stuController");
        obj.printTest();
    }
}
