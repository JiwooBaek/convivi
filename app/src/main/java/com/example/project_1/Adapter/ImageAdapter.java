package com.example.project_1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.project_1.R;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project_1.Item.ImageItem;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<ImageItem> list;
    private Context context;

    public ImageAdapter(ArrayList<ImageItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imageUri = list.get(position).getImageUri();
        list.get(position).setImageUrl(imageUri);

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImageUrl())
                .into(holder.image);

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(holder.itemView.getContext().getContentResolver(), imageUri);
            holder.image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
