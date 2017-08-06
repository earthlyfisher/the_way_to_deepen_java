package com.earthlyfish.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by earthlyfisher on 2017/4/13.
 */
public class CSByCondition {


    public static void main(String[] args) {
        final MiddleObject object = new MiddleObject();

        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(object.get());
                }
            }
        };
        thread.start();

        for (int i = 0; i < 1000; i++) {
            object.put(i + "");
        }
    }
}

class MiddleObject {
    private int size = 1;

    private int count = 0;

    private int getIndex = 0;

    private int putIndex = 0;

    private final String[] items;

    private final ReentrantLock lock;

    private final Condition notEmpty;

    private final Condition notFull;

    public MiddleObject() {
        this.items = new String[size];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(String elment) {

        try {
            lock.lockInterruptibly();
            while (count == size) {
                notFull.await();
            }
            items[putIndex] = elment;
            putIndex = ++putIndex == size ? 0 : putIndex;
            count++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

    public String get() {
        try {
            lock.lockInterruptibly();
            while (count == 0) {
                notEmpty.await();
            }

            String element = items[getIndex];
            items[getIndex] = null;
            getIndex = ++getIndex == size ? 0 : getIndex;
            count--;

            notFull.signal();

            return element;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}