package com.example.project_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import model.ChatModel;
import model.ShareModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_1.Adapter.ChatAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

//    private List<UserModel> mUser;
    FirebaseUser fuser;
    DatabaseReference reference;
    FirebaseDatabase database;

    private List<ChatModel> mChatList;
    //private List<ChatModel> chatList;
    private List<String> userList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        final String fuserId = fuser.getUid();
        String chatTitle;

        userList = new ArrayList<>();
        mChatList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chatlist");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChatList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatModel chatList = snapshot.getValue(ChatModel.class);
                    if (chatList.guest.equals(fuserId) || chatList.host.equals(fuserId)) {
                        mChatList.add(chatList);
                    }
/*
                    database.getInstance().getReference("Share").child(chatList.roomId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ShareModel shareModel = dataSnapshot.getValue(ShareModel.class);
                            chatTitle = shareModel.title;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/

                }

                chatAdapter = new ChatAdapter(getContext(), mChatList);
                recyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}