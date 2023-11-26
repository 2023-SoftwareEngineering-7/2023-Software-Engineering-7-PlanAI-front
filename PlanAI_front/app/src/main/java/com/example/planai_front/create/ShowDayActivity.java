package com.example.planai_front.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.BoardActivity;
import com.example.planai_front.FriendlistActivity;
import com.example.planai_front.MaterialCalendarActivity;
import com.example.planai_front.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Server Test Branch(23.11.27)
 */

public class ShowDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<Schedule> scheduleList;
    private String scheduleSummary, scheduleStartDate, scheduleStartTime, scheduleEndDate, scheduleEndTime, scheduleTag, scheduleDescription;

    private FloatingActionButton addEventFabButton, addScheduleButton, addTaskButton, etcButton;
    private boolean isFABOpen = false;

    // 전역 변수로 scheduleMap 선언
    private HashMap<String, ArrayList<Schedule>> scheduleMap = new HashMap<>();

    // ActivityResultLauncher 초기화
    private final ActivityResultLauncher<Intent> createScheduleLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // 결과 처리
                            Intent data = result.getData();
                            if (data != null) {

                                scheduleSummary = data.getStringExtra("summary");
                                scheduleStartDate = data.getStringExtra("startDate");
                                scheduleStartTime = data.getStringExtra("startTime");
                                scheduleEndDate= data.getStringExtra("endDate");
                                scheduleEndTime = data.getStringExtra("endTime");
                                scheduleTag = data.getStringExtra("tag");
                                scheduleDescription = data.getStringExtra("description");

                                Schedule newSchedule = new Schedule(scheduleSummary, scheduleStartDate, scheduleEndDate, scheduleDescription, "");
                                ArrayList<Schedule> schedules = scheduleMap.computeIfAbsent(scheduleStartDate, k -> new ArrayList<>());
                                schedules.add(newSchedule);

                                if (scheduleStartDate.equals(getIntent().getStringExtra("date"))) {
                                    scheduleList.add(newSchedule);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_main_v2);

        // ScheduleApplication 인스턴스를 사용하여 scheduleMap 가져오기
        scheduleMap = ScheduleApplication.getInstance().getScheduleMap();
        scheduleList = new ArrayList<>(); // scheduleList 초기화

        setupRecyclerView();
        setupDateBanner();
        setupRecyclerView();
        setupFloatingActionButtons();
        setupBottomNavigationBar();

        // 현재 날짜에 해당하는 스케줄 로드
        String todayDate = getIntent().getStringExtra("date");
        if (todayDate != null) {
            loadSchedules(todayDate);
        }

    }

    private void setupDateBanner() {
        Intent dateIntent = getIntent();
        String todayDate = dateIntent.getStringExtra("date");

        String[] parts = todayDate.split("-");
        String todayYear = parts[0];
        String todayMonth = parts[1];
        String todayDay = parts[2];

        String monthName = getMonthName(todayMonth);

        TextView show = findViewById(R.id.todayMonthView);
        show.setText(monthName);

        TextView show2 = findViewById(R.id.todayDateNumView);
        show2.setText(todayDay);

        String todayDayText = dateIntent.getStringExtra("fullDateInfo");
        TextView show3 = findViewById(R.id.todayDateTextView);
        show3.setText(todayDayText.substring(0, 3));
    }

    private String getMonthName(String monthNumber) {
        switch (monthNumber) {
            case "01": return "January";
            case "02": return "February";
            case "03": return "March";
            case "04": return "April";
            case "05": return "May";
            case "06": return "June";
            case "07": return "July";
            case "08": return "August";
            case "09": return "September";
            case "10": return "October";
            case "11": return "November";
            case "12": return "December";
            default: return "Invalid month";
        }
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(adapter);

    }

    private void loadSchedules(String todayDate) {
        ArrayList<Schedule> schedulesForToday = scheduleMap.getOrDefault(todayDate, new ArrayList<>());
        scheduleList.clear();
        scheduleList.addAll(schedulesForToday);
        adapter.notifyDataSetChanged();

    }

    private void setupFloatingActionButtons() {
        addEventFabButton = findViewById(R.id.fabMain);
        addScheduleButton = findViewById(R.id.schedulebutton);
        addTaskButton = findViewById(R.id.taskbutton);
        etcButton = findViewById(R.id.fabOption1);

        addEventFabButton.setOnClickListener(view -> {
            if (!isFABOpen) {
                showFABMenu();
            } else {
                closeFABMenu();
            }
        });

        addScheduleButton.setOnClickListener(view -> {
            Intent intent = new Intent(ShowDayActivity.this, CreateScheduleActivity.class);
            intent.putExtra("date", getIntent().getStringExtra("date"));
            createScheduleLauncher.launch(intent); // ActivityResultLauncher 사용
            overridePendingTransition(R.anim.slide_up, R.anim.stay); // 애니메이션 적용


        });

        addTaskButton.setOnClickListener(view -> {
            Intent intent = new Intent(ShowDayActivity.this, CreateTaskActivity.class);
            intent.putExtra("date", getIntent().getStringExtra("date"));
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up, R.anim.stay); // 아래에서 올라오는 애니메이션 적용
        });


        // 추가 FAB 리스너 설정
    }

    private void showFABMenu() {
        isFABOpen = true;
        addTaskButton.setVisibility(View.VISIBLE);
        addScheduleButton.setVisibility(View.VISIBLE);
        etcButton.setVisibility(View.VISIBLE);
        // FAB 애니메이션 추가 가능
    }

    private void closeFABMenu() {
        isFABOpen = false;
        addTaskButton.setVisibility(View.INVISIBLE);
        addScheduleButton.setVisibility(View.INVISIBLE);
        etcButton.setVisibility(View.INVISIBLE);
        // FAB 애니메이션 추가 가능
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_calendar) {
                startActivity(new Intent(ShowDayActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                startActivity(new Intent(ShowDayActivity.this, FriendlistActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_home){
                startActivity(new Intent(ShowDayActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_post){
                startActivity(new Intent(ShowDayActivity.this, BoardActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_setting) {
                startActivity(new Intent(ShowDayActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현
            }
            return true;
        });
    }
}