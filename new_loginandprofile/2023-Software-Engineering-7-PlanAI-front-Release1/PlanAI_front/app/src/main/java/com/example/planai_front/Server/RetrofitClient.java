package com.example.planai_front.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String PlanAI_URL = "http://10.0.2.2:8080/";
    //public static String PlanAI_URL = "http://localhost:8080/";
    //public static String PlanAI_URL = "http://192.168.0.20:8080/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(PlanAI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

