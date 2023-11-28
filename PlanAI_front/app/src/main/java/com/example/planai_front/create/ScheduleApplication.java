package com.example.planai_front.create;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
/*
Server Test Branch(23.11.27)
 */
public class ScheduleApplication extends Application {
    private static ScheduleApplication instance; // 애플리케이션 인스턴스
    private HashMap<String, ArrayList<Schedule>> scheduleMap; // 스케줄 맵

    public ScheduleApplication() {
        instance = this; // 현재 인스턴스 저장
        scheduleMap = new HashMap<>(); // 스케줄 맵 초기화
    }

    public static ScheduleApplication getInstance() {
        return instance; // 인스턴스 반환
    }

    public HashMap<String, ArrayList<Schedule>> getScheduleMap() {
        return scheduleMap; // 스케줄 맵 반환
    }
}