package com.earthlyfish.thread.communication;

/**
 * Created by earthlyfisher on 2017/3/13.
 */
public class BackUpByNotify {
    public static void main(String[] args) {
        DbTools tools = new DbTools();
        for (int i = 0; i < 10; i++) {
            A a = new A(tools);
            B b = new B(tools);
            a.start();
            b.start();
        }
    }
}


class A extends Thread {
    private DbTools tools;

    public A(DbTools tools) {
        this.tools = tools;
    }

    @Override
    public void run() {
        tools.backUpA();
    }
}


class B extends Thread {
    private DbTools tools;

    public B(DbTools tools) {
        this.tools = tools;
    }

    @Override
    public void run() {
        tools.backUpB();
    }
}

class DbTools {

    private volatile boolean currentIsA = false;

    synchronized public void backUpA() {

        try {
            while (currentIsA) {
                this.wait();
            }

            System.out.println("*****************");

            currentIsA = true;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    synchronized public void backUpB() {
        try {
            while (!currentIsA) {
                this.wait();
            }
            System.out.println("00000000000000000");
            currentIsA = false;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}