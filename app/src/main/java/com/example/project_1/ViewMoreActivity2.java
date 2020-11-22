package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.project_1.Item.ListRowItem_Buy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import model.BuyModel;
import model.UserModel;

public class ViewMoreActivity2 extends AppCompatActivity {
    private ArrayList<ListRowItem_Buy> items;
    private DatabaseReference buy = FirebaseDatabase.getInstance().getReference().child("Buy");
    private DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Users");
    private ViewMoreAdapterBuy viewMoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more2);

        items = new ArrayList<>();
        viewMoreAdapter = new ViewMoreAdapterBuy(this, R.layout.list_row_buy, items);


        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userDataSnapshot) {
                buy.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot Buy : dataSnapshot.getChildren()){
                            BuyModel buyModel = Buy.getValue(BuyModel.class);
                            UserModel userModel = userDataSnapshot.child(buyModel.host).getValue(UserModel.class);
                            ListRowItem_Buy listRowItem_buy = new ListRowItem_Buy(userModel.imgURL, buyModel.title, buyModel.description, buyModel.currentNOP, buyModel.targetNOP);
                            items.add(listRowItem_buy);
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
        ListView lv = findViewById(R.id.list_item_buy);
        lv.setAdapter(viewMoreAdapter);


    }
}