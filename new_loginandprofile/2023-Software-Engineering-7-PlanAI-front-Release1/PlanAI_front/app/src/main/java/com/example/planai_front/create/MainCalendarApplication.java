package com.example.planai_front.create;

import android.app.Application;
import java.util.ArrayList;
import java.util.HashMap;

public class MainCalendarApplication extends Application {
    private static MainCalendarApplication instance; // 애플리케이션 인스턴스
    private HashMap<String, ArrayList<Schedule>> scheduleMap; // 스케줄 맵
    private HashMap<String, ArrayList<Task>> TaskMap; // 태스크 맵

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        scheduleMap = new HashMap<>();
        TaskMap = new HashMap<>();
    }

    public static MainCalendarApplication getInstance() {
        return instance;
    }

    public HashMap<String, ArrayList<Schedule>> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(HashMap<String, ArrayList<Schedule>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public HashMap<String, ArrayList<Task>> getTaskMap() {
        return TaskMap;
    }

    public void setTaskMap(HashMap<String, ArrayList<Task>> taskMap) {
        this.TaskMap = taskMap;
    }
}