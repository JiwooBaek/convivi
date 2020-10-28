package com.example.project_1.Adapter;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.Intent;
import android.media.Image;
>>>>>>> 15b8a7278eadaf5b5d9e643f6bde488d40307373
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.project_1.HomeFragment;
import com.example.project_1.HomeShareItem;
<<<<<<< HEAD
import com.example.project_1.MainActivity;
=======
import com.example.project_1.PopUpActivity;
>>>>>>> 15b8a7278eadaf5b5d9e643f6bde488d40307373
import com.example.project_1.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeShareAdapter extends RecyclerView.Adapter<HomeShareAdapter.Viewhloder> {
    Context context;
    ArrayList<HomeShareItem> arrayList;

    public HomeShareAdapter(Context context, ArrayList<HomeShareItem> arrayList) {
        this.context = context;
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
        Glide.with(holder.itemView.getContext())
                .load(arrayList.get(position).getProfile())
                .into(holder.profile);

        holder.title.setText(arrayList.get(position).getTitle());
        holder.adress.setText(arrayList.get(position).getAdress());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PopUpActivity.class);

                String titleStr = arrayList.get(position).getTitle();
                String addressStr = holder.adress.getText().toString();

                intent.putExtra("address", addressStr);
                intent.putExtra("title", titleStr);

                Toast.makeText(v.getContext(), "팝업창 띄우기", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class Viewhloder extends RecyclerView.ViewHolder {
        protected CircleImageView profile;
        protected TextView title;
        protected TextView adress;

        public Viewhloder(@NonNull View itemView) {
            super(itemView);
            this.profile = (CircleImageView) itemView.findViewById(R.id.profile);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.adress = (TextView) itemView.findViewById(R.id.adress);
        }
    }
}
