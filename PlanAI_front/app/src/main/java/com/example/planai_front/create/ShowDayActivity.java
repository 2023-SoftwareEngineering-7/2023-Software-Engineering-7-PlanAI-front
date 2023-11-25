package com.example.planai_front.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.MaterialCalendarActivity;
import com.example.planai_front.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

//****need to copy THIS to Rana's file**///
/*
Intented from MaterialCalendarActivity - materialCalendarView.setOnDateChangedListener
1) Show daily schedule
2) Create schedule & task through button
3) bottom navigation bar available
 */
public class ShowDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<Schedule> scheduleList;

    private FloatingActionButton addEventFabButton, addScheduleButton, addTaskButton, etcButton;
    private boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //show day_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_main_v2);

        Intent dateIntent = getIntent();
        String todayDate = dateIntent.getStringExtra("date");
//        TextView showDate = (TextView) findViewById(R.id.date_banner);
//        showDate.setText(todayDate);    //remove.....

        //setting banner: month, date, date of week

        String[] parts = todayDate.split("-");

        String todayYear = parts[0]; // 년
        String todayMonth = parts[1]; // 월
        String todayDay = parts[2]; // 일

        String monthName = "";
        switch (todayMonth) {
            case "01":
                monthName = "January";
                break;
            case "02":
                monthName = "February";
                break;
            case "03":
                monthName = "March";
                break;
            case "04":
                monthName = "April";
                break;
            case "05":
                monthName = "May";
                break;
            case "06":
                monthName = "June";
                break;
            case "07":
                monthName = "July";
                break;
            case "08":
                monthName = "August";
                break;
            case "09":
                monthName = "September";
                break;
            case "10":
                monthName = "October";
                break;
            case "11":
                monthName = "November";
                break;
            case "12":
                monthName = "December";
                break;
            default:
                monthName = "Invalid month";
                break;
        }

        TextView show = (TextView) findViewById(R.id.todayMonthView);
        show.setText(monthName);

        TextView show2 = (TextView) findViewById(R.id.todayDateNumView);
        show2.setText(todayDay);

        String todayDayText = dateIntent.getStringExtra("fullDateInfo");
        TextView show3 = (TextView) findViewById(R.id.todayDateTextView);
        show3.setText(todayDayText.substring(0, 3));

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 목록 초기화 및 어댑터 설정
        scheduleList = new ArrayList<>();
        adapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(adapter);

        // 데이터 로딩
        loadSchedules(todayDate);


//branch for Calendal - calendar2: 23.11.25 created


        //schedule&task creating button
        // begin HERE
        //https://ghj1001020.tistory.com/9
        // I think this will be helpful
        // If you are working on this page, please let me know!


        addEventFabButton = findViewById(R.id.fabMain);
        addScheduleButton = findViewById(R.id.schedulebutton);
        addTaskButton = findViewById(R.id.taskbutton);
        etcButton = findViewById(R.id.fabOption1);

        addEventFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                    addScheduleButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ShowDayActivity.this, createSchedule.class);
                            intent.putExtra("date",todayDate);
                            startActivity(intent);
                        }
                    });

                    // 두 번째 FAB 리스너
                    addTaskButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ShowDayActivity.this, createTask.class);
                            intent.putExtra("date",todayDate);
                            startActivity(intent);
                        }
                    });

                    // 세 번째 FAB 리스너
                    etcButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ShowDayActivity.this, Event.class); //Evnet.class is just example not to make error!!!
                            intent.putExtra("date",todayDate);
                            startActivity(intent);
                        }
                    });
                } else {
                    closeFABMenu();
                }
            }
        });








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

    private void loadSchedules(String todayDate) {
// 예시 데이터 추가
//        //public Schedule(String summary, String start, String end, String description, boolean open_schedule, String collaborators) {
        if(todayDate.equals("2023-11-26")) {
            scheduleList.add(new Schedule("Meeting with Team"+todayDate+"yyy", "1Pm", "3pm", "hello world", "colll"));
            scheduleList.add(new Schedule("Call with Client", "5pm", "6pm", "hello world", "coll coll cool"));
            scheduleList.add(new Schedule("Date with friends", "7pm", "10pm", "hello world", "coll coll cool"));
            scheduleList.add(new Schedule("Date with friends", "7pm", "10pm", "hello world", "coll coll cool"));
            scheduleList.add(new Schedule("Date with friends", "7pm", "10pm", "hello world", "coll coll cool"));
//        // 어댑터에 데이터가 변경됨을 알림
            adapter.notifyDataSetChanged();
        }
    }

    private void showFABMenu(){
        isFABOpen=true;
        addTaskButton.setVisibility(View.VISIBLE);
        addScheduleButton.setVisibility(View.VISIBLE);
        etcButton.setVisibility(View.VISIBLE);

        // 여기에 각 FAB에 대한 애니메이션 추가 가능
    }

    private void closeFABMenu(){
        isFABOpen=false;
        addTaskButton.setVisibility(View.INVISIBLE);
        addScheduleButton.setVisibility(View.INVISIBLE);
        etcButton.setVisibility(View.INVISIBLE);

        // 여기에 각 FAB에 대한 애니메이션 추가 가능
    }



}



