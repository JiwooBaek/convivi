package com.example.project_1;


import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_1.Adapter.ShareListAdapter;
import com.example.project_1.Item.ListRowItem_Share;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import model.ImageModel;
import model.ShareModel;


public class ShareListActivity extends AppCompatActivity {
    private ArrayList<ListRowItem_Share> items;
    private DatabaseReference ref_share = FirebaseDatabase.getInstance().getReference().child("Share");
    private DatabaseReference ref_shareImages = FirebaseDatabase.getInstance().getReference().child("ShareImages");
    private ShareListAdapter shareListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share_list);
        items = new ArrayList<>();

        shareListAdapter = new ShareListAdapter(this, R.layout.list_row_share, items);

        ref_shareImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot imageDataSnapshot) {
                ref_share.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot Share : dataSnapshot.getChildren()){
                            ShareModel shareModel = Share.getValue(ShareModel.class);
                            ImageModel imageModel = imageDataSnapshot.child(shareModel.id).getValue(ImageModel.class);

                            ListRowItem_Share listRowItem = new ListRowItem_Share(shareModel.id, imageModel.getUrl(), shareModel.title, shareModel.description);
                            items.add(listRowItem);
                        }
                        shareListAdapter.notifyDataSetChanged();
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

        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(shareListAdapter);
    }
}
