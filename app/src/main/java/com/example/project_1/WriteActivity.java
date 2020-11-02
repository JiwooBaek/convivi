package com.example.project_1;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import model.BuyModel;
import model.ShareModel;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class WriteActivity extends AppCompatActivity {

    //인스턴스 선언
    private FirebaseAuth firebaseAuth;
    String uid;
    EditText et_title;
    EditText et_description;
    Spinner category;
    Button btn_exit;
    Button btn_save;
    DatabaseReference ref_share;
    DatabaseReference ref_buy;
    long shareMaxNum;
    long buyMaxNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //인스턴스 초기화
        firebaseAuth = FirebaseAuth.getInstance();

        et_title = findViewById(R.id.write_title);
        et_description = findViewById(R.id.description);
        btn_exit = findViewById(R.id.back_button);
        btn_save = findViewById(R.id.btn_save);
        category = (Spinner) findViewById(R.id.category);

        //'나눔' 게시글 자동번호 생성
        ref_share = FirebaseDatabase.getInstance().getReference().child("Share");
        ref_share.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    shareMaxNum = dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //'구매' 게시글 자동번호 생성
        ref_buy = FirebaseDatabase.getInstance().getReference().child("Buy");
        ref_buy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    buyMaxNum = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // 게시글 유형 선택시
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //'나눔' 선택시
                if(position == 1) {
                    btn_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uid = firebaseAuth.getCurrentUser().getUid();
                            ShareModel shareModel = new ShareModel();
                            FirebaseDatabase.getInstance().getReference().child("Share").child(uid).setValue(shareModel);

                            shareModel.idNum = Long.toString(shareMaxNum + 1);
                            shareModel.title = et_title.getText().toString();
                            shareModel.host = uid;
                            shareModel.description = et_description.getText().toString();
                            ref_share.child(String.valueOf(shareMaxNum + 1)).setValue(shareModel);

                            finish();
                        }
                    });

                //'구매' 선택시
                } else {
                    btn_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uid = firebaseAuth.getCurrentUser().getUid();
                            BuyModel buyModel = new BuyModel();
                            FirebaseDatabase.getInstance().getReference().child("Buy").child(uid).setValue(buyModel);

                            buyModel.idNum = Long.toString(buyMaxNum + 1);
                            buyModel.title = et_title.getText().toString();
                            buyModel.host = uid;
                            buyModel.description = et_description.getText().toString();
                            buyModel.currentNOP = 0;
                            buyModel.targetNOP = 0;
                            ref_buy.child(String.valueOf(buyMaxNum + 1)).setValue(buyModel);

                            finish();

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
