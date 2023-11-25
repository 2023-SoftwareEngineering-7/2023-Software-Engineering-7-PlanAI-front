package com.example.planai_front.create;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Arrays;
import java.util.Calendar;

public class Schedule {
    private String id;
    private String summary;
    private String datetime;
    private String creator;
    private String description;
    private  String start;
    private  String end;
    private  boolean open_schedule;
    private enum tag{}
    private  boolean saveState;

    private String collaborators;

    public Schedule(String id, String summary, String datetime, String creator, String description, String start, String end, boolean open_schedule, String collaborators, boolean saveState) {
        this.id = id;
        this.summary = summary;
        this.datetime = datetime;
        this.creator = creator;
        this.description = description;
        this.start = start;
        this.end = end;
        this.open_schedule = open_schedule;
        this.collaborators = "hello owordl";
        this.saveState = saveState;
    }

    public Schedule(String summary, String start, String end, String description, String collaborators) {
        this.summary = summary;
        this.description = description;
        this.start = start;
        this.end = end;
        this.collaborators = collaborators;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isOpen_schedule() {
        return open_schedule;
    }

    public void setOpen_schedule(boolean open_schedule) {
        this.open_schedule = open_schedule;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    public boolean isSaveState() {
        return saveState;
    }

    public void setSaveState(boolean saveState) {
        this.saveState = saveState;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", summary='" + summary + '\'' +
                ", datetime=" + datetime +
                ", creator='" + creator + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", open_schedule=" + open_schedule +
                ", collaborators=" + collaborators +
                ", saveState=" + saveState +
                '}';
    }
}
