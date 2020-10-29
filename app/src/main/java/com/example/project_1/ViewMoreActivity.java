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

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import model.BuyModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewMoreActivity extends AppCompatActivity {

    private ArrayList<BuyModel> items;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Buy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_more);
        items = new ArrayList<>();

        items.add(new )


        ViewMoreAdapter viewMoreAdapter = new ViewMoreAdapter(this, R.layout.list_row, items);
        ListView lv = findViewById(R.id.list_item);
        lv.setAdapter(viewMoreAdapter);



    }
}
