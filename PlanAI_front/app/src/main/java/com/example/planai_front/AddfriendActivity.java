package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddfriendActivity extends AppCompatActivity {
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);

        EditText editText_find = findViewById(R.id.editText_find);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 인플레이트할 레이아웃 가져오기
        View addfriend_item = inflater.inflate(R.layout.addfriend_item, null);
        View friendlist = inflater.inflate(R.layout.activity_friend_list, null);

        TextView FriendName = addfriend_item.findViewById(R.id.name_item);


        Button Button1 = (Button) findViewById(R.id.addfriend_button);
        Button1.setOnClickListener(view -> {

            String name = editText_find.getText().toString();
            FriendName.setText(name);
            Log.d("name", "Title: " + FriendName);
        // 부모 레이아웃 가져오기
            LinearLayout showfriend = findViewById(R.id.showfriend);
        // 커스텀 레이아웃을 부모 레이아웃에 추가
            showfriend.addView(addfriend_item);
        });


        Button Button = (Button) findViewById(R.id.buttonx);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FriendlistActivity.class);
            startActivity(intent);
        });
    }
}