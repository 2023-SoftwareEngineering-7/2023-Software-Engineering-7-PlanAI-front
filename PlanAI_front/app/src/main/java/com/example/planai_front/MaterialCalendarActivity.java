package com.example.planai_front;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.planai_front.create.ShowDayActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
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
                startActivity(new Intent(MaterialCalendarActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                startActivity(new Intent(MaterialCalendarActivity.this, FriendlistActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_home){
                startActivity(new Intent(MaterialCalendarActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_post){
                startActivity(new Intent(MaterialCalendarActivity.this, BoardActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_setting) {
                startActivity(new Intent(MaterialCalendarActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현
            }
            return true;
        });
    }
}
