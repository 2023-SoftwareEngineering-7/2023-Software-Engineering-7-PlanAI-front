package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planai_front.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class WriteActivity extends AppCompatActivity {

    // 필요한 변수 선언
    private EditText editTitle, editContent;
    private Button finishButton;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // UI 컴포넌트 초기화 및 설정
        initializeViews();

        // 완료 버튼 리스너 설정
        finishButton.setOnClickListener(view -> finishWrite());

        textViewTitle = findViewById(R.id.texttest);

        Button Button = findViewById(R.id.button_wc);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            startActivity(intent);
        });

        // Intent에서 값 가져오기
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String detail = intent.getStringExtra("detail");

            // TextView에 값 설정
            textViewTitle.setText("Title: " + title);
        }
    }

    // UI 컴포넌트 초기화 및 설정
    private void initializeViews() {
        editTitle = findViewById(R.id.edit_text_title);
        editContent = findViewById(R.id.edit_text_content);
        finishButton = findViewById(R.id.button_finish);
    }

    private void finishWrite() {
        // 사용자 입력 데이터 수집
        String title = editTitle.getEditableText().toString();
        String detail = editContent.getEditableText().toString();

        // 서버로 데이터 전송
        sendDataToServer(title, detail);

        // 결과를 Intent에 담아 반환
        Intent resultIntent = new Intent();
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("detail", detail);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    // 서버에 데이터 전송하는 메서드
    private void sendDataToServer(String title, String detail) {
        // 여기에 서버 전송 로직을 구현
    }


        private ArrayList<String> collectTaskData(){
        ArrayList<String> TaskData=new ArrayList<>();
        TaskData.add(editTitle.getEditableText().toString());
        TaskData.add(editContent.getEditableText().toString());
        return TaskData;
        }

}


    Button btn;
    EditText editText;
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_write);

        btn = findViewById(R.id.button_on);
        textView = findViewById(R.id.text_view);


        editText = findViewById(R.id.edit_text);
        super.onCreate(savedInstanceState);
    @Override
    String text;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
                text = editText.getText().toString();

                if (text != null)
                editText.setText("");
                    textView.setText(text);

            public void onClick(View v) {
/*



            }
        });


    }

    @Override
    protected void onPause() { // Activity가 보이지 않을때 값을 저장한다.
        super.onPause();
        saveState();
    }

    @Override
    protected void onStart() {  // Activity가 보이기 시작할때 값을 저장한다.
        super.onStart();
        restoreState();
        if (text != null)
            textView.setText(text);

    }

    protected void saveState() { // 데이터를 저장한다.
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("text", text);

        editor.commit();


    }

    protected void restoreState() {  // 데이터를 복구한다.
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("text"))) {
            text = pref.getString("text", "");
        }

    }

    protected void clearPref() {  // sharedpreference에 쓰여진 데이터 지우기
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        text = null;
        editor.commit();
    }


}*/
