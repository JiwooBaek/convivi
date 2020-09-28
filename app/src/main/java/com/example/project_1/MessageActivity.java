package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project_1.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    FirebaseUser fuser;
    DatabaseReference reference;

    //메시지 써지나 시험 용도
    FirebaseDatabase database;
    DatabaseReference mRef;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Database에 메시지 쓰기
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("message");

        mRef.setValue("Test message.");
        //왜인지 안 변하고 있음.

        //1. recyclerView - loop
        //2. Database에 내용을 넣는다.
        //3. 상대방에게 채팅 내용이 보이게 한다.

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.userName);

        intent = getIntent();
        String userid = intent.getStringExtra("userid");

        //Database Users 정보 가져오기
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);


        //뭔지 모르겠는데 그 영어 chatapp 영상에서 만들라해서 만듦
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                   // Glide.with(MessageActivity.this).load(user.getImageURL()).into(profile_image); Glide도 이미지 로드 프로그램같은 건 듯 일단 알아보기
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}