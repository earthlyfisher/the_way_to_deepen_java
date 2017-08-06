package com.earthlyfish.loader;

import java.lang.reflect.Method;

/**
 * Created by earthlyfisher on 2017/3/8.
 */
public class LoaderEntry {

    public static void main(String[] args) throws Exception {

        Thread.sleep(2000);

        ClassLoader classLoader = new SelfClassloader();
        Class clazz = classLoader.loadClass("com.earthlyfish.loader.CaseInfo");

        //注意：此处不能通过cast强转的方式进行实例的转换
        //因为这里的CaseInfo类是系统类加载器加载的，由于该处clazz由自定义的加载器加载
        //CaseInfo lc = (CaseInfo) clazz.newInstance();

        Object object = clazz.newInstance();

        //调用加载的类的实例的方法
        Method method = object.getClass().getMethod("setAllproperty", String.class, String.class, String.class);
        method.invoke(object, "1", "Gavin", null);
        System.out.println(object);

        //以下测试类的卸载过程
        method = null;
        object = null;
        clazz = null;
        classLoader = null;
        System.gc();

        Thread.sleep(4000);

        System.gc();
    }
}
