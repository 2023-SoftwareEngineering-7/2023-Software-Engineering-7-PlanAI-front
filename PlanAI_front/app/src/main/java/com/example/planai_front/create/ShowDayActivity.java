package com.example.planai_front.create;

import static com.example.planai_front.Server.RetrofitClient.PlanAI_URL;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.fasterxml.jackson.core.JsonFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;


import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ShowDayActivity extends AppCompatActivity {

    //    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private RecyclerView scheduleRecyclerShowDay;
    private TaskAdapter taskAdapter;
    private RecyclerView taskRecyclerShowDay;
    private MainCalendarApplication mainCalendarApplication;
    private List<Schedule> todayScheduleList;
    private String scheduleId, scheduleSummary, scheduleStartDate, scheduleStartTime, scheduleEndDate, scheduleEndTime, scheduleTag, scheduleDescription;
    private List<String> Server_tagList = new ArrayList<>();

    private String taskId, taskSummary, taskDescription, taskDeadLineDate, taskDeadLineTime, taskTag, taskPriority;
    private Priority Server_taskPriority;
    private List<Task> todayTaskList;

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


    private GoogleAccountCredential mCredential;
    private static final int REQUEST_ACCOUNT_PICKER = 1000;
    private static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    private static final String[] SCOPES = {CalendarScopes.CALENDAR};
    private static final String PREF_ACCOUNT_NAME = "accountName";

    //Google Calendar API에 접근하기 위해 사용되는 구글 캘린더 API 서비스 객체
    private com.google.api.services.calendar.Calendar mService = null;

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
                                scheduleId = userId + data.getStringExtra(("startDate"));
                                // 인텐트에서 스케줄 관련 데이터 추출.
                                scheduleSummary = data.getStringExtra("summary");
                                scheduleStartDate = data.getStringExtra("startDate");
                                scheduleStartTime = data.getStringExtra("startTime");
                                scheduleEndDate = data.getStringExtra("endDate");
                                scheduleEndTime = data.getStringExtra("endTime");
                                scheduleTag = data.getStringExtra("tag");
                                scheduleDescription = data.getStringExtra("description");

                                // 추출한 데이터로 새 Schedule 객체 생성.
                                Schedule newSchedule = new Schedule(scheduleId, scheduleSummary, scheduleStartDate, scheduleStartTime, scheduleEndDate, scheduleEndTime, scheduleTag, scheduleDescription);
                                // scheduleMap에서 해당 날짜에 해당하는 스케줄 리스트 가져오거나 새로 생성.
                                ArrayList<Schedule> newScheduleDateList = scheduleMap.computeIfAbsent(scheduleStartDate, k -> new ArrayList<>());
                                // 새 스케줄 리스트에 추가.
                                newScheduleDateList.add(newSchedule);

                                // 현재 액티비티 인텐트에 있는 'date'와 스케줄 시작 날짜 같으면,
                                // 해당 스케줄 현재 액티비티 스케줄 리스트에 추가하고 어댑터로 리스트뷰 갱신.
                                if (scheduleStartDate.equals(getIntent().getStringExtra("date"))) {
                                    todayScheduleList.add(newSchedule);
                                    scheduleAdapter.notifyDataSetChanged();
                                }
                                Log.d("Calendar", "Calendar updated");

                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                Log.e("Server!!", scheduleTag);
                                if (Server_tagList == null) {
                                    Server_tagList = new ArrayList<>();

                                }
                                Server_tagList.add(scheduleTag);

                                if (Server_tagList.isEmpty()) {
                                    Log.e("Server!!", "empty tagList");
                                } else {
                                    Log.e("Server!!", Server_tagList.get(0));
                                }

                                Server_scheduleStartDate = scheduleStartDate + "T" + scheduleStartTime;
                                Server_scheduleEndDate = scheduleEndDate + "T" + scheduleEndTime;

                                Server_ScheduleDTO serverScheduleDTO = new Server_ScheduleDTO(scheduleSummary, Server_scheduleStartDate, Server_scheduleEndDate, scheduleDescription, userId, Server_tagList);
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
                                            // Google 캘린더에 이벤트 추가
                                            if (mCredential.getSelectedAccountName() != null) {
                                                new AddScheduleToCalendarTask(mCredential, response.body()).execute();
                                            } else {
                                                // 적절한 사용자 인증이 완료되지 않았을 경우 처리
                                                chooseGoogleAccount();
                                                Log.e("Server!!", "GoogleCalendar: Credential is null. User authentication required.");
                                            }
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
                                //Google 캘린더 연동
                                Log.d("Server!!", "google calendar");
                                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                                        this, Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
                                // Google 캘린더에 이벤트 추가
                                //new AddScheduleToCalendarTask(mCredential, serverScheduleDTO).execute();

                            }
                        }
                        // Google Calendar에서 최신 일정 불러오기
                        new FetchSchedulesFromCalendarTask().execute();
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
                                taskId = userId + data.getStringExtra(("startDate"));
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
                                ArrayList<Task> newTaskDateList = TaskMap.computeIfAbsent(taskDeadLineDate, k -> new ArrayList<>());
                                // 새 Task 리스트에 추가.
                                newTaskDateList.add(newTask);

                                // 현재 액티비티 인텐트에 있는 'date'와 Task 시작 날짜 같으면,
                                // 해당 Task 현재 액티비티 스케줄 리스트에 추가하고 어댑터로 리스트뷰 갱신.
                                if (taskDeadLineDate.equals(getIntent().getStringExtra("date"))) {
                                    todayTaskList.add(newTask);
                                    //adapter.notifyDataSetChanged();]
                                    /*combinedAdapter!!!*/
                                    //combinedAdapter.notifyDataSetChanged();
                                    taskAdapter.notifyDataSetChanged();
                                }
                                Log.d("Calendar", "Calendar updated");

                                //서버 전송 준비
                                //TODO: 서버 전송용 Schedule클래스와 저장하는 Schedule클래스 일치시키기
                                Log.e("Server!!", taskTag);

                                //null 여부에 관계없이 tagList 초기화
                                Server_tagList = new ArrayList<>();

                                Server_tagList.add(taskTag);

                                if (Server_tagList.isEmpty()) {
                                    Log.e("Server!!", "empty tagList");
                                } else {
                                    Log.e("Server!!", Server_tagList.get(0));
                                }

                                Server_taskDeadLineDate = taskDeadLineDate + "T" + taskDeadLineTime;
                                Server_taskPriority = Priority.valueOf(taskPriority);
                                Server_TaskDTO serverTaskDTO = new Server_TaskDTO(taskSummary, taskDescription, Server_taskDeadLineDate, Server_taskPriority, userId, Server_tagList);
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
                                            // Google 캘린더에 이벤트 추가
                                            if (mCredential.getSelectedAccountName() != null) {
                                                new AddTaskToCalendarTask(mCredential, response.body()).execute();
                                            } else {
                                                // 적절한 사용자 인증이 완료되지 않았을 경우 처리
                                                chooseGoogleAccount();
                                                Log.e("Server!!", "GoogleCalendar: Credential is null. User authentication required.");
                                            }

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
                                Log.d("Server!!", "google calendar");
                                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                                        this, Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
                                // Google 캘린더에 이벤트 추가
                                new AddTaskToCalendarTask(mCredential, serverTaskDTO).execute();
                            }
                        }
                    }
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_main_v2);
        mainCalendarApplication = MainCalendarApplication.getInstance();

        // ScheduleApplication 및 TaskApplication 인스턴스에서 데이터 맵 가져오기
        scheduleMap = MainCalendarApplication.getInstance().getScheduleMap();
        TaskMap = MainCalendarApplication.getInstance().getTaskMap();

        // 리스트 초기화
        todayScheduleList = new ArrayList<>();
        todayTaskList = new ArrayList<>();

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

        // Google 계정 인증을 위한 GoogleAccountCredential 초기화
        mCredential = GoogleAccountCredential.usingOAuth2(
                        this, Arrays.asList(CalendarScopes.CALENDAR))
                .setBackOff(new ExponentialBackOff());
        initializeGoogleCalendarService();

        // 사용자가 이미 구글 계정을 선택했는지 확인
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String accountName = prefs.getString(PREF_ACCOUNT_NAME, null);
        if (accountName != null) {
            mCredential.setSelectedAccountName(accountName);
        } else {
            // 계정 선택을 위한 인증 프로세스 시작
            chooseGoogleAccount();
        }

    }

    // Google Calendar API 호출을 시작합니다.
    private void getGoogleCalendarResults() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {
            chooseGoogleAccount();
        } else {
            // GET_ACCOUNTS 권한 요청
            EasyPermissions.requestPermissions(
                    this, "구글 캘린더 접근 권한이 필요합니다.",
                    REQUEST_PERMISSION_GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * 사용자에게 Google 계정을 선택하도록 요청합니다.
     * 이미 로그인된 계정이 있으면 해당 계정을 사용합니다.
     */
// Google 계정을 선택하도록 요청합니다.
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseGoogleAccount() {
        String accountName = getPreferences(Context.MODE_PRIVATE)
                .getString(PREF_ACCOUNT_NAME, null);
        if (accountName != null) {
            mCredential.setSelectedAccountName(accountName);
            initializeGoogleCalendarService();
        } else {
            // 사용자가 계정을 선택할 수 있는 다이얼로그 표시
            startActivityForResult(mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
        }
    }

    private void initializeGoogleCalendarService() {
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        mService = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, mCredential)
                .setApplicationName("PlanAI") // 여기에 애플리케이션 이름을 넣으세요.
                .build();
        if (mService == null) {
            Log.e("Server!!", "Failed to initialize Google Calendar API service.");
        } else {
            Log.d("Server!!", "Google Calendar API service is initialized.");
        }
        // Google Calendar API 서비스 준비가 완료되었습니다.
        Log.d("Server!!", "Google Calendar API service is initialized.");

        // 추가적인 작업이 필요한 경우 여기에 코드를 추가합니다.
        // 예를 들어, 사용자 인터페이스를 업데이트하거나, 특정 API 호출을 시작할 수 있습니다.
    }


    // Google Calendar에 Schedule 추가하는 AsyncTask
    private class AddScheduleToCalendarTask extends AsyncTask<Void, Void, Void> {
        private GoogleAccountCredential credential;
        private Server_ScheduleDTO googleSendScheduleDTO;

        public AddScheduleToCalendarTask(GoogleAccountCredential credential, Server_ScheduleDTO schedule) {
            this.credential = credential;
            this.googleSendScheduleDTO = schedule;
            Log.e("Server!!", "AddEventToCalendarTask working");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // 구글 캘린더에 추가할 이벤트 생성
                Event event = new Event()
                        .setSummary(googleSendScheduleDTO.getTitle())
                        .setDescription(googleSendScheduleDTO.getDescription() + "\n#" + String.join("#", googleSendScheduleDTO.getTagList()));

                // 시작 및 종료 시간 설정
                String startDateStr = googleSendScheduleDTO.getStartDate();
                String endDateStr = googleSendScheduleDTO.getEndDate();

                // 날짜 형식 변환 (예: "2023-11-27T11:11" -> "2023-11-27T11:11:00.000Z")
                startDateStr = convertToRFC3339Format(startDateStr);
                endDateStr = convertToRFC3339Format(endDateStr);

                DateTime startDateTime = new DateTime(startDateStr);
                DateTime endDateTime = new DateTime(endDateStr);

                EventDateTime start = new EventDateTime()
                        .setDateTime(startDateTime)
                        .setTimeZone("Asia/Seoul");
                EventDateTime end = new EventDateTime()
                        .setDateTime(endDateTime)
                        .setTimeZone("Asia/Seoul");

                event.setStart(start);
                event.setEnd(end);
                Log.d("Server!!", startDateStr);
                // 캘린더 ID 설정 (기본 캘린더 사용)
                String calendarId = "primary";
                Log.e("Server!!", "doInBackground ok");

                // 이벤트 삽입
                mService.events().insert(calendarId, event).execute();
                Log.e("Server!!", "execute okay");
            } catch (UserRecoverableAuthIOException e) {
                Intent intent = e.getIntent();
                startActivityForResult(intent, REQUEST_AUTHORIZATION);
            }catch (IOException e) {
                // 다른 IOException 처리
            }catch (Exception e) {
                e.printStackTrace();

                Log.e("Server!!", "exception: "+e.getMessage());
            }
            return null;
        }
    }

    // Google Calendar에 Schedule 추가하는 AsyncTask
    private class AddTaskToCalendarTask extends AsyncTask<Void, Void, Void> {
        private GoogleAccountCredential credential;
        private Server_TaskDTO googleSendTaskDTO;

        public AddTaskToCalendarTask(GoogleAccountCredential credential, Server_TaskDTO task) {
            this.credential = credential;
            this.googleSendTaskDTO = task;
            Log.e("Server!!", "AddEventToCalendarTask working");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // 구글 캘린더에 추가할 이벤트 생성
                Event event = new Event()
                        .setColorId("2")
                        .setSummary(googleSendTaskDTO.getTitle())
                        .setDescription(googleSendTaskDTO.getDescription() + "\n#" + String.join("#", googleSendTaskDTO.getTagList())+"\n#" + String.join("#"+googleSendTaskDTO.getPriority()));


                // 시작 및 종료 시간 설정
                String startDateStr = googleSendTaskDTO.getDeadline();
                String endDateStr = googleSendTaskDTO.getDeadline();

                // 날짜 형식 변환 (예: "2023-11-27T11:11" -> "2023-11-27T11:11:00.000Z")
                startDateStr = convertToRFC3339Format(startDateStr);
                endDateStr = convertToRFC3339Format(endDateStr);

                DateTime startDateTime = new DateTime(startDateStr);
                DateTime endDateTime = new DateTime(endDateStr);

                EventDateTime start = new EventDateTime()
                        .setDateTime(startDateTime)
                        .setTimeZone("Asia/Seoul");
                EventDateTime end = new EventDateTime()
                        .setDateTime(endDateTime)
                        .setTimeZone("Asia/Seoul");

                event.setStart(start);
                event.setEnd(end);
                Log.d("Server!!", startDateStr);
                // 캘린더 ID 설정 (기본 캘린더 사용)
                String calendarId = "primary";
                Log.e("Server!!", "doInBackground ok");

                // 이벤트 삽입
                mService.events().insert(calendarId, event).execute();
                Log.e("Server!!", "execute okay");
            } catch (UserRecoverableAuthIOException e) {
                Intent intent = e.getIntent();
                startActivityForResult(intent, REQUEST_AUTHORIZATION);
            }catch (IOException e) {
                // 다른 IOException 처리
            }catch (Exception e) {
                e.printStackTrace();
                Log.e("Server!!", "exception: "+e.getMessage());
            }
            return null;
        }
    }

    // RFC3339 형식으로 날짜 변환하는 메소드
    private String convertToRFC3339Format(String dateTimeStr) {
        if (dateTimeStr != null) {
            if (dateTimeStr.length() == "2023-11-26T19:30".length()) {
                dateTimeStr += ":00Z";
            } else if (dateTimeStr.length() == ("2023-11-26T19:30".length() + 3)) {
                dateTimeStr += "Z";
            }

        }
        return dateTimeStr;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ACCOUNT_PICKER && resultCode == RESULT_OK && data != null) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            if (accountName != null) {
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(PREF_ACCOUNT_NAME, accountName);
                editor.apply();
                mCredential.setSelectedAccountName(accountName);
            }
        }
    }

    private class FetchSchedulesFromCalendarTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                DateTime now = new DateTime(System.currentTimeMillis());
                Events events = mService.events().list("primary")
                        .setMaxResults(30)
                        .setTimeMin(now)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();

                List<Event> items = events.getItems();

                for (Event event : items) {
                    Schedule schedule = convertEventToSchedule(event);
                    String startDate = schedule.getStartDate();

                    ArrayList<Schedule> schedulesForDate = scheduleMap.getOrDefault(startDate, new ArrayList<>());
                    if (!schedulesForDate.contains(schedule)) {
                        schedulesForDate.add(schedule);
                        Log.d("Server!!","loaded date:" +schedule.toString());
                    }

                    scheduleMap.put(startDate, schedulesForDate);
                }
            } catch (Exception e) {
                Log.e("Server!!", "Error fetching calendar events: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // 현재 선택된 날짜를 가져옵니다.
            String selectedDate = getIntent().getStringExtra("date");
            ArrayList<Schedule> schedulesForSelectedDate = scheduleMap.get(selectedDate);

            // 현재 RecyclerView에 표시된 스케줄 리스트를 업데이트합니다.
            if (schedulesForSelectedDate != null) {
                todayScheduleList.clear();
                todayScheduleList.addAll(schedulesForSelectedDate);
            } else {
                // 선택된 날짜에 대한 스케줄이 없는 경우, 목록을 비웁니다.
                todayScheduleList.clear();
            }

            // RecyclerView 어댑터를 통해 UI를 갱신합니다.
            scheduleAdapter.notifyDataSetChanged();
            Log.d("Server!!", "RecyclerView가 갱신되었습니다.");
        }
    }

