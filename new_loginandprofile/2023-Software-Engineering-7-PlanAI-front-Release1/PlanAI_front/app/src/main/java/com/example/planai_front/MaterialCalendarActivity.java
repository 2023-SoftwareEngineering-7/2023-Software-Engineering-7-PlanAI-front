package com.example.planai_front;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.planai_front.Profile.SettingActivity;
import com.example.planai_front.create.ShowDayActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;

/*
Intented from MainActivity
Intented from bottomNavigation - id navigation_calendar
1) Show materialCalendar
2) date selection available
3) bottom navigation bar available
 */
public class MaterialCalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materialcalendar_main);

        setupCalendarView();
        setupBottomNavigationBar();
    }

    private void setupCalendarView() {
        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget,
                                       @NonNull CalendarDay date,
                                       boolean selected) {
                Intent intent = new Intent(MaterialCalendarActivity.this, ShowDayActivity.class);
                intent.putExtra("date", date.getDate().toString());
                intent.putExtra("fullDateInfo", date.getDate().getDayOfWeek().toString());
                startActivity(intent);
            }
        });
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_calendar) {
                //캘린더 화면으로 돌아옴
                startActivity(new Intent(MaterialCalendarActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                //친구목록 화면
                startActivity(new Intent(MaterialCalendarActivity.this, FriendlistActivity.class));


            } else if (item.getItemId() == R.id.navigation_home){
                //오늘 일정으로 이동
                LocalDate today = LocalDate.now();
                Intent homeIntent = new Intent(MaterialCalendarActivity.this, ShowDayActivity.class);
                homeIntent.putExtra("date", today.toString());
                homeIntent.putExtra("fullDateInfo", today.getDayOfWeek().toString());
                startActivity(homeIntent);

            }else if (item.getItemId() == R.id.navigation_post){
                //게시판으로 이동
                startActivity(new Intent(MaterialCalendarActivity.this, BoardActivity.class));


            }else if (item.getItemId() == R.id.navigation_setting) {
                //프로필 세팅으로 이동
                startActivity(new Intent(MaterialCalendarActivity.this, SettingActivity.class));

            }
            return true;
        });
    }
}
