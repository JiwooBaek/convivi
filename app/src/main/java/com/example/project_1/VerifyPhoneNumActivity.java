package com.example.project_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import model.UserModel;

public class VerifyPhoneNumActivity extends AppCompatActivity {

//    private String phoneNum = "1122223333";
//    private String verificationCode = "123456";
    private String verificationId;
    private String uid;
    private TextView message;
    private TextView codeMessage;
    private TextView verifyMessage;
    private TextInputEditText phoneNumView;
    private TextInputEditText codeView;
    private Button sendCode;
    private Button button;
    private RelativeLayout container;
    private String code;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        verifyMessage = (TextView) findViewById(R.id.verifyMessage);
        message = (TextView) findViewById(R.id.verifyMessage);
        codeMessage = (TextView) findViewById(R.id.codeMessage);
        phoneNumView = (TextInputEditText) findViewById(R.id.PhoneNumber);
        codeView = (TextInputEditText) findViewById(R.id.verifyCode);
        sendCode = (Button) findViewById(R.id.sendCode);
        button = (Button) findViewById(R.id.Button);
        container = (RelativeLayout) findViewById(R.id.container);

        //SMS 수신권한
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);   // SEND_SMS 권한이 있는지 체크
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS 권한 주어져 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SMS 권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "SMS 권한 설명 필요함", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 1);
            }
        }

       //현재 유저 uid 값 받아오기
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        //인증코드 뷰 비활성화
        container.setVisibility(View.INVISIBLE);
        codeMessage.setVisibility(View.INVISIBLE);
        codeView.setVisibility(View.INVISIBLE);

        //가상번호 자동입력
//        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNum, verificationCode);

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "82" + phoneNumView.getText().toString().trim();

                // 입력값 오류
                if(num.isEmpty() || num.length() < 10) {
                    phoneNumView.setError("유효하지 않은 값입니다");
                    phoneNumView.requestFocus();
                    return;
                }

                code = createCode();

                sendVerificationCode(num, code);
                container.setVisibility(View.VISIBLE);
                codeMessage.setVisibility(View.VISIBLE);
                codeView.setVisibility(View.VISIBLE);
                verifyMessage.setVisibility(View.INVISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeView.getText().toString().trim();

                // 입력값 오류
                if(code.isEmpty() || code.length() < 4) {
                    Toast.makeText(getApplicationContext(), "유효하지 않은 값입니다", Toast.LENGTH_SHORT).show();
                }
                verifyCode(code);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 승인함", Toast.LENGTH_LONG).show();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 거부함", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "SMS 수신 권한을 부여받지 못함", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }


    private void sendVerificationCode(String phoneNum, String num) {
        String msg = "[" + num + "]" + " 전화번호 인증 코드를 입력하세요!";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "인증코드가 전송되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "인증코드 전송이 실패하였습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+82" + phoneNum,
//                60,
//                TimeUnit.SECONDS,
//                this,
//                callbacks);
    }

//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
//            callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//            // SMS통해서 코드 받아오기
//            String code = phoneAuthCredential.getSmsCode();
//
//            if(code != null) {
//                codeView.setText(code);
//                verifyCode(code);
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            verificationId = s;
//        }
//    };

    private void verifyCode(String num) {
        if(num.equals(code)) {
            FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("phoneAuthFlag").setValue(true);
            Toast.makeText(getApplicationContext(), "인증이 완료되었습니다\n환영합니다!", Toast.LENGTH_SHORT).show();
            //메인액티비티 이동
            startActivity(new Intent(VerifyPhoneNumActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        } else {
            Toast.makeText(getApplicationContext(), "일치하지 않는 코드입니다.", Toast.LENGTH_SHORT).show();
        }
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//        signInWithCredential(credential);
    }

    private String createCode() {
        //4자리 랜덤코드 생성
        int num = (int) (Math.random() * 10000 + 1);
        NumberFormat formatter = new DecimalFormat("0000");
        String randCode = formatter.format(num);
        return randCode;
    }

//    private void signInWithCredential(PhoneAuthCredential credential) {
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()) {
//                            FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("phoneAuthFlag").setValue(true);
//                            startActivity(new Intent(VerifyPhoneNumActivity.this, MainActivity.class)
//                            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
//
//                        } else {
//                            Toast.makeText(VerifyPhoneNumActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
}
