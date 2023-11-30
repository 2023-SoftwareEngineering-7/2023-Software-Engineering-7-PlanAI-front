package com.example.planai_front.Profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.planai_front.BoardActivity;
import com.example.planai_front.FriendlistActivity;
import com.example.planai_front.MaterialCalendarActivity;
import com.example.planai_front.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {
    private TextView settings;
    private TextView darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settings = findViewById(R.id.setting_txt);
        darkMode = findViewById(R.id.darkMode);
        setupBottomNavigationBar();
        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked(view);
            }
        });
    }
    public void showAlertDialogButtonClicked(View view) {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Dark Mode");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.popup_dark_mode, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            // send data from the AlertDialog to the Activity
//            EditText editText = customLayout.findViewById(R.id.editText);
//            sendDialogDataToActivity(editText.getText().toString());
        });
        builder.setCancelable(false);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_calendar) {
                startActivity(new Intent(SettingActivity.this, MaterialCalendarActivity.class));

            }else if (item.getItemId() == R.id.navigation_friend) {
                startActivity(new Intent(SettingActivity.this, FriendlistActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            } else if (item.getItemId() == R.id.navigation_home){
                startActivity(new Intent(SettingActivity.this, MaterialCalendarActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_post){
                startActivity(new Intent(SettingActivity.this, BoardActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현

            }else if (item.getItemId() == R.id.navigation_setting) {
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                // 추가 버튼에 대한 처리를 여기에 작성합니다.
                // 예: case R.id.navigation_new_button:
                // 버튼에 대한 액션 구현
            }
            return true;
        });
    }
}