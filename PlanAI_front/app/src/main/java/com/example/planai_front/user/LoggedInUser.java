package com.example.planai_front.user;

import androidx.annotation.NonNull;

import lombok.Getter;

@Getter
public class LoggedInUser {
    private static LoggedInUser instance;

    public static LoggedInUser getInstance(Long id, String name, String email, String phoneNumber) {
        if (instance == null)
            return instance = new LoggedInUser(id, name, email, phoneNumber);
        return instance;
    }

    public static LoggedInUser getInstance() {
        return instance;
    }


    @Override
    public String toString() {
        return "id : " + id + " / name : " + name + " / email : " + email + " / phoneNumber : " + phoneNumber;
    }

    private LoggedInUser(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
