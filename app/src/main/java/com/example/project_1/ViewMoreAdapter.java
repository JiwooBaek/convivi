package com.example.project_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.project_1.Item.ListRowItem_Share;

import java.util.ArrayList;

public class ViewMoreAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListRowItem_Share> arrayList;
    public ViewMoreAdapter(Context context, int layout, ArrayList<ListRowItem_Share> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
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
        final int pos = position;
        if (view == null)
            view = View.inflate(context, layout, null);

        TextView title = view.findViewById(R.id.text_title);
        title.setText(arrayList.get(pos).getTitle());
        TextView description = view.findViewById(R.id.text_description);
        description.setText(arrayList.get(pos).getDescription());
        ImageView image= view.findViewById(R.id.img_flag);
        Glide.with(view.getContext())
                .load(arrayList.get(position).getProfile())
                .into(image);

        return view;
    }
}

