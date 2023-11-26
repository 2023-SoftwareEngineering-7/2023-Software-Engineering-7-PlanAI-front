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
public class CreateTaskActivity extends AppCompatActivity {

    private String todayDate;
    private EditText summaryText;
    private Switch repeatSchedule;
    private TextView deadLineDate, deadLineTime;
    private ImageView deadLineCalendarButton, deadLineTimeButton;
    private TextView tagTextView;
    private String todayTag;
    private String todaySummary;
    private Calendar deaLineCalendar, deadLineTimeCal;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtask_popuplayout);

        initializeViews();
        getSummaryText();
        setupDateAndTimePickers();
        setupTagDropDown();
        setFinishButton();
    }

    private void initializeViews() {
        Intent dateIntent = getIntent();
        todayDate = dateIntent.getStringExtra("date");

        deadLineDate = findViewById(R.id.deadLineDate);
        deadLineCalendarButton = findViewById(R.id.deadLineCalendarButton);
        deadLineTime = findViewById(R.id.deadLineTime);
        deadLineTimeButton = findViewById(R.id.deadLineTimeButton);
        tagTextView = findViewById(R.id.tagTextView);
        deaLineCalendar = Calendar.getInstance();
        deadLineTimeCal = Calendar.getInstance();
        finishButton = findViewById(R.id.finishButton);
    }
    private void getSummaryText() {
        summaryText = findViewById(R.id.scheduleSummary);
        todaySummary = summaryText.getText().toString();

    }
    private void setupDateAndTimePickers() {
        setupDatePicker(deadLineCalendarButton, deadLineDate, deaLineCalendar);
        setupTimePicker(deadLineTimeButton, deadLineTime, deadLineTimeCal);

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
        Button tagShowDropDown = findViewById(R.id.tagShowDropDown);
        tagShowDropDown.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(CreateTaskActivity.this, v);
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
//                ArrayList<String> taskData = collectTaskData();
//                sendDataToServer(taskData);
                finish();
            }
        });
    }

    private ArrayList<String> collectTaskData() {
        ArrayList<String> TaskData = new ArrayList<>();
        TaskData.add(todayDate);
        TaskData.add(todaySummary);
        TaskData.add(deadLineDate.getText().toString());
        TaskData.add(deadLineTime.getText().toString());

        TaskData.add(tagTextView.getText().toString());
        return TaskData;
    }

    // 서버에 데이터 전송하는 메서드 (더미 예시)
    private void sendDataToServer(ArrayList<String> data) {


    }
}
