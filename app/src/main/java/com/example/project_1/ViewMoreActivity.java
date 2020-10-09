package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.renderscript.Script;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import model.ScriptModel;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ViewMoreActivity extends AppCompatActivity {

    ScriptModel scriptModel = new ScriptModel();
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ScriptModel> scriptModels = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private DownloadManager.Query mRef;
    private  RecyclerView.LayoutManager layoutManager;
    String[] myDataset;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_more);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);





        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Buy").child("Buy").child("0000");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                ScriptModel value = dataSnapshot.getValue(ScriptModel.class);
                String key = dataSnapshot.getKey();
                if(previousChildName == NULL){
                    scriptModels.add(0, value);
                    strings.add(0, key);
                }else {
                    int previousIndex = strings.indexOf(previousChildName);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == scriptModels.size()) {
                        scriptModels.add(value);
                        strings.add(key);
                    } else {
                        scriptModels.add(nextIndex, value);
                        strings.add(nextIndex, key);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                ScriptModel value = dataSnapshot.getValue(ScriptModel.class);
                int index = strings.indexOf(key);
                scriptModels.set(index, value);

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = strings.indexOf(key);

                strings.remove(index);
                scriptModels.remove(index);

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // A model changed position in the list. Update our list accordingly
                String key = dataSnapshot.getKey();
                ScriptModel newModel = dataSnapshot.getValue(ScriptModel.class);
                int index = strings.indexOf(key);
                scriptModels.remove(index);
                strings.remove(index);
                if (previousChildName == null) {
                    scriptModels.add(0, newModel);
                    strings.add(0, key);
                } else {
                    int previousIndex = strings.indexOf(previousChildName);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == scriptModels.size()) {
                        scriptModels.add(newModel);
                        strings.add(key);
                    } else {
                        scriptModels.add(nextIndex, newModel);
                        strings.add(nextIndex, key);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView text_description;
        ImageView img_flag;


        @SuppressLint("ResourceType") //수정
        public MyViewHolder(View itemView) {
            super(itemView);
            text_title =  itemView.findViewById(R.id.text_title);
            text_description =  itemView.findViewById(R.id.text_description);
            img_flag =  itemView.findViewById(R.drawable.default_1);

        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.list_row, null);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ScriptModel scriptModel = scriptModels.get(position);

            holder.text_title.setText(scriptModel.title);
            holder.text_description.setText(scriptModel.description);
            holder.img_flag.setImageResource(scriptModel.imgld);

            //빼야하나?..
            Glide.with(ViewMoreActivity.this).load(scriptModel);
        }

        @Override
        public int getItemCount() {
            return scriptModels.size();
        }
    }
}