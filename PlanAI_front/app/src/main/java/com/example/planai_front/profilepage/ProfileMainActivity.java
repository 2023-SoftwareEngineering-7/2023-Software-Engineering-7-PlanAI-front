package com.example.planai_front.profilepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.planai_front.profilepage.R;

public class ProfileMainActivity extends AppCompatActivity {
    private Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile.activity_main);
        check = findViewById(R.id.checkButton);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Comment out or remove the following line to prevent moving to SettingActivity
                // startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }
}

//package com.example.profilepage;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//
//public class MainActivity extends AppCompatActivity {
//    private Button check;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        check = findViewById(R.id.checkButton);
//
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
//            }
//        });
//    }
//}
