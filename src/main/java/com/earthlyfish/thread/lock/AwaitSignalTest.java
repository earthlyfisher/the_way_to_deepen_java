package com.earthlyfish.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by earthlyfisher on 2017/3/13.
 */
public class AwaitSignalTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void waitMethod() {
        while (lock.tryLock()) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " await" + System.currentTimeMillis());
                condition.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void notifyMethod() {
        while (lock.tryLock()) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " signal " + System.currentTimeMillis());
                Thread.sleep(2000);
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AwaitSignalTest service = new AwaitSignalTest();
        Thread thread = new Thread() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };

        thread.start();
        service.notifyMethod();
    }
}
