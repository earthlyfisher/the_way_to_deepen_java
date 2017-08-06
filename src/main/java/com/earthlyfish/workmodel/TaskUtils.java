package com.earthlyfish.workmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class TaskUtils {

    public static final TreeMap<Long, TaskEntity> READY_TASK_MAP = new TreeMap<Long, TaskEntity>();

    public static final TreeMap<Long, TaskEntity> HISTORY_TASK_MAP = new TreeMap<Long, TaskEntity>();

    public static final int TASK_COUNT = 50;

    private static Long nextTaskId = 0L;

    /**
     * update task
     *
     * @param task
     */
    public synchronized static void addTask(TaskEntity task) {
        READY_TASK_MAP.put(task.getTaskId(), task);
    }

    /**
     * update task
     *
     * @param task
     */
    public synchronized static void updateTask(TaskEntity task) {
        READY_TASK_MAP.put(task.getTaskId(), task);
    }

    /**
     * 将失败任务移除到历史任务表
     *
     * @param taskId
     */
    public static void moveFailTaskToHistoryMap(Long taskId) {
        TaskEntity task = READY_TASK_MAP.get(taskId);
        task.setErrorcode("COMMON_INNER_ERROR");
        task.setLastModifyTime(System.currentTimeMillis());
        task.setStatus(TaskStatus.FAILED.status);
        synchronized (TaskUtils.class) {
            READY_TASK_MAP.remove(taskId);
            HISTORY_TASK_MAP.put(taskId, task);
        }
    }

    /**
     * 将成功任务移除到历史任务表
     *
     * @param taskId
     */
    public static void moveSuccessTaskToHistoryMap(Long taskId) {
        TaskEntity task = READY_TASK_MAP.get(taskId);
        task.setLastModifyTime(System.currentTimeMillis());
        task.setStatus(TaskStatus.SUCCESS.status);
        task.setProgress(100);
        synchronized (TaskUtils.class) {
            READY_TASK_MAP.remove(taskId);
            HISTORY_TASK_MAP.put(taskId, task);
        }
    }

    public static synchronized List<TaskEntity> getSomeReadyTasks() {
        List<TaskEntity> tasks = new ArrayList<TaskEntity>();
        int i = 0;
        for (Map.Entry<Long, TaskEntity> entity : READY_TASK_MAP.entrySet()) {
            if (entity.getKey() >= nextTaskId && entity.getValue().getStatus().equals(TaskStatus.READY.status)) {
                tasks.add(entity.getValue());
                i++;
            }

            if (i == TASK_COUNT) {
                nextTaskId = entity.getKey();
                break;
            }
        }

        if (i < TASK_COUNT) {
            nextTaskId = 0L;
        }

        return tasks;
    }

    public static boolean isNotFinished(Long taskId) {
        return READY_TASK_MAP.containsKey(taskId);
    }

    public enum TaskStatus {
        READY("ready"),
        RUNNING("running"),
        FINISH("finish"),
        SUCCESS("success"),
        FAILED("failed");

        public String status;

        TaskStatus(String status) {
            this.status = status;
        }
    }
}
