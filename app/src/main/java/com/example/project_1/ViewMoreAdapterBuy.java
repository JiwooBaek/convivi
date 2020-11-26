package com.example.project_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_1.Item.ListRowItem_Buy;

import java.util.ArrayList;

public class ViewMoreAdapterBuy extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListRowItem_Buy> arrayList_buy;
    public ViewMoreAdapterBuy(Context context, int layout, ArrayList<ListRowItem_Buy> arrayList_buy) {
        this.context = context;
        this.layout = layout;
        this.arrayList_buy = arrayList_buy;
    }


    @Override
    public int getCount() {
        return arrayList_buy.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_buy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final int pos = position;
        if (view == null)
            view = View.inflate(context, layout, null);

        TextView title = view.findViewById(R.id.text_title2);
        title.setText(arrayList_buy.get(pos).getTitle());
        TextView description = view.findViewById(R.id.text_description2);
        description.setText(arrayList_buy.get(pos).getDescription());
        TextView textNOP = view.findViewById(R.id.text_nop);
        String nop;
        nop = "" + arrayList_buy.get(pos).getCurrentNOP() + " / " + arrayList_buy.get(pos).getTargetNOP();
        textNOP.setText(nop);

        ImageView image= view.findViewById(R.id.img_flag2);
        Glide.with(view.getContext())
                .load(arrayList_buy.get(position).getProfile())
                .into(image);

        return view;
    }
}
