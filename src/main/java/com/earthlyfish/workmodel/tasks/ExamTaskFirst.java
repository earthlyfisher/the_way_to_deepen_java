package com.earthlyfish.workmodel.tasks;

import com.earthlyfish.utils.SystemUtils;
import com.earthlyfish.workmodel.ProcessTask;
import com.earthlyfish.workmodel.TaskEntity;
import com.earthlyfish.workmodel.TaskUtils;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class ExamTaskFirst extends ProcessTask {

    public ExamTaskFirst(TaskEntity taskEntity) {
        super(taskEntity);
    }

    @Override
    public void execute() throws Exception {
        SystemUtils.printInfoLog("current Thread name=%s  taskId= %s",
                Thread.currentThread().getName(), taskEntity.getTaskId());
        Thread.sleep(500);

        taskEntity.setLastModifyTime(System.currentTimeMillis());
        taskEntity.setStatus(TaskUtils.TaskStatus.READY.status);
        ProcessTask task = new ExamTaskLast(taskEntity);
        taskEntity.setTaskChildType(task);
        TaskUtils.updateTask(taskEntity);
    }

    @Override
    public void dealNotify() throws Exception {
        SystemUtils.printInfoLog("call first example task finished");
    }
}
