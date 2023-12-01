package com.example.planai_front;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MywriteActivity extends AppCompatActivity {

    private static final int YOUR_REQUEST_CODE = 123;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<FriendItem> mfriendItems;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywrite);

        ImageView myImageView = findViewById(R.id.imageView2);

        mRecyclerView = findViewById(R.id.recyclerViewmw);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        // 이미 생성된 리스트를 사용하여 데이터를 추가
        mfriendItems = new ArrayList<>();

        if(mfriendItems == null) {
            mfriendItems.add(new FriendItem(R.drawable.nav_item_background, "", ""));
        }
        else
        {
            mfriendItems.add(new FriendItem(R.drawable.jhanoo, title, "    " + content));
        }



        // RecyclerView 어댑터에 데이터 변경 알리기
        mRecyclerAdapter.setFriendList(mfriendItems);

        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 시 수행할 작업

                // 다른 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(MywriteActivity.this, BoardActivity.class);

                // Intent 실행
                startActivity(intent);
            }
        });
    }

}
