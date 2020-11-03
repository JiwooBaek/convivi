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

import java.util.List;

import model.ChatModel;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context mContext;
    private List<ChatModel> mChats;

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

        //글 제목으로 바꾸는 게 좋겠음. 일단은 host 이름으로
        holder.chat_title.setText(chatModel.host);
        holder.write_image.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", chatModel.host);
                intent.putExtra("chatid", chatModel.roomNumber);

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
}
