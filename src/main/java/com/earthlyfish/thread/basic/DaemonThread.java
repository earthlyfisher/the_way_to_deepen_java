package com.earthlyfish.thread.basic;

/**
 * Created by earthlyfisher on 2017/3/9.
 */
public class DaemonThread {

    public static void main(String[] args) {
        MyThread thread=new MyThread();
        thread.setName("守护线程");
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(500);
            System.out.println("非守护线程main线程结束，守护线程也不打印了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}