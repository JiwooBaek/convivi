package com.example.project_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.project_1.BuyPopUpActivity;
import com.example.project_1.Item.ListRowItem_Share;
import com.example.project_1.R;
import com.example.project_1.SharePopUpActivity;

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
        final int pos = position;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_row_share, parent, false);
        }
        String id = arrayList.get(pos).getId();
        TextView title = view.findViewById(R.id.text_title);
        TextView description = view.findViewById(R.id.text_description);
        ImageView image = view.findViewById(R.id.img_flag);

        title.setText(arrayList.get(pos).getTitle());
        description.setText(arrayList.get(pos).getDescription());
        if (!((arrayList.get(pos).getImage()).equals("default"))) {
            Glide.with(view.getContext()).load(arrayList.get(pos).getImage()).into(image);
        }

        LinearLayout item = (LinearLayout) view.findViewById(R.id.item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SharePopUpActivity.class);

                intent.putExtra("id", id);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });

        return view;
    }

}

