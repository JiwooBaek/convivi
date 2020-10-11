package com.example.project_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_1.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import model.UserModel;

public class SignupActivity extends AppCompatActivity {
    private TextInputEditText name;
    private TextInputEditText emailAdd;
    private TextInputEditText password;
    private TextInputEditText passwordCheck;
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

        name = findViewById(R.id.userName);
        emailAdd = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPwd);
        passwordCheck = findViewById(R.id.userPwdCheck);
        signUp = (Button) findViewById(R.id.complete);
        showEmailVerified = (TextView) findViewById(R.id.emailVerifiedCheck);


        Button verification = (Button) findViewById(R.id.verifyEmail);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(emailAdd.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                firebaseAuth.getCurrentUser().sendEmailVerification();
                                                showEmailVerified.setText("인증 메일 전송!");
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
                firebaseAuth.getCurrentUser().reload();

                if(name.getText().toString() == null | emailAdd.getText().toString() == null | password.getText().toString() == null) {
                    return;
                }

                String uid = firebaseAuth.getCurrentUser().getUid();
                UserModel userModel = new UserModel();

                if(isPwdChecked(password.getText().toString(), passwordCheck.getText().toString()) == true
                        && firebaseAuth.getCurrentUser().isEmailVerified() == true) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(uid).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            SignupActivity.this.finish();
                        }

                    });

                    //RealTime Database에 User 추가
                    userModel.name = name.getText().toString();
                    userModel.emailAddress = emailAdd.getText().toString();
                    FirebaseDatabase.getInstance().getReference().child("Users").child(uid).setValue(userModel);
                    //DB에 Uid 추가
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", uid);

                } else if(isPwdChecked(password.getText().toString(), passwordCheck.getText().toString()) == false){
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
