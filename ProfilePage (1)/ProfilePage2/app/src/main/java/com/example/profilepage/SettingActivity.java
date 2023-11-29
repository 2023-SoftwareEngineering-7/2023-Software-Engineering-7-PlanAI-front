package com.example.profilepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    private TextView settings;
    private TextView darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settings = findViewById(R.id.setting_txt);
        darkMode = findViewById(R.id.darkMode);

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
}