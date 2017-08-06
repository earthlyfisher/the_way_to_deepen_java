package com.earthlyfish.workmodel;

import com.earthlyfish.utils.SystemUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class Workers {

    private ThreadPoolExecutor threadPoolExecutor = null;

    private Workers() {
        threadPoolExecutor = new ThreadPoolExecutor(30, 50, 10L, SECONDS,
                new ArrayBlockingQueue<Runnable>(3000), new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 静态内部类创建单例works
     */
    private static class WorkerHolder {
        public static Workers instance = new Workers();
    }


    public static boolean addTaskToWorkers(ProcessTask task) {
        try {
            WorkerHolder.instance.threadPoolExecutor.execute(task);
            return true;
        } catch (Exception e) {
            SystemUtils.printErrorLog(e, "add task to thread pool error");
        }
        return false;
    }
}
