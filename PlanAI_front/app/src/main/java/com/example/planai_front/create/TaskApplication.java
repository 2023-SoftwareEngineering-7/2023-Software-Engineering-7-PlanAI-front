//package com.example.planai_front.create;
//
//import android.app.Application;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class TaskApplication extends Application {
//
//    private static TaskApplication instance; // 애플리케이션 인스턴스
//    private HashMap<String, ArrayList<Task>> TaskMap; // 스케줄 맵
//
//    public TaskApplication() {
//        instance = this; // 현재 인스턴스 저장
//        TaskMap = new HashMap<>(); // 스케줄 맵 초기화
//    }
//
//    public static TaskApplication getInstance() {
//        return instance; // 인스턴스 반환
//    }
//
//    public HashMap<String, ArrayList<Task>> getTaskMap() {
//        return TaskMap; // 스케줄 맵 반환
//    }
//}
