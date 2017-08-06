package com.earthlyfish.thread.basic;

/**
 * Created by earthlyfisher on 2017/3/9.
 */
public class ExitThreadByInterrupt {

    public static void main(String[] args) {
        InterruptThread thread = new InterruptThread("exit-sub-thread");
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        System.out.println(thread.getA() + ";" + thread.getB());
    }
}


class InterruptThread extends Thread {
    private int a = 0;

    private int b = 0;


    public InterruptThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (this.isInterrupted()) {
                    System.out.println("我被中断了，我要退出！");
                    throw new InterruptedException();
                }
                a++;
                b++;
            }
        } catch (InterruptedException e) {
            System.out.println("进入了中断处理处！");
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
