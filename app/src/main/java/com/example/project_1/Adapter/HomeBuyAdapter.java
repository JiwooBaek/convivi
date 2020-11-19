package com.example.project_1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_1.BuyPopUpActivity;
import com.example.project_1.HomeBuyItem;
import com.example.project_1.SharePopUpActivity;
import com.example.project_1.R;

import java.util.ArrayList;

public class HomeBuyAdapter extends RecyclerView.Adapter<HomeBuyAdapter.ViewHolder> {

    ArrayList<HomeBuyItem> arrayList;

    public HomeBuyAdapter(ArrayList<HomeBuyItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_buy, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(arrayList.get(position).getProfile())
                .into(holder.profile);

        String id = arrayList.get(position).getId();
        String titleStr = arrayList.get(position).getTitle();
        String addressStr = arrayList.get(position).getAdress();
        String profileImage = arrayList.get(position).getProfile();

        holder.title.setText(arrayList.get(position).getTitle());
        holder.adress.setText(arrayList.get(position).getAdress());
        holder.currentNOP.setText(arrayList.get(position).getCurrentNOP());
        holder.targetNOP.setText(arrayList.get(position).getTargetNOP());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BuyPopUpActivity .class);

                intent.putExtra("id", id);
                intent.putExtra("profileImage", profileImage);
                intent.putExtra("address", addressStr);
                intent.putExtra("title", titleStr);

                Toast.makeText(v.getContext(), "팝업창 띄우기", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView profile;
        protected TextView title;
        protected TextView adress;
        protected TextView currentNOP;
        protected TextView targetNOP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.adress = (TextView) itemView.findViewById(R.id.adress);
            this.currentNOP = (TextView) itemView.findViewById(R.id.currentNOP);
            this.targetNOP = (TextView) itemView.findViewById(R.id.targetNOP);
        }
    }
}
