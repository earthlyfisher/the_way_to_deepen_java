package com.earthlyfish.workmodel;

import com.earthlyfish.utils.SystemUtils;
import com.earthlyfish.workmodel.tasks.ExamTaskFirst;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class WorkModelStarter {

    public static void main(String[] args) throws InterruptedException {
        TaskDispatcher dispatcher = new TaskDispatcher();
        dispatcher.start();
        for (int i = 0; i < 5; i++) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setTaskId(SystemUtils.generateId());
            taskEntity.setTaskType(TaskType.EXAMPLE_TASK.name());
            taskEntity.setCreateTime(System.currentTimeMillis());
            taskEntity.setStatus(TaskUtils.TaskStatus.READY.status);
            ProcessTask task = new ExamTaskFirst(taskEntity);
            taskEntity.setTaskChildType(task);
            TaskUtils.addTask(taskEntity);
        }
    }
}
