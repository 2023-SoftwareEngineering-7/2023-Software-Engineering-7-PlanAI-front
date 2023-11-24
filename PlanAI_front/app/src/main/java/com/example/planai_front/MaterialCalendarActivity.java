package com.example.planai_front;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.planai_front.create.ShowDayActivity;
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
public class MaterialCalendarActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materialcalendar_main);

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.setOnDateChangedListener((new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //Toast.makeText(MaterialCalendarActivity.this, ""+date,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MaterialCalendarActivity.this, ShowDayActivity.class);
                intent.putExtra("date",date.getDate().toString());
                startActivity(intent);

            }
        }));

    }
}
