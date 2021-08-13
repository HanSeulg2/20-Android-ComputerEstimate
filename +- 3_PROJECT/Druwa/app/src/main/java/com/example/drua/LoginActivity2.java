package com.example.drua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class LoginActivity2 extends AppCompatActivity {
    String strNickname, strProfile, strEmail, strGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        EditText tvNickname = findViewById(R.id.nameEditText);
        ImageView ivProfile = findViewById(R.id.ivProfile);
        EditText tvEmail = findViewById(R.id.emailEditText);
        EditText tvGender = findViewById(R.id.sexEditText);
        Button btnLogout = findViewById(R.id.btnLogout);
        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile");
        strEmail = intent.getStringExtra("email");
        strGender = intent.getStringExtra("gender");

        tvNickname.setText(strNickname);
        tvEmail.setText(strEmail);
        tvGender.setText(strGender);

        Glide.with(this).load(strProfile).into(ivProfile); //프로필 사진 url을 사진으로 보여줌
        btnLogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(LoginActivity2.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}