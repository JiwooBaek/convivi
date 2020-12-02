package com.example.project_1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.project_1.Item.ListRowItem_Share;
import com.example.project_1.R;

import java.util.ArrayList;

public class ShareListAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListRowItem_Share> arrayList;
    public ShareListAdapter(Context context, int layout, ArrayList<ListRowItem_Share> arrayList) {
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
        if (view != null) {
            TextView title = view.findViewById(R.id.text_title);
            TextView description = view.findViewById(R.id.text_description);
            ImageView image= view.findViewById(R.id.img_flag);

            title.setText(arrayList.get(position).getTitle());
            description.setText(arrayList.get(position).getDescription());
            if (!(arrayList.get(position).getImage()).equals("default")) {
                Glide.with(view.getContext()).load(arrayList.get(position).getImage()).into(image);
            }
        } else {
            view = View.inflate(context, layout, null);
        }
        return view;
    }
}

