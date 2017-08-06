package com.earthlyfish.workmodel;

import com.earthlyfish.utils.StringUtils;
import com.earthlyfish.utils.SystemUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class TaskDispatcher extends Thread {

    @Override
    public void run() {

        /**
         * next dispatch task period time
         */
        Long nextExecSleepTime = 1000L;

        while (true) {
            try {
                List<TaskEntity> readyTasks = TaskUtils.getSomeReadyTasks();
                Iterator<TaskEntity> it = readyTasks.iterator();
                while (it.hasNext()) {
                    TaskEntity task = it.next();
                    if (StringUtils.isNullOrEmpty(task.getTaskType())) {
                        continue;
                    }

                    task.setStatus(TaskUtils.TaskStatus.RUNNING.status);
                    Integer progress = task.getProgress();
                    if ((progress == null) || (0 == task.getProgress().intValue())) {
                        task.setProgress(Integer.valueOf(10));
                    }
                    TaskUtils.updateTask(task);

                    if (!Workers.addTaskToWorkers(task.getTaskChildType())) {
                        task.setStatus(TaskUtils.TaskStatus.READY.status);
                        TaskUtils.updateTask(task);

                        Thread.sleep(1000);
                    }
                    continue;
                }
            } catch (Exception e) {
                SystemUtils.printErrorLog(e, "task dispatch exception");
            }

            try {
                Thread.sleep(nextExecSleepTime);
            } catch (InterruptedException e) {
                SystemUtils.printErrorLog(e, "task dispatch exception");
            }
        }
    }
}
