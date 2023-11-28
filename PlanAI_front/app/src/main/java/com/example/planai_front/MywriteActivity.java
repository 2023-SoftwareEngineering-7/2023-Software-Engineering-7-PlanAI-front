package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MywriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywrite);

        Button Button = (Button) findViewById(R.id.button44);
        Button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            startActivity(intent);
        });
    }
}