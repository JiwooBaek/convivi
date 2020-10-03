package com.example.project_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import model.UserModel;

public class SignupActivity extends AppCompatActivity {
    private EditText name;
    private EditText emailAdd;
    private EditText password;
    private EditText passwordCheck;
    private Button signUp;
    private TextView showEmailVerified;
    String name_txt;
    String email_txt;
    String pwd_txt;
    String pwdCheck_txt;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //파이어베이스 접근 설정
        firebaseAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");

        name = (TextInputEditText) findViewById(R.id.userName);
        emailAdd = (TextInputEditText) findViewById(R.id.userEmail);
        password = (TextInputEditText) findViewById(R.id.userPwd);
        passwordCheck = (TextInputEditText) findViewById(R.id.userPwdCheck);
        signUp = (Button) findViewById(R.id.complete);
        showEmailVerified = (TextView) findViewById(R.id.emailVerifiedCheck);

        //문자열 값 추출 및 문자열형 변수 설정
        name_txt = name.getText().toString();
        email_txt = emailAdd.getText().toString();
        pwd_txt = password.getText().toString();
        pwdCheck_txt = passwordCheck.getText().toString();

        Button verification = (Button) findViewById(R.id.verifyEmail);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(email_txt, pwd_txt)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                showEmailVerified.setText("이메일 인증 성공!");
                                                signUp.setEnabled(true);
                                            } else {
                                                Log.e("Email Verifier Error","sendEmailVerification",task.getException());
                                                showEmailVerified.setText("이메일 인증 실패");
                                            }
                                        }
                                    });
                                }
                            }
                        });
            }
        });

        //확인 버튼 동작 구현
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_txt == null | email_txt == null | pwd_txt == null) {
                    return;
                }

                String uid = firebaseAuth.getCurrentUser().getUid();
                UserModel userModel = new UserModel();

                if(isPwdChecked(pwd_txt, pwdCheck_txt) == true
                        && firebaseAuth.getCurrentUser().isEmailVerified() == true) {
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            SignupActivity.this.finish();
                        }

                    });
                    userModel.name = name_txt;
                    userModel.emailAddress = email_txt;
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                } else if(isPwdChecked(pwd_txt, pwdCheck_txt) == false){
                    Toast.makeText(SignupActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
                } else if(firebaseAuth.getCurrentUser().isEmailVerified() == false) {
                    Toast.makeText(SignupActivity.this, "이메일을 인증해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignupActivity.this, "인증 절차가 완료되지 않았습니다.", Toast.LENGTH_LONG).show();
                }
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

    public boolean isPwdChecked(String pwd, String pwd_check) {
        if(pwd.equals(pwd_check)) {
            return true;
        }
        return false;
    }
}
