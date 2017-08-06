package com.earthlyfish.thread.basic;

import java.lang.management.ManagementFactory;

public class ThreadTest {

    public static void main(String[] args) throws Exception {
        System.out.println("我是主线程：no：" + Thread.currentThread());
        Thread thread = new SubThread("sub-thread");
        thread.start();
        thread.join();
        System.out.println("主线程complish：no：" + Thread.currentThread());

        System.out.println(Status.START.name());
        System.out.println(Status.START.toString());
        System.out.println(Status.STOP.ordinal());
        System.out.println(Status.STOP.getClass() + "     " +Status.RUNNING) ;
        System.out.println(Status.STOP);
        String name = ManagementFactory.getRuntimeMXBean().getClassPath();
        System.out.println(name);
    }
}

class SubThread extends Thread {

    public SubThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("我是子线程：no：" + Thread.currentThread());
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stes) {
            System.out.printf("文件名:%s  类名:%s  行号:%s 方法名:%s  +\r\n", ste.getFileName(), ste.getClassName(),
                    ste.getLineNumber(), ste.getMethodName());
        }
    }
}

enum Status {
    START(0), RUNNING(1), STOP(2);

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    private int key;

    private Status(int key) {
        this.key = key;
    }
}