//    private String getEvent() throws IOException {
//
//
//        DateTime now = new DateTime(System.currentTimeMillis());
//
//        String calendarID = getCalendarID("CalendarTitle");
//        if ( calendarID == null ){
//
//            return "캘린더를 먼저 생성하세요.";
//        }
//
//
//        Events events = mService.events().list(calendarID)//"primary")
//                .setMaxResults(10)
//                //.setTimeMin(now)
//                .setOrderBy("startTime")
//                .setSingleEvents(true)
//                .execute();
//        List<Event> items = events.getItems();
//
//
//        for (Event event : items) {
//            DateTime start = event.getStart().getDateTime();
//            if (start == null) {
//
//                // 모든 이벤트가 시작 시간을 갖고 있지는 않다. 그런 경우 시작 날짜만 사용
//                start = event.getStart().getDate();
//            }
//            eventStrings.add(String.format("%s \n (%s)", event.getSummary(), start));
//        }
//
//        return eventStrings.size() + "개의 데이터를 가져왔습니다.";
//    }


    /**
     * Google Calendar의 Event 객체를 앱의 Schedule 객체로 변환합니다.
     *
     * @param event Google Calendar의 Event 객체.
     * @return 변환된 Schedule 객체.
     */
    private Schedule convertEventToSchedule(Event event) {
        Log.d("Server!!","convertEventToSchedule started");
        // 이벤트의 요약(제목)을 가져옵니다.
        String summary = event.getSummary();

        // 이벤트의 시작 및 종료 시간을 가져옵니다.
        DateTime startDateTime = event.getStart().getDateTime();
        DateTime endDateTime = event.getEnd().getDateTime();

        // 전일 이벤트의 경우 DateTime이 아닌 Date만 사용되므로, 이를 확인하고 처리합니다.
        if (startDateTime == null) {
            startDateTime = event.getStart().getDate();
        }
        if (endDateTime == null) {
            endDateTime = event.getEnd().getDate();
        }

        // 시작 및 종료 날짜와 시간을 문자열로 변환합니다.
        // RFC3339 형식의 문자열에서 날짜와 시간을 분리합니다.
        String startDate = startDateTime.toStringRfc3339().split("T")[0];
        String startTime = startDateTime.toStringRfc3339().split("T").length > 1 ? startDateTime.toStringRfc3339().split("T")[1].split("Z")[0] : "";
        String endDate = endDateTime.toStringRfc3339().split("T")[0];
        String endTime = endDateTime.toStringRfc3339().split("T").length > 1 ? endDateTime.toStringRfc3339().split("T")[1].split("Z")[0] : "";

        // 이벤트의 설명(Description)을 가져옵니다.
        String description = event.getDescription();
        // 설명에서 태그를 추출합니다.
        String tags = extractTagsFromDescription(description);

        // Schedule 객체를 생성하여 반환합니다.
        //TODO: ID 변경
        return new Schedule("1", summary, startDate, startTime, endDate, endTime, tags, description);
    }

    /**
     * 이벤트의 설명에서 #으로 시작하는 태그를 추출하여 하나의 문자열로 반환합니다.
     *
     * @param description 이벤트의 설명 문자열.
     * @return 추출된 태그들을 포함하는 문자열.
     */
    private String extractTagsFromDescription(String description) {
        StringBuilder tagsBuilder = new StringBuilder();
        if (description != null) {
            // 설명을 공백 기준으로 분리하여 각 부분을 검사합니다.
            String[] parts = description.split(" ");
            for (String part : parts) {
                // 각 부분이 #으로 시작한다면 태그로 간주합니다.
                if (part.startsWith("#")) {
                    // '#'을 제외하고 태그를 추가합니다.
                    tagsBuilder.append(part.substring(1)).append(" ");
                }
            }
        }
        // 생성된 태그 문자열에서 앞뒤 공백을 제거하여 반환합니다.
        return tagsBuilder.toString().trim();
    }

    private void setupDateBanner() {
        Intent dateIntent = getIntent();
        todayDate = dateIntent.getStringExtra("date");
        //TODO: userId = dateIntent.getStringExtra("userId");
        userId = 1111L;

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
            case "01":
                return "January";
            case "02":
                return "February";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "August";
            case "09":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "Invalid month";
        }
    }


    private void setupRecyclerView() {
        scheduleRecyclerShowDay = findViewById(R.id.scheduleRecyclerView);
        scheduleRecyclerShowDay.setHasFixedSize(true);
        scheduleRecyclerShowDay.setLayoutManager(new LinearLayoutManager(this));
        scheduleAdapter = new ScheduleAdapter(todayScheduleList);
        scheduleRecyclerShowDay.setAdapter(scheduleAdapter);

        // 태스크 RecyclerView 설정
        taskRecyclerShowDay = findViewById(R.id.taskRecyclerView);
        taskRecyclerShowDay.setHasFixedSize(true);
        taskRecyclerShowDay.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(todayTaskList);
        taskRecyclerShowDay.setAdapter(taskAdapter);


    }

    private void loadSchedules(String date) {

        // 스케줄과 태스크 데이터 로드
        List<Schedule> schedulesForToday = mainCalendarApplication.getScheduleMap().getOrDefault(date, new ArrayList<>());
        List<Task> tasksForToday = mainCalendarApplication.getTaskMap().getOrDefault(date, new ArrayList<>());

        // 어댑터 데이터 갱신
        todayScheduleList.clear();
        todayScheduleList.addAll(schedulesForToday);
        scheduleAdapter.notifyDataSetChanged();

        todayTaskList.clear();
        todayTaskList.addAll(tasksForToday);
        taskAdapter.notifyDataSetChanged();
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
            createTaskLauncher.launch(intent); // ActivityResultLauncher 사용
            overridePendingTransition(R.anim.slide_up, R.anim.stay); // 아래에서 올라오는 애니메이션 적용
        });

        etcButton.setOnClickListener(view ->{
            if (mCredential.getSelectedAccountName() != null) {
                // 사용자 계정이 이미 선택되었으면, Google Calendar에서 일정을 가져옵니다.
                new FetchSchedulesFromCalendarTask().execute();
            } else {
                // 사용자 계정이 선택되지 않았으면, 계정 선택을 요청합니다.
                chooseGoogleAccount();
            }
            Toast.makeText(getApplicationContext(), "갱신완료!", Toast.LENGTH_SHORT).show();
        });


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

            } else if (item.getItemId() == R.id.navigation_friend) {
                startActivity(new Intent(ShowDayActivity.this, FriendlistActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_home) {
                startActivity(new Intent(ShowDayActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_post) {
                startActivity(new Intent(ShowDayActivity.this, BoardActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_setting) {
                startActivity(new Intent(ShowDayActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현
            }
            return true;
        });
    }
}