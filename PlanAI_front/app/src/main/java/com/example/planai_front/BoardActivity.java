package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import android.animation.ObjectAnimator;

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

        mRecyclerView = findViewById(R.id.recyclerView);

        /* 어댑터 초기화 */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* 리사이클러뷰 초기화 */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        /* 데이터 추가 */
        mfriendItems = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0)
                mfriendItems.add(new FriendItem(R.drawable.jhanoo, i + "번째 사람", i + "번째 상태메시지"));
            else
                mfriendItems.add(new FriendItem(R.drawable.jhanoo, i + "번째 사람", i + "번째 상태메시지"));
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

        // 내글목록 버튼 클릭
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardActivity.this, "내 글 목록 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 글쓰기 버튼 클릭
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardActivity.this, "글쓰기 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 글쓰기 액티비티로 이동하는 버튼
        FloatingActionButton imageButton = findViewById(R.id.fabEdit);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            startActivity(intent);
        });

        // 내글목록 액티비티로 이동하는 버튼
        FloatingActionButton imageButton2 = findViewById(R.id.fabCamera);
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MywriteActivity.class);
            startActivity(intent);
        });
    }

    // 플로팅 액션 버튼 클릭시 애니메이션 효과
    public void toggleFab() {
        if (fabMain_status) {
            // 플로팅 액션 버튼 닫기
            // 애니메이션 추가
            ObjectAnimator fc_animation = ObjectAnimator.ofFloat(fabCamera, "translationY", 0f);
            fc_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", 0f);
            fe_animation.start();
            // 메인 플로팅 이미지 변경
            fabMain.setImageResource(R.drawable.plus);
        } else {
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
