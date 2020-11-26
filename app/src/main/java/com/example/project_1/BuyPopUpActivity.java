package com.example.project_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;
import model.BuyModel;
import model.ChatModel;
import model.ChatUserModel;
import model.ShareModel;

public class BuyPopUpActivity extends Activity {

    TextView titleView;
    TextView addressView;
    TextView descriptionView;
    TextView hostView;
    TextView targetNumView;
    TextView currentNumView;
    ImageView profileImageView;
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
    private FirebaseDatabase database;
    private DatabaseReference ref_buy = FirebaseDatabase.getInstance().getReference().child("Buy");
    private DatabaseReference ref_share = FirebaseDatabase.getInstance().getReference().child("Share");

    String checkRoom = "";
    ShareModel shareModel;
    BuyModel buyModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_buy_popup);

        titleView = (TextView) findViewById(R.id.title);
        addressView = (TextView) findViewById(R.id.userAddress);
        descriptionView = (TextView) findViewById(R.id.description);
        profileImageView = (ImageView) findViewById(R.id.profile_image);
        hostView = (TextView) findViewById(R.id.hostName);
        targetNumView = (TextView) findViewById(R.id.targetNOP);
        currentNumView = (TextView) findViewById(R.id.currentNOP);
        hostLayout = (LinearLayout) findViewById(R.id.number_of_people);


        //데이터 가져오기
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        image = intent.getStringExtra("profileImage");

        // 데이터 설정하기

        if (id.substring(0, 1).equals("S")) {
            ref_share.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    shareModel = dataSnapshot.getValue(ShareModel.class);
                    uid = shareModel.host;
                    title = shareModel.title;
                    description = shareModel.description;

                    hostLayout.setVisibility(View.INVISIBLE);
                    titleView.setText(title);
                    descriptionView.setText(description);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (id.substring(0, 1).equals("B")) {
            if (id.substring(0, 1).equals("B")) {
                ref_buy.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        buyModel = dataSnapshot.getValue(BuyModel.class);
                        uid = buyModel.host;
                        title = buyModel.title;
                        description = buyModel.description;
                        targetNum = Integer.toString(buyModel.targetNOP);
                        currentNum = Integer.toString(buyModel.currentNOP);

                        titleView.setText(title);
                        descriptionView.setText(description);
                        targetNumView.setText(targetNum);
                        currentNumView.setText(currentNum);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } else {
                Toast.makeText(this, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            }

            //데이터 설정하기
            titleView.setText(title);
            descriptionView.setText(description);
            Glide.with(this).load(image).into(profileImageView);

            Button openChat;
            openChat = (Button) findViewById(R.id.chat_button);
            userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            // 채팅방 버튼 클릭시 이동
            openChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*

                    ChatUserModel chatUserModel = new ChatUserModel();
                    //기존 채팅방에 접속


                    ChatModel chatModel = new ChatModel();
                    chatModel.host = uid;
                    chatModel.roomId = shareModel.id;
                    database.getInstance().getReference("Share").child(shareModel.id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ShareModel shareModel = dataSnapshot.getValue(ShareModel.class);
                            uid = shareModel.host;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //chatModel.users.put(fuserUid, true);
                    //chatUserModel.users.put(userUid, true);

                    //FirebaseDatabase.getInstance().getReference().child("Chatlist").child(idNum).child("users").setValue(chatUserModel);
*/
                    //채팅방 클릭 시 이동
                    Intent intent = new Intent(BuyPopUpActivity.this, MessageActivity.class);
                    intent.putExtra("userid", uid);
                    intent.putExtra("chatid", id);
                    startActivity(intent);

                }
            });

        }
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

