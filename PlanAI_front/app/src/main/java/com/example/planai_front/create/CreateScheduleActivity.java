package com.example.planai_front.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planai_front.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

//전달받은 todayDate(2023-11-22 형식을 가짐)을 id로 해서 서버에 Task 등록
//popup_layout을 띄움(아래에서 올라오는 pop-up 또는 popup 처럼 생긴 새로운 xml 페이지. 편한걸로 할것!!!)
//뒤로가기 버튼을 통해 ShowDayActivity로 돌아감
public class CreateScheduleActivity extends AppCompatActivity {

    private String todayDate;
    private EditText summaryText;
    private Switch repeatSchedule;
    private TextView startDateText, startTimeText, endDateText, endTimeText;
    private ImageView startCalendarButton, startTimeButton, endCalendarButton, endTimeButton;
    private TextView tagTextView;
    private String todayTag;
    private String todaySummary;
    private Calendar startCalendar, startTimeCal, endCalendar, endTimeCal;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createschedule_popuplayout);

        initializeViews();
        getSummaryText();
        setupDateAndTimePickers();
        setupTagDropDown();
        setFinishButton();
    }

    private void initializeViews() {
        Intent dateIntent = getIntent();
        todayDate = dateIntent.getStringExtra("date");

        startDateText = findViewById(R.id.deadLineDate);
        startCalendarButton = findViewById(R.id.deadLineCalendarButton);
        startTimeText = findViewById(R.id.deadLineTime);
        startTimeButton = findViewById(R.id.deadLineTimeButton);
        endDateText = findViewById(R.id.endDate);
        endCalendarButton = findViewById(R.id.endCalendarButton);
        endTimeText = findViewById(R.id.endTime);
        endTimeButton = findViewById(R.id.endTimeButton);
        tagTextView = findViewById(R.id.tagTextView);
        startCalendar = Calendar.getInstance();
        startTimeCal = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        endTimeCal = Calendar.getInstance();
        finishButton = findViewById(R.id.finishButton);
    }

    private void getSummaryText() {
        summaryText = findViewById(R.id.scheduleSummary);
        todaySummary = summaryText.getText().toString();

    }
    private void setupDateAndTimePickers() {
        setupDatePicker(startCalendarButton, startDateText, startCalendar);
        setupTimePicker(startTimeButton, startTimeText, startTimeCal);
        setupDatePicker(endCalendarButton, endDateText, endCalendar);
        setupTimePicker(endTimeButton, endTimeText, endTimeCal);
    }

    private void setupDatePicker(ImageView button, final TextView textView, final Calendar calendar) {
        button.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateScheduleActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        month++;
                        String selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month, dayOfMonth);
                        textView.setText(selectedDate);
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

    private void setupTimePicker(ImageView button, final TextView textView, final Calendar calendar) {
        button.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateScheduleActivity.this,
                    (view, hourOfDay, minute) -> {
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        textView.setText(selectedTime);
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });
    }

    private void setupTagDropDown() {
        Button tagShowDropDown = findViewById(R.id.tagShowDropDown);
        tagShowDropDown.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(CreateScheduleActivity.this, v);
            popupMenu.getMenu().add("text1");
            popupMenu.getMenu().add("text2");
            popupMenu.getMenu().add("text3");
            popupMenu.setOnMenuItemClickListener(item -> {
                todayTag = item.getTitle().toString();
                tagTextView.setText(todayTag);
                return true;
            });
            popupMenu.show();
        });
    }

    private void setFinishButton() {
        Button finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                ArrayList<String> scheduleData = collectScheduleData();
//                sendDataToServer(scheduleData);
                finish();
            }
        });
    }

    private ArrayList<String> collectScheduleData() {
        ArrayList<String> scheduleData = new ArrayList<>();
        scheduleData.add(todayDate);
        scheduleData.add(todaySummary);
        scheduleData.add(startDateText.getText().toString());
        scheduleData.add(startTimeText.getText().toString());
        scheduleData.add(endDateText.getText().toString());
        scheduleData.add(endTimeText.getText().toString());
        scheduleData.add(tagTextView.getText().toString());
        return scheduleData;
    }

    // 서버에 데이터 전송하는 메서드 (더미 예시)
    private void sendDataToServer(ArrayList<String> data) {


    }
}
