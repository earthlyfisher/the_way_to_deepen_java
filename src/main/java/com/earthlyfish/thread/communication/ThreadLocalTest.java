package com.earthlyfish.thread.communication;

import java.util.Date;

/**
 * InheritableThreadLocal可以在子线程中取父线程继承下来的值
 * Created by earthlyfisher on 2017/3/13.
 */
public class ThreadLocalTest {

    private static InheritableThreadLocal itl = new InheritableThreadLocal() {

        //该方法可以在继承父线程的值得同时做一些修改
        @Override
        protected Object childValue(Object parentValue) {
            return parentValue + " I am a sub thread";
        }

        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
    };


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("main=" + itl.get());
            Thread.sleep(500);
        }
        Thread.sleep(500);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("thread=" + itl.get());
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
