package com.example.planai_front.create;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Arrays;
import java.util.Calendar;

public class Schedule implements CalendarItem{

    private String id;
    private String summary;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String tag;
    private String description;
    private String collaborators;

    // 기본 생성자
    public Schedule() {
    }

    public Schedule(String id, String summary, String startDate, String endDate, String description, String tag){
        this.summary = summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.tag = tag;

    }


    // 모든 필드를 포함하는 생성자
    public  Schedule(String id, String summary, String startDate, String startTime, String endDate, String endTime, String tag, String description){
        this.id = id;
        this.summary = summary;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.tag = tag;
        this.description = description;

    }



    // Getter 및 Setter 메서드
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "ScheduleDetails{" +
                "summary='" + summary + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
