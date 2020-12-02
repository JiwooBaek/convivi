package com.example.project_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.MessageActivity;
import com.example.project_1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import model.BuyModel;
import model.ChatModel;
import model.ShareModel;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context mContext;
    private List<ChatModel> mChats;

    String theLastMessage;
    String theChatTitle;

    public ChatAdapter(Context mContext, List<ChatModel> mChats) {
        this.mContext = mContext;
        this.mChats = mChats;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item, parent, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChatModel chatModel = mChats.get(position);


        lastMessgae(chatModel.roomId, holder.description);
        chatTitle(chatModel.roomId, holder.chat_title);
        numberOfPeople(chatModel.roomId, holder.number_of_people);
        //holder.chat_title.setText(chatModel.host);
        holder.write_image.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", chatModel.host);
                intent.putExtra("chatid", chatModel.roomId);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView chat_title;
        public TextView number_of_people;
        public TextView description;
        public ImageView write_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_title = itemView.findViewById(R.id.chat_title);
            number_of_people = itemView.findViewById(R.id.number_of_people);
            description = itemView.findViewById(R.id.description);
            write_image = itemView.findViewById(R.id.write_image);
        }
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
    private void lastMessgae(final String roomid, final TextView last_msg) {
        theLastMessage = "default";
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(roomid).child("comments");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatModel.Comment comment = snapshot.getValue(ChatModel.Comment.class);
                    theLastMessage = comment.message;
                }

                switch (theLastMessage) {
                    case "default" :
                        last_msg.setText("No message yet.");
                        break;
                    default :
                        last_msg.setText(theLastMessage);
                        break;
                }

                theLastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void numberOfPeople(final String roomid, final TextView number_of_people) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(roomid);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                if (chatModel.guest.equals("null")) {
                    number_of_people.setText("1");
                } else {
                    number_of_people.setText("2");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
