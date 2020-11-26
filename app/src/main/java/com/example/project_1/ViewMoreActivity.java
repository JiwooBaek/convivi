package com.example.project_1;


import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_1.Item.ListRowItem_Share;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import model.ShareModel;
import model.UserModel;


public class ViewMoreActivity extends AppCompatActivity {
    private ArrayList<ListRowItem_Share> items;
    private DatabaseReference share = FirebaseDatabase.getInstance().getReference().child("Share");
    private DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Users");
    private ViewMoreAdapter viewMoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_more);
        items = new ArrayList<>();

        viewMoreAdapter = new ViewMoreAdapter(this, R.layout.list_row, items);

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userDataSnapshot) {
                share.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot Share : dataSnapshot.getChildren()){
                            ShareModel shareModel = Share.getValue(ShareModel.class);
                            UserModel userModel = userDataSnapshot.child(shareModel.host).getValue(UserModel.class);
                            ListRowItem_Share listRowItem = new ListRowItem_Share(userModel.imgURL, shareModel.title, shareModel.description);
                            items.add(listRowItem);
                        }
                        viewMoreAdapter.notifyDataSetChanged();
                        Collections.reverse(items); //역순으로 보여줌

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ListView lv = findViewById(R.id.list_item);
        lv.setAdapter(viewMoreAdapter);
    }
}
