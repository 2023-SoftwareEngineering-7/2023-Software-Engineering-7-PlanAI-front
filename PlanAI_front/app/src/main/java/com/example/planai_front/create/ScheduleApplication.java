package com.example.planai_front.create;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
/*
Server Test Branch(23.11.27)
 */
public class ScheduleApplication extends Application {
    private static ScheduleApplication instance;
    private HashMap<String, ArrayList<Schedule>> scheduleMap;

    public ScheduleApplication() {
        instance = this;
        scheduleMap = new HashMap<>();
    }

    public static ScheduleApplication getInstance() {
        return instance;
    }

    public HashMap<String, ArrayList<Schedule>> getScheduleMap() {
        return scheduleMap;
    }
}