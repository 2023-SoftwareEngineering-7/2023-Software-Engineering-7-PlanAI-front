package com.example.planai_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Button1 = (Button) findViewById(R.id.button311);
        Button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FriendlistActivity.class);
            startActivity(intent);
        });



        Button calendarButton = findViewById(R.id.testCalendar);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
