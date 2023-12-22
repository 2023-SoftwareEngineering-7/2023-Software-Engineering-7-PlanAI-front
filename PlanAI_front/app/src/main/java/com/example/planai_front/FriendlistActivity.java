package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class FriendlistActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FriendlistRecyclerAdapter mRecyclerAdapter;
    private ArrayList<FriendlistItem> mfriendItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.friendlsit_recycle);

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));


        mfriendItems = new ArrayList<>();
        mfriendItems.add(new FriendlistItem(R.drawable.jhanoo, "name_item", "namebutton"));
        mfriendItems.add(new FriendlistItem(R.drawable.jhanoo, "name_item", "namebutton"));
        // ... add more items

        FriendlistRecyclerAdapter adapter = new FriendlistRecyclerAdapter(this, mfriendItems);
        mRecyclerView.setAdapter(adapter);



        Button Button = (Button) findViewById(R.id.buttonaddf);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddfriendActivity.class);
            startActivity(intent);
        });
        Button Button1 = (Button) findViewById(R.id.buttonftf);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GetfriendActivity.class);
            startActivity(intent);
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView12);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));


        /* adapt data */
        mfriendItems = new ArrayList<>();
        mfriendItems.add(new FriendItem(R.drawable.nav_item_background,"",""));
        mRecyclerAdapter.setFriendList(mfriendItems);


        Button Button1 = (Button) findViewById(R.id.buttonftf);
        Button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GetfriendActivity.class);
            startActivity(intent);
        });
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_calendar) {
                startActivity(new Intent(FriendlistActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                startActivity(new Intent(FriendlistActivity.this, FriendlistActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_home){
                startActivity(new Intent(FriendlistActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_post){
                startActivity(new Intent(FriendlistActivity.this, BoardActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_setting) {
                startActivity(new Intent(FriendlistActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현
            }
            return true;
        });
    }

}