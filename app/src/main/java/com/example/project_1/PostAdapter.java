package com.example.project_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import model.BuyModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder> {

    Context context;
    List<BuyModel> buyModellist;
    String Uid;

    public PostAdapter(Context context, List<BuyModel> buyModels) {
        this.context = context;
        this.buyModellist = buyModels;
        Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    @NonNull
    //아이템 뷰를 관리하는 viewHolder 객체 생성
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new PostAdapter.MyHolder(view);
    }
    //position에 해당하는 데이터를 viewholder가 관리하는 view에 바인딩
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final String title = buyModellist.get(i).title;
        final String description = buyModellist.get(i).description;
        final String host = buyModellist.get(i).host;

        Log.d(host, " " + title+" "+ description+" ");
        if(host!=null) {

            myHolder.title.setText(title);
            myHolder.description.setText(description);
            myHolder.image.setImageResource(R.drawable.default_1);
        }
    }

    @Override
    public int getItemCount() {

        return buyModellist.size();
    }

    //각 list에 들어갈 객체의 맴버 변수
    class MyHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        ImageView image;

        public MyHolder(View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.text_title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.img_flag);


        }


    }

}
 