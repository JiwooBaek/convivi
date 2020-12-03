package com.example.project_1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_1.Item.HomeShareItem;
import com.example.project_1.SharePopUpActivity;
import com.example.project_1.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeShareAdapter extends RecyclerView.Adapter<HomeShareAdapter.Viewhloder> {
    ArrayList<HomeShareItem> arrayList;

    public HomeShareAdapter(ArrayList<HomeShareItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HomeShareAdapter.Viewhloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_share, parent, false);
        RecyclerView.ViewHolder holder = new Viewhloder(view);

        return (Viewhloder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeShareAdapter.Viewhloder holder, int position) {
        if (!((arrayList.get(position).getImageUrl()).equals("default"))) {
            Glide.with(holder.itemView.getContext())
                    .load(arrayList.get(position)
                            .getImageUrl()).into(holder.image);
        }
        String id = arrayList.get(position).getId();
        String profileImage = arrayList.get(position).getImageUrl();

        holder.title.setText(arrayList.get(position).getTitle());
        holder.adress.setText(arrayList.get(position).getAdress());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SharePopUpActivity.class);

                intent.putExtra("id", id);

//                Toast.makeText(v.getContext(), "팝업창 띄우기", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class Viewhloder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView title;
        protected TextView adress;

        public Viewhloder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.adress = (TextView) itemView.findViewById(R.id.adress);
        }
    }
}
