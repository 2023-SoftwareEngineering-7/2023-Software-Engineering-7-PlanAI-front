package com.example.planai_front.create;

import static com.example.planai_front.Server.RetrofitClient.PlanAI_URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.example.planai_front.Server.Server_TaskDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ShowDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private ScheduleAdapter adapter;
    private MainCalendarApplication mainCalendarApplication;
    private List<Schedule> scheduleList;
    private String scheduleId, scheduleSummary, scheduleStartDate, scheduleStartTime, scheduleEndDate, scheduleEndTime, scheduleTag, scheduleDescription;
    private List<String> Server_tagList = new ArrayList<>();

    private String taskId, taskSummary, taskDescription,taskDeadLineDate, taskDeadLineTime,taskTag, taskPriority;
    private Priority Server_taskPriority;
    private List<Task> taskList;
    private List<String> tagList;


    private FloatingActionButton addEventFabButton, addScheduleButton, addTaskButton, etcButton;
    private boolean isFABOpen = false;

    //선택 날짜, 유저 id 받아오기
    private String todayDate;
    //private
    private Long userId;

    // 전역 변수로 scheduleMap 선언
    private HashMap<String, ArrayList<Schedule>> scheduleMap = new HashMap<>();
    private String Server_scheduleStartDate;
    private String Server_scheduleEndDate;

    private HashMap<String, ArrayList<Task>> TaskMap = new HashMap<>();
    private String Server_taskDeadLineDate;

    private CombinedAdapter combinedAdapter;
    private List<CalendarItem> calendarItems = new ArrayList<>(); // 초기화 추가


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
                                    //adapter.notifyDataSetChanged();
                                    combinedAdapter.notifyDataSetChanged();
                                }
                                Log.d("Calendar", "Calendar updated");

                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                Log.e("Server!!", scheduleTag);
                                if (Server_tagList == null) {
                                    Server_tagList = new ArrayList<>();

                                }
                                Server_tagList.add(scheduleTag);

                                if(Server_tagList.isEmpty()){
                                    Log.e("Server!!", "empty tagList");
                                }else{
                                    Log.e("Server!!", Server_tagList.get(0));
                                }

                                Server_scheduleStartDate = scheduleStartDate+"T"+scheduleStartTime;
                                Server_scheduleEndDate = scheduleEndDate+"T"+scheduleEndTime;

                                Server_ScheduleDTO serverScheduleDTO = new Server_ScheduleDTO(scheduleSummary, Server_scheduleStartDate, Server_scheduleEndDate, scheduleDescription, userId, Server_tagList );
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


    // ActivityResultLauncher 객체 선언. 다른 액티비티에서 결과 받아오는데 사용.
    private final ActivityResultLauncher<Intent> createTaskLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        // 결과 코드 OK이고, 반환된 데이터 null 아닐 경우에만 처리.
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // 결과 데이터 Intent 객체로 받아옴.
                            Intent data = result.getData();
                            if (data != null) {
                                //스케줄 id 생성
                                taskId = userId+data.getStringExtra(("startDate"));
                                // 인텐트에서 스케줄 관련 데이터 추출.
                                taskSummary = data.getStringExtra("summary");
                                taskDescription = data.getStringExtra("description");
                                taskDeadLineDate = data.getStringExtra("deadLineDate");
                                taskDeadLineTime = data.getStringExtra("deadLineTime");
                                taskTag = data.getStringExtra("tag");
                                taskPriority = data.getStringExtra("priority");

                                // 추출한 데이터로 새 task 객체 생성.
                                Task newTask = new Task(taskSummary, taskDescription, taskDeadLineDate, taskDeadLineTime, taskTag, taskPriority);
                                // TaskMap에서 해당 날짜에 해당하는 Task 리스트 가져오거나 새로 생성.
                                ArrayList<Task> tasks = TaskMap.computeIfAbsent(taskDeadLineDate, k -> new ArrayList<>());
                                // 새 Task 리스트에 추가.
                                tasks.add(newTask);

                                // 현재 액티비티 인텐트에 있는 'date'와 Task 시작 날짜 같으면,
                                // 해당 Task 현재 액티비티 스케줄 리스트에 추가하고 어댑터로 리스트뷰 갱신.
                                if (taskDeadLineDate.equals(getIntent().getStringExtra("date"))) {
                                    taskList.add(newTask);
                                    //adapter.notifyDataSetChanged();
                                    combinedAdapter.notifyDataSetChanged();
                                }
                                Log.d("Calendar", "Calendar updated");

                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                Log.e("Server!!", taskTag);

                                //null 여부에 관계없이 tagList 초기화
                                Server_tagList = new ArrayList<>();

                                Server_tagList.add(taskTag);

                                if(Server_tagList.isEmpty()){
                                    Log.e("Server!!", "empty tagList");
                                }else{
                                    Log.e("Server!!", Server_tagList.get(0));
                                }

                                Server_taskDeadLineDate = taskDeadLineDate+"T"+taskDeadLineTime;
                                Server_taskPriority = Priority.valueOf(taskPriority);
                                Server_TaskDTO serverTaskDTO = new Server_TaskDTO(taskSummary, taskDescription, Server_taskDeadLineDate, Server_taskPriority, userId, Server_tagList );
                                Log.d("Server!!", "Response 11");
                                ApiService apiService = RetrofitClient.getClient(PlanAI_URL).create(ApiService.class);
                                Log.d("Server!!", "Response 22");
                                Call<Server_TaskDTO> call = apiService.createTask(serverTaskDTO);
                                Log.d("Server!!", "Response 33");
                                call.enqueue(new Callback<Server_TaskDTO>() {
                                    @Override
                                    public void onResponse(Call<Server_TaskDTO> call, Response<Server_TaskDTO> response) {
                                        Log.d("Server!!", "Response 44");
                                        if (response.isSuccessful()) {
                                            //성공적인 응답 처리
                                            Log.d("Server!!", "Response oOK");

                                        } else {
                                            // 서버 에러 처리
                                            Log.d("Server!!", "Server Error11");
                                            int statusCode = response.code();
                                            Log.d("Server!!", "Response Code1: " + statusCode);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Server_TaskDTO> call, Throwable t) {
                                        // 네트워크 에러 처리
                                        Log.d("Server!!", "Server Error22");
                                        Log.e("Server!!", "Network Error22", t);
                                    }
                                });
                            }
                        }
                    }
            );

    ////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_main_v2);
        mainCalendarApplication = MainCalendarApplication.getInstance();

        // ScheduleApplication 및 TaskApplication 인스턴스에서 데이터 맵 가져오기
