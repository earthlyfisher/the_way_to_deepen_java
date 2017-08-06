package com.earthlyfish.thread.basic;

/**
 * Created by earthlyfisher on 2017/3/9.
 */
public class ExitThreadByFlag {

    public static void main(String[] args) {
        FlagThread thread = new FlagThread("exit-sub-thread");
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.exitFlag = true;
        System.out.println(thread.getA() + ";" + thread.getB());
    }
}


class FlagThread extends Thread {
    private int a = 0;

    private int b = 0;

    public volatile boolean exitFlag = false;

    public FlagThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            if (exitFlag) {
                System.out.println("我听到指令退出了");
                return;
            }
            a++;
            b++;
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
