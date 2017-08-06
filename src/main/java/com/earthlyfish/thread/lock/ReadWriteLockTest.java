package com.earthlyfish.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock有共享锁和排他锁两种，读读共享，读写和写读互斥
 * Created by earthlyfisher on 2017/3/13.
 */
public class ReadWriteLockTest {
    ReentrantReadWriteLock locks = new ReentrantReadWriteLock();

    private void read() {
        locks.readLock().lock();
        System.out.println("get read  lock " + Thread.currentThread().getName() + "  " + System.currentTimeMillis());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locks.readLock().unlock();
        }
    }

    private void write() {
        locks.writeLock().lock();
        System.out.println("get write lock " + Thread.currentThread().getName() + "  " + System.currentTimeMillis());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locks.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockTest service = new ReadWriteLockTest();
        Runnable taskA = new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        };
        Runnable taskB = new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        };
        Thread a = new Thread(taskA, "a");
        Thread b = new Thread(taskB, "b");
        a.start();
        b.start();
    }
}
