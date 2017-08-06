package com.earthlyfish.workmodel;

import com.earthlyfish.utils.SystemUtils;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public abstract class ProcessTask implements Runnable {

    protected TaskEntity taskEntity;

    public ProcessTask(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @Override
    public void run() {
        execTask();
    }

    private void execTask() {
        try {
            execute();
        } catch (Exception e) {
            SystemUtils.printInfoLog("exec task, taskType:%s, taskId:%s failed, error:%s .",
                    taskEntity.getTaskType(), taskEntity.getTaskId(), e.getMessage());
            TaskUtils.moveFailTaskToHistoryMap(taskEntity.getTaskId());
        } finally {
            try {
                dealNotify();
            } catch (Exception e) {
                SystemUtils.printInfoLog("exec task, taskType:%s, taskId:%s failed, error:%s .",
                        taskEntity.getTaskType(), taskEntity.getTaskId(), e.getMessage());
            }
        }
    }

    public abstract void execute() throws Exception;

    public abstract void dealNotify() throws Exception;
}
