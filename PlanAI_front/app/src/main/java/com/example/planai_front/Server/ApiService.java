package com.example.planai_front.Server;

import com.example.planai_front.create.Schedule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    //TODO:서버에서 정의된 엔드포인트?
    @POST("user/{userId}/Schedule") // 여기서 "schedule/create"는 서버에서 정의된 엔드포인트를 나타냅니다.
    Call<Server_ScheduleDTO> createSchedule(@Body Server_ScheduleDTO schedule);
}