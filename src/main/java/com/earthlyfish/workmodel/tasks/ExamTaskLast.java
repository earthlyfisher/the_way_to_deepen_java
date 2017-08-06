package com.earthlyfish.workmodel.tasks;

import com.earthlyfish.utils.SystemUtils;
import com.earthlyfish.workmodel.ProcessTask;
import com.earthlyfish.workmodel.TaskEntity;
import com.earthlyfish.workmodel.TaskUtils;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class ExamTaskLast extends ProcessTask {

    public ExamTaskLast(TaskEntity taskEntity) {
        super(taskEntity);
    }

    @Override
    public void execute() throws Exception {
        SystemUtils.printInfoLog("current Thread name=%s  taskId= %s",
                Thread.currentThread().getName(),taskEntity.getTaskId());
        Thread.sleep(500);

        taskEntity.setLastModifyTime(System.currentTimeMillis());
        taskEntity.setStatus(TaskUtils.TaskStatus.SUCCESS.status);
        TaskUtils.updateTask(taskEntity);
    }

    @Override
    public void dealNotify() throws Exception {
       SystemUtils.printInfoLog("call first example task finished");
        if (taskEntity.getTaskChildType() == null) {
            if (TaskUtils.isNotFinished(taskEntity.getTaskId())) {
                if (taskEntity.getStatus().equals(TaskUtils.TaskStatus.SUCCESS.status)) {
                    TaskUtils.moveSuccessTaskToHistoryMap(taskEntity.getTaskId());
                }
                if (taskEntity.getStatus().equals(TaskUtils.TaskStatus.FAILED.status)) {
                    TaskUtils.moveFailTaskToHistoryMap(taskEntity.getTaskId());
                }
            }
        }
    }
}
