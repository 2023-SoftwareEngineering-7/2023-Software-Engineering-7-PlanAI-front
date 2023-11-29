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


public class WriteActivity extends AppCompatActivity {

    Button btn;
    EditText editText;
    TextView textView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        btn = findViewById(R.id.button_on);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);

        Button Button = (Button) findViewById(R.id.button_wc);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            startActivity(intent);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();

                if (text != null)
                    textView.setText(text);
                editText.setText("");

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


}
