package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddfriendActivity extends AppCompatActivity {
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);

        Button Button = (Button) findViewById(R.id.buttonx);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FriendlistActivity.class);
            startActivity(intent);
        });
    }
}