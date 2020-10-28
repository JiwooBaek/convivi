package com.example.project_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import model.ShareModel;

public class PopUpActivity extends Activity {

    TextView titleView;
    TextView addressView;
    TextView descriptionView;
    String idNum;
    String uid;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        titleView = (TextView) findViewById(R.id.title);
        addressView = (TextView) findViewById(R.id.userAddress);
        descriptionView = (TextView) findViewById(R.id.description);

        //데이터 가져오기
        Intent intent = getIntent();
        idNum = Long.toString(intent.getLongExtra("idNum", 0));

        //데이터 설정하기
        database.getInstance().getReference("Share").child(idNum).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ShareModel shareModel = dataSnapshot.getValue(ShareModel.class);
                uid = shareModel.host;
                titleView.setText(shareModel.title);
                descriptionView.setText(shareModel.description);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
