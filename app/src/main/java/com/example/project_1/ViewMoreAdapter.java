//package com.example.project_1;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.List;
//
//import model.ScriptModel;
//
//public class ViewMoreAdapter extends RecyclerView.Adapter<ViewMoreAdapter.MyViewHolder>{
//
//    private RecyclerView recyclerView;
//    private LinearLayout linearLayout;
//
//    private  String[] mDataset;
//    List contents;
//    public ViewMoreAdapter() {
//        contents = new
//    }
//
//
//    public  class MyViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView textView;
//        public MyViewHolder(TextView v) {
//            super(v);
//            textView = v;
//        }
//    }
//    public ViewMoreAdapter(String[] myDataset){
//        mDataset = myDataset;
//    }
//
//
//
//    @NonNull
//    @Override
//    public ViewMoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent,false);
//
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position ) {
//        Glide.with(holder.textView).load()
//        holder.textView.setText(mDataset[position]);
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return mDataset.length;
//    }
//}
