package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.project_1.Adapter.BuyListAdapter;
import com.example.project_1.Item.ListRowItem_Buy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import model.BuyModel;
import model.ImageModel;

public class BuyListActivity extends AppCompatActivity {
    private ArrayList<ListRowItem_Buy> items;
    private DatabaseReference ref_buy = FirebaseDatabase.getInstance().getReference().child("Buy");
    private DatabaseReference ref_buyImages = FirebaseDatabase.getInstance().getReference().child("BuyImages");
    private BuyListAdapter buyMoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        items = new ArrayList<>();
        buyMoreAdapter = new BuyListAdapter(this, R.layout.list_row_buy, items);


        ref_buyImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot imageDataSnapshot) {
                ref_buy.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot Buy : dataSnapshot.getChildren()){
                            BuyModel buyModel = Buy.getValue(BuyModel.class);
                            ImageModel imageModel = imageDataSnapshot.child(buyModel.id).getValue(ImageModel.class);

                            ListRowItem_Buy listRowItem_buy = new ListRowItem_Buy(imageModel.url, buyModel.title, buyModel.description, buyModel.currentNOP, buyModel.targetNOP);
                            items.add(listRowItem_buy);
                        }
                        buyMoreAdapter.notifyDataSetChanged();
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
        ListView lv = findViewById(R.id.list_item_buy);
        lv.setAdapter(buyMoreAdapter);


    }
}