//        scheduleMap = ScheduleApplication.getInstance().getScheduleMap();
//        TaskMap = TaskApplication.getInstance().getTaskMap();
//        scheduleMap = MainCalendarApplication.getInstance().getScheduleMap();
//        TaskMap = MainCalendarApplication.getInstance().getTaskMap();

        // 리스트 초기화
        scheduleList = new ArrayList<>();
        taskList = new ArrayList<>();

        // 나머지 UI 설정
        setupRecyclerView();
        setupDateBanner();
        setupFloatingActionButtons();
        setupBottomNavigationBar();

        // 현재 날짜에 해당하는 스케줄 및 태스크 로드
        todayDate = getIntent().getStringExtra("date");
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
///////////////////////////////////
    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendarItems = new ArrayList<>();
        combinedAdapter = new CombinedAdapter(calendarItems);
        recyclerView.setAdapter(combinedAdapter);
    }

    private void loadSchedules(String todayDate) {
//        List<Schedule> schedulesForToday = scheduleMap.getOrDefault(todayDate, new ArrayList<>());
//        List<Task> tasksForToday = TaskMap.getOrDefault(todayDate, new ArrayList<>());
        List<Schedule> schedulesForToday = mainCalendarApplication.getScheduleMap().getOrDefault(todayDate, new ArrayList<>());
        List<Task> tasksForToday = mainCalendarApplication.getTaskMap().getOrDefault(todayDate, new ArrayList<>());
        calendarItems.clear();
        calendarItems.addAll(schedulesForToday);
        calendarItems.addAll(tasksForToday);
        combinedAdapter.notifyDataSetChanged();
    }



//    private void setupRecyclerView() {
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new ScheduleAdapter(scheduleList);
//        recyclerView.setAdapter(adapter);
//
//    }
//
//    private void loadSchedules(String todayDate) {
//        ArrayList<Schedule> schedulesForToday = scheduleMap.getOrDefault(todayDate, new ArrayList<>());
//        scheduleList.clear();
//        scheduleList.addAll(schedulesForToday);
//        adapter.notifyDataSetChanged();
//
//    }
/////////////////////////////////////////////////////
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
            createTaskLauncher.launch(intent); // ActivityResultLauncher 사용
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