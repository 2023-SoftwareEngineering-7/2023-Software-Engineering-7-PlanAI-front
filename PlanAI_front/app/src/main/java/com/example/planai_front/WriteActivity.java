package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {

    private EditText Edit_Text_Title, Edit_Text_Content;

    private String writeTitle, writeContent;
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