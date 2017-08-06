package com.earthlyfish.jpa;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(System.getSecurityManager());

        FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                System.out.println(Thread.currentThread());
                return "132";
            }
        });
        new Thread(ft).start();
        System.out.println(ft.get());
    }
}
