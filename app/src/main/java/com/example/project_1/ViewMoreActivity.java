package com.example.project_1;
/*
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ViewMoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    PostAdapter adapter;

    DatabaseReference mbase;
    ArrayList<ScriptModel> arrayList;
    ScriptModel scriptModel;


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReferenceFromUrl("gs://project-75936.appspot.com");
    StorageReference pathReference = storageReference.child("photos/default_1.png");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        adapter = new PostAdapter(this, arrayList);



        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        mbase = FirebaseDatabase.getInstance().getReference("Buy").child("0000");
        scriptModel = new ScriptModel()
        arrayList.add()

        FirebaseRecyclerOptions<ScriptModel> options = new FirebaseRecyclerOptions.Builder<ScriptModel>().setQuery(mbase, ScriptModel.class).build();

        //recyclerView.setAdapter(adapter);
    }


}*/

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ViewMoreActivity extends AppCompatActivity {

    ScriptModel scriptModel = new ScriptModel();
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<ScriptModel> scriptModels = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private ArrayList arrayList = new ArrayList();
    private DownloadManager.Query mRef;
   // private  RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;
    String[] myDataset;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Buy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        setContentView(R.layout.activity_view_more);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(scriptModel);
        recyclerView.setAdapter(myAdapter);
*/
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycler_view);

        myAdapter = new MyAdapter(arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);


        arrayList.add(scriptModels);

        myAdapter.notifyDataSetChanged();




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
            this.text_title =  itemView.findViewById(R.id.text_title);
            this.text_description =  itemView.findViewById(R.id.text_description);
            this.img_flag =  itemView.findViewById(R.drawable.default_1);

        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        ArrayList<ScriptModel> arrayList;

        MyAdapter(ArrayList<ScriptModel> arrayList){
            this.arrayList = arrayList;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.list_row, null);

            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text_title.setText(scriptModel.title);
            holder.text_description.setText(scriptModel.description);
            holder.img_flag.setImageResource(scriptModel.image);


            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "팝업창 띄우기", Toast.LENGTH_SHORT).show();
                }
            });

            //빼야하나?..
           // Glide.with(ViewMoreActivity.this).load(scriptModel);
        }

        @Override
        public int getItemCount() {
            return scriptModels.size();
        }

    }
}
