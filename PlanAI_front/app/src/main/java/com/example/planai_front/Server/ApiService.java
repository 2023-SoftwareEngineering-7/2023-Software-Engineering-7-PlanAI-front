package com.example.planai_front.Server;

import com.example.planai_front.create.Schedule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    //TODO:서버에서 정의된 엔드포인트=> 유저 등록 먼저 하고, 해당 유저 id를 불러올것
    @POST("user/1/Schedule") // 여기서 "schedule/create"는 서버에서 정의된 엔드포인트를 나타냅니다.
    Call<Server_ScheduleDTO> createSchedule(@Body Server_ScheduleDTO post);

    @POST("user/1/Post") // 여기서 "schedule/create"는 서버에서 정의된 엔드포인트를 나타냅니다.
    Call<Server_PostRegisterDTO> createSchedule(@Body Server_PostRegisterDTO post);

    @POST("user/register")
    Call<UserRegisterResponseDTO> registerUser(@Body RegisterUserDTO dto);

}