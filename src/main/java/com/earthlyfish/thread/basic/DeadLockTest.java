package com.earthlyfish.thread.basic;

/**
 * Created by earthlufisher on 2017/3/10.
 */
public class DeadLockTest {

    public static void main(String[] args) throws InterruptedException {
        DeadLockThread deadLock = new DeadLockThread();
        deadLock.setUserName("a");
        Thread a = new Thread(deadLock);
        a.start();

        Thread.sleep(100);

        deadLock.setUserName("b");
        Thread b = new Thread(deadLock);
        b.start();
    }
}

class DeadLockThread implements Runnable {

    private String userName;

    private Object object1 = new Object();

    private Object object2 = new Object();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {

        if (userName.equals("a")) {
            synchronized (object1) {
                try {
                    System.out.println("useName=" + userName);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object2) {
                    System.out.println("lock1->lock2");

                }
            }
        }


        if (userName.equals("b")) {
            synchronized (object2) {
                try {
                    System.out.println("useName=" + userName);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object1) {
                    System.out.println("lock2->lock1");

                }
            }
        }
    }
}