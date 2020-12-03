package com.example.project_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import model.BuyModel;
import model.ImageModel;

public class BuyPopUpActivity extends Activity {

    TextView titleView;
    TextView addressView;
    TextView descriptionView;
    TextView hostView;
    TextView targetNumView;
    TextView currentNumView;
    ImageView imageView;
    LinearLayout hostLayout;
    String title;
    String address;
    String image;
    String description;
    String id;
    String hostName;
    String targetNum;
    String currentNum;
    String uid;
    String userUid;
    Uri imageUri;
    private FirebaseDatabase database;
    private DatabaseReference ref_buy = FirebaseDatabase.getInstance().getReference().child("Buy");
    private DatabaseReference ref_buyImage = FirebaseDatabase.getInstance().getReference().child("BuyImages");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_buy_popup);

        titleView = (TextView) findViewById(R.id.title);
        addressView = (TextView) findViewById(R.id.userAddress);
        descriptionView = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);
        hostView = (TextView) findViewById(R.id.hostName);
        targetNumView = (TextView) findViewById(R.id.targetNOP);
        currentNumView = (TextView) findViewById(R.id.currentNOP);
        hostLayout = (LinearLayout) findViewById(R.id.number_of_people);


        //데이터 가져오기
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        //데이터 설정하기
        ref_buyImage.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot imageSnapshot) {
                ref_buy.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ImageModel imageModel = imageSnapshot.getValue(ImageModel.class);
                        BuyModel buyModel = dataSnapshot.getValue(BuyModel.class);

                        imageUri = Uri.parse(imageModel.url);
                        uid = buyModel.host;
                        title = buyModel.title;
                        description = buyModel.description;
                        targetNum = Integer.toString(buyModel.targetNOP);
                        currentNum = Integer.toString(buyModel.currentNOP);

                        if (!(imageModel.getUrl()).equals("default")) {
                            Glide.with(getApplicationContext()).load(imageModel.getUrl()).into(imageView);
                        }
                        titleView.setText(title);
                        descriptionView.setText(description);
                        targetNumView.setText(targetNum);
                        currentNumView.setText(currentNum);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button openChat;
        openChat = (Button) findViewById(R.id.chat_button);
        userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // 채팅방 버튼 클릭시 이동
        openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyPopUpActivity.this, MessageActivity.class);
                intent.putExtra("userid", uid);
                intent.putExtra("chatid", id);
                startActivity(intent);

            }
        });

    }
}
    //바깥 레이어 클릭시 안닫히게
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//            return false;
//        }
//        return true;
//    }

