package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


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
        Button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GetfriendActivity.class);
            startActivity(intent);
        });
    }

}