package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.project_1.Adapter.MessageAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.BuyModel;
import model.ChatModel;
import model.ShareModel;
import model.UserModel;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    FirebaseUser fuser;
    DatabaseReference reference;
    DatabaseReference user_ref;

    ImageButton btn_send;
    EditText text_send;

    MessageAdapter messageAdapter;

    List<ChatModel.Comment> mchat;

    String theChatTitle;

    RecyclerView recyclerView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        reference  = FirebaseDatabase.getInstance().getReference().child("Chatlist");
        user_ref  = FirebaseDatabase.getInstance().getReference().child("Users");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        profile_image = findViewById(R.id.imageView);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        final String chatid = intent.getStringExtra("chatid");
        //putExtra 받아오기
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        chatTitle(chatid, username);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = text_send.getText().toString();
                String profilUrl = user_ref.child(fuser.getUid()).child("imgURL").toString();
                //msg가 비어있지 않으면 Uid 보내고
                if (!msg.equals("")) {
                    sendMessage(fuser.getUid(), chatid, msg, profilUrl);
                } else {
                    Toast.makeText(MessageActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
                //다시 비우는 건가
                text_send.setText("");
            }
        });
        //시험으로 추가해보는 중
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);

                //채팅창 상단 이름 설정
                //username.setText(user.name);
                if (user.getImgURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(MessageActivity.this).load(user.getImgURL()).into(profile_image);
                }

                readMessages(chatid, "default");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String myId, String chatid, String message, String profilUrl) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        ChatModel.Comment chatModel = new ChatModel.Comment();
        chatModel.uid = myId;
        chatModel.message = message;
        chatModel.profileUrl = profilUrl;
        reference.child("Chatlist").child(chatid).child("comments").push().setValue(chatModel);
    }

    private void readMessages(final String chatid, final String imageurl) {
        mchat = new ArrayList<>();

        messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
        recyclerView.setAdapter(messageAdapter);

        reference = FirebaseDatabase.getInstance().getReference().child("Chatlist").child(chatid).child("comments");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatModel.Comment comment = snapshot.getValue(ChatModel.Comment.class);
                    mchat.add(comment);
                }
                    messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                    messageAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void chatTitle(final String roomid, final TextView chat_title) {
        if (roomid.substring(0, 1).equals("S")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Share").child(roomid);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ShareModel shareModel = dataSnapshot.getValue(ShareModel.class);
                    theChatTitle = shareModel.title;
                    chat_title.setText(theChatTitle);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if (roomid.substring(0, 1).equals("B")) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Buy").child(roomid);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    BuyModel buyModel = dataSnapshot.getValue(BuyModel.class);
                    theChatTitle = buyModel.title;
                    chat_title.setText(theChatTitle);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}