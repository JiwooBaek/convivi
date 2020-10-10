//package com.example.project_1;
//
//import android.media.Image;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class DataAdapter extends BaseAdapter {
//
//    ArrayList<Data> datas;
//    LayoutInflater inflater;
//
//    public DataAdapter(LayoutInflater inflater, ArrayList<Data> datas) {
//        this.datas = datas;
//        this.inflater = inflater;
//    }
//
//    @Override
//    public int getCount() {
//        return datas.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return datas.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.list_row, null);
//        }
//        TextView text_name=(TextView)convertView.findViewById(R.id.text_name);
//        TextView text_title = (TextView)convertView.findViewById(R.id.text_title);
//        ImageView img_flag = (ImageView)convertView.findViewById(R.id.img_flag);
//
//        text_name.setText(datas.get(position).getName());
//        text_title.setText(datas.get(position).getTitle());
//        img_flag.setImageResource(datas.get(position).getImgld());
//
//        return  convertView;
//    }
//}
