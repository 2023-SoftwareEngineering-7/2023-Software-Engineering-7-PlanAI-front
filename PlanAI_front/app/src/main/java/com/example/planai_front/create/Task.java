package com.example.planai_front.create;

import java.util.List;

public class Task{

    private String summary;
    private String description;
    private String deadLineDate;
    private String deadLineTime;
    private String priority;
    private Long ownerId;
    private String tag;

    public Task() {
    }

    public Task(String summary, String description, String deadLineDate, String deadLineTime, String tag,String priority) {
        this.summary = summary;
        this.description = description;
        this.deadLineDate = deadLineDate;
        this.deadLineTime= deadLineTime;
        this.priority = priority;
        this.tag = tag;
    }

    public Task(String summary, String description, String deadLineDate,String deadLineTime, String priority, Long ownerId, String tag) {
        this.summary = summary;
        this.description = description;
        this.deadLineDate = deadLineDate;
        this.deadLineTime = deadLineTime;
        this.priority = priority;
        this.ownerId = ownerId;
        this.tag = tag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public String getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(String deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
