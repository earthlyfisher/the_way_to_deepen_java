package com.earthlyfish.loader;

import com.earthlyfish.utils.SystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 *
 */
public class SelfClassloader extends ClassLoader {

    private static final String EXECLUDE_PACKAGE = "com.earthlyfish.loader";

    public SelfClassloader() {
        super(getSystemClassLoader());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz;
        clazz = findLoadedClass(name);

        if (null != clazz) {
            return clazz;
        }
        if (name.indexOf(EXECLUDE_PACKAGE) < 0) {
            clazz = this.getParent().loadClass(name);
            if (clazz != null) {
                return clazz;
            }
        }

        clazz = findClass(name);

        if (resolve) {
            resolveClass(clazz);
        }

        return clazz;
    }

    @SuppressWarnings("resource")
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz;
        String path = SystemUtils.getClsFileRootDir() + name.replace(".", "//") + ".class";
        File file = new File(path);
        if (file.exists()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            try {
                new FileInputStream(file).getChannel().read(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byteBuffer.flip();

            clazz = this.defineClass(name, byteBuffer.array(), 0, (int) file.length());
            return clazz;
        }
        throw new ClassNotFoundException();
    }
}
