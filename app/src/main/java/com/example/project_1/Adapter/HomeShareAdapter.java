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

import com.example.project_1.HomeShareItem;
import com.example.project_1.PopUpActivity;
import com.example.project_1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

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
        holder.profile.setImageResource(arrayList.get(position).getProfile());
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
        protected ImageView profile;
        protected TextView title;
        protected TextView adress;

        public Viewhloder(@NonNull View itemView) {
            super(itemView);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.adress = (TextView) itemView.findViewById(R.id.adress);
        }
    }
}
