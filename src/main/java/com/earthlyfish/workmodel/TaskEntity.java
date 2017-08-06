package com.earthlyfish.workmodel;

import java.io.Serializable;

/**
 * Created by earthlyfisher on 2017/3/16.
 */
public class TaskEntity implements Serializable {

    private Long taskId;

    private String entityName;

    private Long createTime;

    private Long lastModifyTime;

    private Integer timeDelay;

    private String taskType;

    private ProcessTask taskChildType;

    private String status;

    private String entityType;

    private String taskDescription;

    private String objectJson;

    private String errorcode;

    private String description;

    private Integer progress;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getTimeDelay() {
        return timeDelay;
    }

    public void setTimeDelay(Integer timeDelay) {
        this.timeDelay = timeDelay;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public ProcessTask getTaskChildType() {
        return taskChildType;
    }

    public void setTaskChildType(ProcessTask taskChildType) {
        this.taskChildType = taskChildType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getObjectJson() {
        return objectJson;
    }

    public void setObjectJson(String objectJson) {
        this.objectJson = objectJson;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof TaskEntity) {
            TaskEntity other = (TaskEntity) obj;
            if (this.getTaskId().equals(other.getTaskId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getTaskId().hashCode();
    }
}

