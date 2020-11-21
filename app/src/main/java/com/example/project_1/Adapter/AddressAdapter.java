package com.example.project_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.AddressItem;
import com.example.project_1.R;

import java.util.ArrayList;

import model.addressSearch.Document;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Viewholder> {
    ArrayList<Document> items = new ArrayList<>();

    public AddressAdapter(ArrayList<Document> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        RecyclerView.ViewHolder holder = new Viewholder(view);
        return (Viewholder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.Viewholder holder, int position) {
        holder.address.setText(items.get(position).getAddressName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear(){
        items.clear();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        protected TextView address;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            this.address = (TextView) itemView.findViewById(R.id.address);
        }
    }
}
