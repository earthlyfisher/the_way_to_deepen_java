package com.earthlyfish.thread;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 使用静态内部类实现单例
 * Created by earthlyfisher on 2017/3/13.
 */
public class SingletonByStaticClass implements Serializable {

    protected static final long serialVersionUID = 888L;

    private SingletonByStaticClass() {
    }

    private static class Holder {
        public static SingletonByStaticClass instance = new SingletonByStaticClass();

    }

    public static SingletonByStaticClass getInstance() {
        return Holder.instance;
    }

    protected Object readResolve() throws ObjectStreamException {
        return Holder.instance;
    }
}
