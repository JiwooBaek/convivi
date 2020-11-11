package com.example.project_1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyPhoneNumber  extends AppCompatActivity {

    private String phoneNum = "821122223333";
    private String verificationCode = "123456";

    private TextView message;
    private TextView codeMessage;
    private TextInputEditText phoneNumView;
    private TextInputEditText codeView;
    private Button sendCode;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();
    PhoneAuthCredential credential = PhoneAuthProvider.getCredential()

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_verify_phone);

        message = (TextView) findViewById(R.id.verifyMessage);
        codeMessage = (TextView) findViewById(R.id.codeMessage);
        phoneNumView = (TextInputEditText) findViewById(R.id.PhoneNumber);
        codeView = (TextInputEditText) findViewById(R.id.verifyCode);
        sendCode = (Button) findViewById(R.id.sendCode);

        // 인증코드 뷰 비활성화
        codeMessage.setVisibility(View.INVISIBLE);
        codeView.setVisibility(View.INVISIBLE);

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPhoneNumber(phoneNum, verificationCode);
            }
        });

        // 가상번호 자동입력
//        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNum, verificationCode);


    }

    private void verifyPhoneNumber(String num, String code) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(num, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                codeMessage.setVisibility(View.VISIBLE);
                codeView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
        });
    }


}
