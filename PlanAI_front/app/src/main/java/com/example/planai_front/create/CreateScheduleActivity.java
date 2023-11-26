package com.example.planai_front.create;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planai_front.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//전달받은 todayDate(2023-11-22 형식을 가짐)을 id로 해서 서버에 Task 등록
//popup_layout을 띄움(아래에서 올라오는 pop-up 또는 popup 처럼 생긴 새로운 xml 페이지. 편한걸로 할것!!!)
//뒤로가기 버튼을 통해 ShowDayActivity로 돌아감
public class CreateScheduleActivity extends AppCompatActivity {

    private EditText summaryText;
    private Switch repeatSchedule;
    private EditText startDateText;
    private Calendar startCalendar;
    private ImageView startCalendarButton;
    private EditText startTime;
    private ImageView startTimeButton;
    private EditText endDateText;
    private Calendar endCalendar;
    private  EditText endTime;


    protected void onCreate(Bundle savedInstanceState) {
        //show day_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createschedule_popuplayout);

        Intent dateIntent = getIntent();
        String todayDate = dateIntent.getStringExtra("date");

        //날짜 선택기
        startDateText = findViewById(R.id.startDate);
        startCalendarButton = findViewById(R.id.startCalendarButton);
        startCalendar = Calendar.getInstance();

        startCalendarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateScheduleActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Calendar month is zero-based
                                month++;
                                String selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month, dayOfMonth);
                                startDateText.setText(selectedDate);
                            }
                        },
                        startCalendar.get(Calendar.YEAR),
                        startCalendar.get(Calendar.MONTH),
                        startCalendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    }
}
