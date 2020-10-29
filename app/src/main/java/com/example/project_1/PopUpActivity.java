package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import model.ChatlistModel;

public class PopUpActivity extends AppCompatActivity {

    TextView titleView;
    TextView addressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        titleView = (TextView) findViewById(R.id.title);
        addressView = (TextView) findViewById(R.id.userAddress);

        //UI 객체 생성

        //데이터 가져오기
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String address = intent.getStringExtra("address");

        //데이터 설정하기
        titleView.setText(title);
        addressView.setText(address);

        //닫기 버튼 클릭시 팝업 닫기
        Button close_button = (Button) findViewById(R.id.cancel_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button openChat;
        openChat = (Button) findViewById(R.id.chat_button);

        // 채팅방 버튼 클릭시 이동
        openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //채팅방 데베에 생성
                ChatlistModel chatlistModel = new ChatlistModel();
                chatlistModel.sender = FirebaseAuth.getInstance().getCurrentUser().getUid();
                chatlistModel.receiver = "hihi";
                FirebaseDatabase.getInstance().getReference().child("Chatlist").push().setValue(chatlistModel);

                //채팅방 클릭 시 이동
                Intent intent = new Intent(PopUpActivity.this, MessageActivity.class);
                intent.putExtra("userid", "hihi");
                startActivity(intent);
            }
        });

    }

    //바깥 레이어 클릭시 안닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}
