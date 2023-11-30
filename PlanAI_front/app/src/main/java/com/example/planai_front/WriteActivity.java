package com.example.planai_front;

import static com.example.planai_front.Server.RetrofitClient.PlanAI_URL;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.planai_front.Server.ApiService;
import com.example.planai_front.Server.RetrofitClient;
import com.example.planai_front.Server.Server_PostRegisterDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import com.example.planai_front.create.CreateScheduleActivity;
import com.example.planai_front.create.Schedule;
import com.example.planai_front.create.ScheduleAdapter;
import com.example.planai_front.create.ShowDayActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WriteActivity extends AppCompatActivity {

    private EditText Edit_Text_Title, Edit_Text_Content;

    private String writeTitle, writeContent;


    private String writeid, writeTag;
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
                            Intent data = result.getData();
                            if (data != null) {
                                //스케줄 id 생성
                                writeid = userid+data.getStringExtra(("Post"));
                                // 인텐트에서 스케줄 관련 데이터 추출.
                                writeTitle = data.getStringExtra("title");
                                writeContent = data.getStringExtra("content");


                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                Log.e("Server!!", writeTag);
                                if (Server_tagList == null) {
                                    Server_tagList = new ArrayList<>();

                                }
                                Server_tagList.add(writeTag);

                                if(Server_tagList.isEmpty()){
                                    Log.e("Server!!", "empty tagList");
                                }else{
                                    Log.e("Server!!", Server_tagList.get(0));
                                }


                                Server_PostRegisterDTO serverPostRegisterDTO = new Server_PostRegisterDTO(writeTitle, writeContent, userid,Server_tagList );
                                Log.d("Server!!", "Response 1");
                                ApiService apiService = RetrofitClient.getClient(PlanAI_URL).create(ApiService.class);
                                Log.d("Server!!", "Response 2");
                                Call<Server_PostRegisterDTO > call = apiService.createPost(serverPostRegisterDTO);
                                Log.d("Server!!", "Response 3");
                                call.enqueue(new Callback<Server_PostRegisterDTO>() {
                                    @Override
                                    public void onResponse(Call<Server_PostRegisterDTO > call, Response<Server_PostRegisterDTO > response) {
                                        Log.d("Server!!", "Response 4");
                                        if (response.isSuccessful()) {
                                            //성공적인 응답 처리
                                            Log.d("Server!!", "Response OK");

                                        } else {
                                            // 서버 에러 처리
                                            Log.d("Server!!", "Server Error1");
                                            int statusCode = response.code();
                                            Log.d("Server!!", "Response Code: " + statusCode);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Server_PostRegisterDTO > call, Throwable t) {
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
        setContentView(R.layout.activity_write);



        Button Button = (Button) findViewById(R.id.button_wc);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            startActivity(intent);
        });

        Button writeButton = findViewById(R.id.button_write_finish);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getEdit_text_title 메서드 실행
                getEdit_text_title();

                // getEdit_Text_Content 메서드 실행
                getEdit_Text_Content();

                // 로그에 값을 출력하여 디버깅할 수 있습니다.
                Log.d("WriteActivity", "Title: " + writeTitle);
                Log.d("WriteActivity", "Content: " + writeContent);

                // 또는 다른 출력 방법을 선택하여 값을 확인할 수 있습니다.
                // 예: AlertDialog, TextView에 출력, Toast 등

                // 사용자 입력 데이터 수집
                String title = Edit_Text_Title.getText().toString(); // 제목 입력란에서 텍스트 가져오기
                String content = Edit_Text_Content.getText().toString(); // 내용 입력란에서 텍스트 가져오기

                // 결과를 Intent에 담아 반환
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", title); // Intent에 제목 추가
                resultIntent.putExtra("content", content); // Intent에 내용 추가

                setResult(RESULT_OK, resultIntent); // 현재 액티비티의 결과로 설정하고 Intent를 전달
                createPostLauncher.launch(resultIntent); // ActivityResultLauncher 사용



                finish(); // 현재 액티비티 종료

            }
        });

    }


    private void getEdit_text_title() {
        // XML 레이아웃에서 edit_text_title이라는 아이디를 가진 뷰를 찾아와서 Edit_Text_Title 변수에 할당합니다.
        Edit_Text_Title = findViewById(R.id.edit_text_title);
        // Edit_Text_Title에 입력된 텍스트를 문자열로 가져와서 writeTitle 변수에 저장합니다.
        writeTitle = Edit_Text_Title.getText().toString();
    }

    private void getEdit_Text_Content() {
        // XML 레이아웃에서 edit_text_content라는 아이디를 가진 뷰를 찾아와서 Edit_Text_Content 변수에 할당합니다.
        Edit_Text_Content = findViewById(R.id.edit_text_content);
        // Edit_Text_Content에 입력된 텍스트를 문자열로 가져와서 writeContent 변수에 저장합니다.
        writeContent = Edit_Text_Content.getText().toString();
    }



}