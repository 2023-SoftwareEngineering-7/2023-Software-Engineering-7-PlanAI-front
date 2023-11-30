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



    private String writeid, writeTitle, writeContent, writeTag;
    private List<String> Server_tagList = new ArrayList<>();

    //선택 날짜, 유저 id 받아오기
    private String todayDate;
    //private
    private Long userid;

    // ActivityResultLauncher 객체 선언. 다른 액티비티에서 결과 받아오는데 사용.
    private final ActivityResultLauncher<Intent> createPostLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        // 결과 코드 OK이고, 반환된 데이터 null 아닐 경우에만 처리.
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // 결과 데이터 Intent 객체로 받아옴.
                            Intent boardData = result.getData();
                            if (boardData != null) {
                                //스케줄 id 생성
                                writeid = userid+boardData.getStringExtra(("Post"));
                                // 인텐트에서 스케줄 관련 데이터 추출.
                                writeTitle = boardData.getStringExtra("title");
                                writeContent = boardData.getStringExtra("content");

                                writeTag = "Work";
                                userid = 1L;
                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                if (Server_tagList == null) {
                                    Server_tagList = new ArrayList<>();

                                }
                                Server_tagList.add(writeTag);

                                if(Server_tagList.isEmpty()){
                                    Log.e("Server!!", "empty tagList");
                                }else{
                                    Log.e("Server!!", Server_tagList.get(0)+"!!!!!");
                                }


                                Server_PostRegisterDTO serverPostRegisterDTO = new Server_PostRegisterDTO(writeTitle, writeContent, userid, Server_tagList );
                                Log.d("Server!!", "Response 1");
                                ApiService apiService = RetrofitClient.getClient(PlanAI_URL).create(ApiService.class);
                                Log.d("Server!!", "Response 2");
                                Call<Server_PostRegisterDTO > callBoard = apiService.createPost(serverPostRegisterDTO);
                                Log.d("Server!!", "Response 3");
                                callBoard.enqueue(new Callback<Server_PostRegisterDTO>() {
                                    @Override
                                    public void onResponse(Call<Server_PostRegisterDTO > callBoard, Response<Server_PostRegisterDTO > response) {
                                        Log.d("Server!!", "Response 4");
                                        if (response.isSuccessful()) {
                                            //성공적인 응답 처리
                                            Log.d("Server!!", "Response OK");
                                        } else {
                                            // 서버 에러 처리
                                            Log.d("Server!!", "Server Error1");
                                            int statusCode = response.code();
                                            String code = response.toString();
                                            Log.d("Server!!", "Response Code: " + code);
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Server_PostRegisterDTO > callBoard, Throwable t) {
                                        // 네트워크 에러 처리
                                        Log.d("Server!!", "Server Error2");
                                        Log.e("Server!!", "Network Error", t);
                                    }
                                });
                            }
                        }
                    }
            );


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
            createPostLauncher.launch(intent);
            //startActivity(intent);
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




