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
import model.ChatModel;
import model.UserModel;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    FirebaseUser fuser;
    DatabaseReference reference;

    ImageButton btn_send;
    EditText text_send;

    MessageAdapter messageAdapter;

    List<ChatModel.Comment> mchat;

    RecyclerView recyclerView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        reference  = FirebaseDatabase.getInstance().getReference().child("Chatlist");


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



        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        final String chatid = intent.getStringExtra("chatid");
        //putExtra 받아오기
        fuser = FirebaseAuth.getInstance().getCurrentUser();


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = text_send.getText().toString();
                //msg가 비어있지 않으면 Uid 보내고
                if (!msg.equals("")) {
                    sendMessage(fuser.getUid(), chatid, msg);
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
                username.setText(user.name);
                /*이 부분 나중에 고쳐야 함
                * if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
//                    Glide.with(MessageActivity.this).load(user.getImageURL()).into(profile_image);
                }*/
                profile_image.setImageResource(R.mipmap.ic_launcher);

                readMessages(chatid, "default");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String myId, String chatid, String message) {
        /*시도 해보는 중 1
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        */

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        ChatModel.Comment chatModel = new ChatModel.Comment();
        chatModel.uid = myId;
        chatModel.message = message;
        reference.child("Chatlist").child(chatid).child("comments").push().setValue(chatModel);
    }

    private void readMessages(final String chatid, final String imageurl) {
        mchat = new ArrayList<>();

        messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
        recyclerView.setAdapter(messageAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatModel.Comment comment = snapshot.getValue(ChatModel.Comment.class);

                    mchat.add(comment);
                    messageAdapter.notifyDataSetChanged();

<<<<<<< HEAD
                }
=======
                    messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
>>>>>>> e73e62d89d3d6af178f09325bdca1bd9041ef4e9

                }
                messageAdapter.notifyDataSetChanged();
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}