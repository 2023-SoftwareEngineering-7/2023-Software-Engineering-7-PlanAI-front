package com.example.planai_front.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.planai_front.MaterialCalendarActivity;
import com.example.planai_front.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
Intented from MaterialCalendarActivity - materialCalendarView.setOnDateChangedListener
1) Show daily schedule
2) Create schedule & task through button
3) bottom navigation bar available
 */
public class ShowDayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //show day_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_main);

        Intent dateIntent = getIntent();
        String todayDate = dateIntent.getStringExtra("date");
        TextView showDate = (TextView) findViewById(R.id.date_banner);
        showDate.setText(todayDate);


//testshowdayactivity with MySQL






        //schedule&task creating button
        // begin HERE
        //https://ghj1001020.tistory.com/9
        // I think this will be helpful
        // If you are working on this page, please let me know!

        //하단 메뉴 바
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_calendar) {
                    Intent intent = new Intent(ShowDayActivity.this, MaterialCalendarActivity.class);
                    startActivity(intent);

                    /*case R.id.menu_item2:
                        selectedFragment = new Fragment2();
                        break;
                    // 다른 메뉴 아이템에 대한 처리
                    */

                }
                return true;
            }
        });

    }

}
