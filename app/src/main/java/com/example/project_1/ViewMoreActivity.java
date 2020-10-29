package com.example.project_1;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import model.BuyModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;

import model.ShareModel;
import model.UserModel;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ViewMoreActivity extends AppCompatActivity {

    private ArrayList<ListRowItem> items;
    private DatabaseReference share = FirebaseDatabase.getInstance().getReference().child("Share");
    private DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Users");
    private ViewMoreAdapter viewMoreAdapter;
=======

public class ViewMoreActivity extends AppCompatActivity {

    private ArrayList<BuyModel> items;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Buy");

>>>>>>> 28885f4ecf6077734b2ae3d2373007f8e914760b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_more);
        items = new ArrayList<>();

<<<<<<< HEAD
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
                            ListRowItem listRowItem = new ListRowItem(userModel.imgURL, shareModel.title, shareModel.description);
                            items.add(listRowItem);

                        }
                        viewMoreAdapter.notifyDataSetChanged();
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
=======
    //    items.add(new )
>>>>>>> 28885f4ecf6077734b2ae3d2373007f8e914760b


        ListView lv = findViewById(R.id.list_item);
        lv.setAdapter(viewMoreAdapter);



    }
}
