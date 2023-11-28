package com.example.planai_front;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class GoogleSignInActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        // GoogleSignInOptions 설정
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("400496893792-6o7htfak2iihkr230ncecn10msjchq6n.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // GoogleApiClient 설정
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Google 로그인 버튼 클릭 시 이벤트 처리
        findViewById(R.id.btn_google_sign_in).setOnClickListener(view -> signIn());
        findViewById(R.id.toMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("메인으로 버튼 클릭");
                Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String displayName = account.getDisplayName();
            String email = account.getEmail();
            String idToken = account.getIdToken();


            GoogleSignIn.getLastSignedInAccount(this);

            // 로그인 성공 시 원하는 작업 수행
            // 예: 사용자 정보를 서버에 전송하거나 앱 내에서 활용

            Toast.makeText(this, "로그인 성공: "+ "token : " + idToken + " / " + displayName + " (" + email + ")", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // 로그인 실패 시 사용자에게 알림
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("GoogleSignIn", "onConnectionFailed:" + connectionResult);
        //LOg.d("GoogleSignIn", connectionResult.)
        Toast.makeText(this, "Google 로그인 실패", Toast.LENGTH_SHORT).show();
    }
}
