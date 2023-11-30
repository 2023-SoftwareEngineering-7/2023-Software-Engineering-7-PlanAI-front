package com.example.planai_front;

import static com.example.planai_front.Server.RetrofitClient.PlanAI_URL;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import android.animation.ObjectAnimator;

import com.example.planai_front.Server.ApiService;
import com.example.planai_front.Server.RetrofitClient;
import com.example.planai_front.Server.Server_PostRegisterDTO;
import com.example.planai_front.WriteActivity;
import com.example.planai_front.Server.Server_ScheduleDTO;
import com.example.planai_front.create.Schedule;
import com.example.planai_front.create.ScheduleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BoardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<FriendItem> mfriendItems;


    // 플로팅버튼 상태
    private boolean fabMain_status = false;

    private FloatingActionButton fabMain;
    private FloatingActionButton fabCamera;
    private FloatingActionButton fabEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        /* adapt data */
        mfriendItems = new ArrayList<>();
        for(int i=1;i<=10;i++){
            if(i%2==0)
                mfriendItems.add(new FriendItem(R.drawable.jhanoo,i+"번째 사람",i+"번째 상태메시지"));
            else
                mfriendItems.add(new FriendItem(R.drawable.jhanoo,i+"번째 사람",i+"번째 상태메시지"));

        }
        mRecyclerAdapter.setFriendList(mfriendItems);


        fabMain = findViewById(R.id.fabMain);
        fabCamera = findViewById(R.id.fabCamera);
        fabEdit = findViewById(R.id.fabEdit);

        // 메인플로팅 버튼 클릭
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab();

            }
        });
        // 카메라 플로팅 버튼 클릭
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardActivity.this, "카메라 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 수정 플로팅 버튼 클릭
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardActivity.this, "수정 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton imageButton = (FloatingActionButton) findViewById(R.id.fabEdit);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            startActivity(intent);
        });

        FloatingActionButton imageButton2 = (FloatingActionButton) findViewById(R.id.fabCamera);
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MywriteActivity.class);
            startActivity(intent);
        });


    }



    // 플로팅 액션 버튼 클릭시 애니메이션 효과
    public void toggleFab() {
        if(fabMain_status) {
            // 플로팅 액션 버튼 닫기
            // 애니메이션 추가
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabCamera, "translationY", 0f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", 0f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.plus);

        }else {
            // 플로팅 액션 버튼 열기
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabCamera, "translationY", -200f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", -400f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.plus);
        }
        // 플로팅 버튼 상태 변경
        fabMain_status = !fabMain_status;
    }
}



