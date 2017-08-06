package com.earthlyfish.thread.basic;

/**
 * Created by earthlyfisher on 2017/3/10.
 */

//由于AA是字符串常量，所以多线程同步监视
public class StringPoolSyncTest {

    public static void main(String[] args) {
        Service service =new Service();
        SyncThread a=new SyncThread("a",service);
        SyncThread b=new SyncThread("b",service);
        a.start();
        b.start();
    }
}




class SyncThread extends Thread {
    private Service service;

    public SyncThread(String name,Service service) {
        super(name);
        this.service=service;
    }

    @Override
    public void run() {
       service.print("AA");
    }
}
class Service {
    public  void print(Object object) {
        synchronized (object) {
            try {
                while(true){
                    System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}