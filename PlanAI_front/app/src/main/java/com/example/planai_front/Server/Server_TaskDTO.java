package com.example.planai_front.Server;

import com.example.planai_front.create.Priority;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Server_TaskDTO {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("deadLine")
    @Expose
    private String deadLine;
    @SerializedName("priority")
    @Expose
    private Priority priority;
    @SerializedName("ownerId")
    @Expose
    private Long ownerId;
    @SerializedName("tagList")
    @Expose
    private List<String> tagList;

    public Server_TaskDTO() {
    }

    public Server_TaskDTO(String title, String description, String deadLine, Priority priority, Long ownerId, List<String> tagList) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.priority = priority;
        this.ownerId = ownerId;
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
