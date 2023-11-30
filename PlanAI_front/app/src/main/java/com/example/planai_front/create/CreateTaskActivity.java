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
//ChatGPT 더하기
public class CreateTaskActivity extends AppCompatActivity {

    private String todayDate;
    private EditText summaryText, descriptionText;
    private Switch repeatSchedule;
    private TextView deadLineDateText, deadLineTimeText;
    private ImageView deadLineCalendarButton, deadLineTimeButton;
    private TextView tagTextView, priorityTextView;
    private String todayTag,todayPriority;
    private String todaySummary, todayDescription;
    private Calendar deadLineCalendar, deadLineTimeCal;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtask_popuplayout);

        initializeViews();
        setupDateAndTimePickers();
        setupTagDropDown();
        setUpPriorityDropDown();

        getSummaryText();
        getDescriptionText();

        finishButton.setOnClickListener((view->finishTaskCreation()));
    }

    private void initializeViews() {
        Intent dateIntent = getIntent();
        todayDate = dateIntent.getStringExtra("date");

        deadLineDateText = findViewById(R.id.taskDeadLineDateView);
        deadLineCalendarButton = findViewById(R.id.taskDeadLineCalendarButtonView);
        deadLineTimeText = findViewById(R.id.taskDeadLineTimeView);
        deadLineTimeButton = findViewById(R.id.taskDeadLineTimeButton);
        tagTextView = findViewById(R.id.taskTagView);
        priorityTextView = findViewById(R.id.taskPriorityView);
        deadLineCalendar = Calendar.getInstance();
        deadLineTimeCal = Calendar.getInstance();
        finishButton = findViewById(R.id.taskFinishButton);

        deadLineCalendar = Calendar.getInstance();
        deadLineTimeCal = Calendar.getInstance();
    }
    private void getSummaryText() {
        summaryText = findViewById(R.id.taskSummaryView);
        todaySummary = summaryText.getText().toString();
    }

    private void setupDateAndTimePickers() {
        setupDatePicker(deadLineCalendarButton, deadLineDateText, deadLineCalendar);
        setupTimePicker(deadLineTimeButton, deadLineTimeText, deadLineTimeCal);

    }

    private void setupDatePicker(ImageView button, final TextView textView, final Calendar calendar) {
        button.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateTaskActivity.this,
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
            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateTaskActivity.this,
                    (view, hourOfDay, minute) -> {
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        textView.setText(selectedTime);
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });
    }

    private void setupTagDropDown() {
        Button tagShowDropDown = findViewById(R.id.taskTagShowDropDown);
        tagShowDropDown.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(CreateTaskActivity.this, v);
            popupMenu.getMenu().add("Study");
            popupMenu.getMenu().add("Work");
            popupMenu.getMenu().add("text3");
            popupMenu.setOnMenuItemClickListener(item -> {
                todayTag = item.getTitle().toString();
                tagTextView.setText(todayTag);
                return true;
            });
            popupMenu.show();
        });
    }

    private void setUpPriorityDropDown() {
        Button priorityShowDropDown = findViewById(R.id.taskPriorityShowDropDown);
        priorityShowDropDown.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(CreateTaskActivity.this, v);
            popupMenu.getMenu().add("HIGH");
            popupMenu.getMenu().add("MEDIUM");
            popupMenu.getMenu().add("LOW");
            popupMenu.setOnMenuItemClickListener(item -> {
                todayPriority = item.getTitle().toString();
                priorityTextView.setText(todayPriority);  // 여기서 수정되어야 합니다.
                return true;
            });
            popupMenu.show();
        });
    }

    private void getDescriptionText() {
        descriptionText = findViewById(R.id.taskDescriptionView);
        todayDescription = descriptionText.getText().toString();
    }

    // 일정 생성 완료 처리
    private void finishTaskCreation() {
        // 사용자 입력 데이터 수집
        String summary = summaryText.getText().toString();
        String deadLineDate= deadLineDateText.getText().toString();
        String deadLineTime = deadLineTimeText.getText().toString();
        String tag = tagTextView.getText().toString();
        String priority = priorityTextView.getText().toString();
        String description = descriptionText.getText().toString();

        // 결과를 Intent에 담아 반환
        Intent resultIntent = new Intent();
        resultIntent.putExtra("summary", summary);
        resultIntent.putExtra("deadLineDate", deadLineDate);
        resultIntent.putExtra("deadLineTime", deadLineTime);
        resultIntent.putExtra("tag", tag);
        resultIntent.putExtra("priority",priority);
        resultIntent.putExtra("description", description);

        setResult(RESULT_OK, resultIntent);
        finish();
    }


}
