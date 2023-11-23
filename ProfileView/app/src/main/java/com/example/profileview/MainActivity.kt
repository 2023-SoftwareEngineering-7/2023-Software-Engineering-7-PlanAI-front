package com.example.profileview

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "프로필 변경" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonProfileChange).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "계정 변경" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonAccountChange).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "연동" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonLink).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "다크모드" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonDarkMode).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "알림 설정" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonNotificationSettings).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "회원 탈퇴" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonWithdrawal).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }

        // "로그 아웃" 버튼 클릭 시 처리
        findViewById<View>(R.id.buttonLogout).setOnClickListener {
            // 해당하는 액티비티로 이동하는 코드 추가
        }
    }
}
