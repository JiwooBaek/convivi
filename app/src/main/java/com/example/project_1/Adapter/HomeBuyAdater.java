package com.example.project_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.HomeBuyItem;
import com.example.project_1.PopUpActivity;
import com.example.project_1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeBuyAdater extends RecyclerView.Adapter<HomeBuyAdater.ViewHolder> {
    Context context;
    ArrayList<HomeBuyItem> arrayList;

    public HomeBuyAdater(Context context, ArrayList<HomeBuyItem> arrayList) {
        this.context = context;
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
        holder.profile.setImageResource(arrayList.get(position).getProfile());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.adress.setText(arrayList.get(position).getAdress());
        holder.currentNOP.setText(arrayList.get(position).getCurrentNOP());
        holder.targetNOP.setText(arrayList.get(position).getTargetNOP());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "팝업창 띄우기", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, PopUpActivity.class));

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
