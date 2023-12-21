package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.planai_front.Profile.SettingActivity;
import com.example.planai_front.create.ShowDayActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.ArrayList;


public class FriendlistActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<FriendItem> mfriendItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Button Button = (Button) findViewById(R.id.buttonaddf);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddfriendActivity.class);
            startActivity(intent);
        });

        setupBottomNavigationBar();
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_calendar) {
                //캘린더 화면으로 돌아옴
                startActivity(new Intent(FriendlistActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                //친구목록 화면
                startActivity(new Intent(FriendlistActivity.this, FriendlistActivity.class));


            } else if (item.getItemId() == R.id.navigation_home){
                //오늘 일정으로 이동
                LocalDate today = LocalDate.now();
                Intent homeIntent = new Intent(FriendlistActivity.this, ShowDayActivity.class);
                homeIntent.putExtra("date", today.toString());
                homeIntent.putExtra("fullDateInfo", today.getDayOfWeek().toString());
                startActivity(homeIntent);

            }else if (item.getItemId() == R.id.navigation_post){
                //게시판으로 이동
                startActivity(new Intent(FriendlistActivity.this, BoardActivity.class));


            }else if (item.getItemId() == R.id.navigation_setting) {
                //프로필 세팅으로 이동
                startActivity(new Intent(FriendlistActivity.this, SettingActivity.class));

            }
            return true;
        });
    }

}