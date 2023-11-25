package com.example.planai_front.create;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planai_front.R;

//전달받은 todayDate(2023-11-22 형식을 가짐)을 id로 해서 서버에 Task 등록
//popup_layout을 띄움(아래에서 올라오는 pop-up 또는 popup 처럼 생긴 새로운 xml 페이지. 편한걸로 할것!!!)
//뒤로가기 버튼을 통해 ShowDayActivity로 돌아감

public class createTask extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        //show day_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);

        Intent dateIntent = getIntent();
        String todayDate = dateIntent.getStringExtra("date");
    }
}

