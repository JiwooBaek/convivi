package com.example.project_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import model.UserModel;

public class SignupActivity extends AppCompatActivity {
    private EditText name;
    private EditText emailAdd;
    private EditText password;
    String name_txt;
    String email_txt;
    String pswd_txt;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");

        name = (EditText) findViewById(R.id.userName);
        emailAdd = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPswd);

        //문자열 값 추출 및 문자열형 변수 설정
        name_txt = name.getText().toString();
        email_txt = emailAdd.getText().toString();
        pswd_txt = password.getText().toString();

        //확인 버튼 동작 구현
        Button signUp_button = (Button) findViewById(R.id.complete);
        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_txt == null | email_txt == null | pswd_txt == null) {
                    return;
                }

                String uid = firebaseAuth.getCurrentUser().getUid();

                UserModel userModel = new UserModel();

            }
        });

        // 취소 버튼 동작 구현
        Button cancel_button = (Button) findViewById(R.id.cancel);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
