package com.example.planai_front;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MaterialCalendarActivity extends AppCompatActivity {
    private MaterialCalendarView materialCalendarView;
    private RecyclerView recyclerView;
    private EventActivity adapter;
    private LinearLayout newUILayout;
    private TextView selectedDateTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materialcalendar_main); // XML 레이아웃 파일 설정

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView); // ID 확인
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view); // RecyclerView ID
        newUILayout = (LinearLayout) findViewById(R.id.layout_new_ui); // 새로운 UI 레이아웃
        selectedDateTextView = (TextView)findViewById(R.id.text_view_selected_date); // 날짜 표시 TextView
        backButton = (Button) findViewById(R.id.button_back); // 뒤로 가기 버튼

        // RecyclerView 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventActivity(new ArrayList<>()); // 초기 빈 데이터 세트
        recyclerView.setAdapter(adapter);

        // 날짜 선택 리스너 설정
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected (@NonNull MaterialCalendarView widget, @NonNull CalendarDay date,boolean selected){
                selectedDateTextView.setText("Selected Date: " + date.getDate().toString());
                newUILayout.setVisibility(View.VISIBLE);

                // 날짜에 따른 데이터 업데이트
                updateDataForDate(date);
            }
        });

        // 뒤로 가기 버튼 리스너 설정
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUILayout.setVisibility(View.GONE);
            }
        });
    }

    private void updateDataForDate(CalendarDay date) {
        // 선택된 날짜에 대한 데이터를 가져오고 RecyclerView 업데이트
        List<Event> eventsForSelectedDate = getEventsForDate(date);
        //adapter.updateData(eventsForSelectedDate);
    }

    private List<Event> getEventsForDate(CalendarDay date) {
        // 이 부분에서 날짜에 따른 이벤트 데이터를 생성하거나 가져옵니다.
        List<Event> events = new ArrayList<>();
        // 예시 데이터 추가
        events.add(new Event("Event 1", "Description for Event 1"));
        events.add(new Event("Event 2", "Description for Event 2"));
        return events;
    }
}
