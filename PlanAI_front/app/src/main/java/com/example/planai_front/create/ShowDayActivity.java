package com.example.planai_front.create;

import static com.example.planai_front.Server.RetrofitClient.PlanAI_URL;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import com.example.planai_front.Server.ApiService;
import com.example.planai_front.Server.RetrofitClient;
import com.example.planai_front.Server.Server_ScheduleDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Server Test Branch(23.11.27)
 */

public class ShowDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<Schedule> scheduleList;
    private String scheduleId, scheduleSummary, scheduleStartDate, scheduleStartTime, scheduleEndDate, scheduleEndTime, scheduleTag, scheduleDescription;

    private FloatingActionButton addEventFabButton, addScheduleButton, addTaskButton, etcButton;
    private boolean isFABOpen = false;

    //선택 날짜, 유저 id 받아오기
    private String todayDate;
    //private
    private Long userId;

    // 전역 변수로 scheduleMap 선언
    private HashMap<String, ArrayList<Schedule>> scheduleMap = new HashMap<>();

    // ActivityResultLauncher 객체 선언. 다른 액티비티에서 결과 받아오는데 사용.
    private final ActivityResultLauncher<Intent> createScheduleLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        // 결과 코드 OK이고, 반환된 데이터 null 아닐 경우에만 처리.
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // 결과 데이터 Intent 객체로 받아옴.
                            Intent data = result.getData();
                            if (data != null) {
                                //스케줄 id 생성
                                scheduleId = userId+data.getStringExtra(("startDate"));
                                // 인텐트에서 스케줄 관련 데이터 추출.
                                scheduleSummary = data.getStringExtra("summary");
                                scheduleStartDate = data.getStringExtra("startDate");
                                scheduleStartTime = data.getStringExtra("startTime");
                                scheduleEndDate = data.getStringExtra("endDate");
                                scheduleEndTime = data.getStringExtra("endTime");
                                scheduleTag = data.getStringExtra("tag");
                                scheduleDescription = data.getStringExtra("description");

                                // 추출한 데이터로 새 Schedule 객체 생성.
                                Schedule newSchedule = new Schedule(scheduleId, scheduleSummary, scheduleStartDate,scheduleStartTime, scheduleEndDate, scheduleEndTime,scheduleTag,scheduleDescription, "");
                                // scheduleMap에서 해당 날짜에 해당하는 스케줄 리스트 가져오거나 새로 생성.
                                ArrayList<Schedule> schedules = scheduleMap.computeIfAbsent(scheduleStartDate, k -> new ArrayList<>());
                                // 새 스케줄 리스트에 추가.
                                schedules.add(newSchedule);

                                // 현재 액티비티 인텐트에 있는 'date'와 스케줄 시작 날짜 같으면,
                                // 해당 스케줄 현재 액티비티 스케줄 리스트에 추가하고 어댑터로 리스트뷰 갱신.
                                if (scheduleStartDate.equals(getIntent().getStringExtra("date"))) {
                                    scheduleList.add(newSchedule);
                                    adapter.notifyDataSetChanged();
                                }
                                Log.d("Calendar", "Calendar updated");

                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                List<String> tagList = new ArrayList<>();
                                tagList.add(scheduleTag);
                                Server_ScheduleDTO serverScheduleDTO = new Server_ScheduleDTO(scheduleSummary, scheduleStartDate, scheduleEndDate, scheduleDescription, userId, tagList );
                                Log.d("Server!!", "Response 1");
                                ApiService apiService = RetrofitClient.getClient(PlanAI_URL).create(ApiService.class);
                                Log.d("Server!!", "Response 2");
                                Call<Server_ScheduleDTO> call = apiService.createSchedule(serverScheduleDTO);
                                Log.d("Server!!", "Response 3");
                                call.enqueue(new Callback<Server_ScheduleDTO>() {
                                    @Override
                                    public void onResponse(Call<Server_ScheduleDTO> call, Response<Server_ScheduleDTO> response) {
                                        Log.d("Server!!", "Response 4");
                                        if (response.isSuccessful()) {
                                            //성공적인 응답 처리
                                            Log.d("Server!!", "Response OK");

                                        } else {
                                            // 서버 에러 처리
                                            Log.d("Server!!", "Server Error1");
                                            int statusCode = response.code();
                                            Log.d("Server!!", "Response Code: " + statusCode);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Server_ScheduleDTO> call, Throwable t) {
                                        // 네트워크 에러 처리
                                        Log.d("Server!!", "Server Error2");
                                        Log.e("Server!!", "Network Error", t);
                                    }
                                });
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
        todayDate = dateIntent.getStringExtra("date");
        //TODO: userId = dateIntent.getStringExtra("userId");
        userId  = 1111L;

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