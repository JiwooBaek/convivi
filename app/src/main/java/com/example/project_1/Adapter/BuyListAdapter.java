package com.example.project_1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_1.Item.ListRowItem_Buy;
import com.example.project_1.R;

import java.util.ArrayList;

public class BuyListAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListRowItem_Buy> arrayList;
    public BuyListAdapter(Context context, int layout, ArrayList<ListRowItem_Buy> arrayList_buy) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList_buy;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view != null) {
            TextView title = view.findViewById(R.id.text_title2);
            TextView description = view.findViewById(R.id.text_description2);
            TextView textNOP = view.findViewById(R.id.text_nop);
            ImageView image= view.findViewById(R.id.img_flag2);

            title.setText(arrayList.get(position).getTitle());
            description.setText(arrayList.get(position).getDescription());

            String nop = "" + arrayList.get(position).getCurrentNOP() + " / " + arrayList.get(position).getTargetNOP();
            textNOP.setText(nop);

            if (!(arrayList.get(position).getImage()).equals("default")) {
                Glide.with(view.getContext()).load(arrayList.get(position).getImage()).into(image);
            }
        } else {
            view = View.inflate(context, layout, null);
        }
        return view;
    }
}
