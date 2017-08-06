package com.earthlyfish.thread.basic;

/**
 * Created by earthlyfisher on 2017/3/9.
 */
public class ExitThreadByStop {

    public static void main(String[] args) {
        AThread thread=new AThread("exit-sub-thread");
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
        System.out.println(thread.getA()+";"+thread.getB());
    }
}


class AThread extends Thread {
    private int a = 0;

    private int b = 0;

    public AThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
             a++;
             Thread.sleep(500);
             b++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}