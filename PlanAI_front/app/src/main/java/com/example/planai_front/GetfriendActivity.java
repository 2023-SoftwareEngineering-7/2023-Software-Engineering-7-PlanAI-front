package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class GetfriendActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FriendlistRecyclerAdapter mRecyclerAdapter;
    private ArrayList<GetFriendItem> mfriendItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getfriend);



        mRecyclerView = (RecyclerView) findViewById(R.id.getfriendre);

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));


        mfriendItems = new ArrayList<>();
        mfriendItems.add(new GetFriendItem(R.drawable.jhanoo, "name_item", "yesbutton", "nobutton"));
        mfriendItems.add(new GetFriendItem(R.drawable.jhanoo, "name_item", "yesbutton","nobutton"));
        // ... add more items

        GetFriendRecyclerAdapter adapter = new GetFriendRecyclerAdapter(this, mfriendItems);
        mRecyclerView.setAdapter(adapter);
    }
}