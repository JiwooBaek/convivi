package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import model.BuyModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class WriteActivity extends AppCompatActivity {

    //인스턴스 선언
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uid;
    EditText et_title;
    EditText et_description;
    Button btn_exit;
    Button btn_save;
    DatabaseReference autoNum;
    long maxNum = 0;


    /*
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReferenceFromUrl("gs://project-75936.appspot.com");
    StorageReference pathReference = storageReference.child("photos/default_1.png");
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //인스턴스 초기화
        firebaseAuth = FirebaseAuth.getInstance();

        et_title = findViewById(R.id.write_title);
        et_description = findViewById(R.id.et_description);
        btn_exit = findViewById(R.id.back_button);
        btn_save = findViewById(R.id.btn_save);


        Integer defaultImage = R.drawable.default_1;
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            uid = user.getUid();
//        }

        autoNum = FirebaseDatabase.getInstance().getReference().child("Buy");
        autoNum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    maxNum = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseUser.reload();

        btn_save.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uid = firebaseAuth.getCurrentUser().getUid();
                BuyModel buyModel = new BuyModel();
                FirebaseDatabase.getInstance().getReference().child("Buy").child(uid).setValue(buyModel);


                 finish();


                buyModel.idNum = Long.toString(maxNum + 1);
                buyModel.title = et_title.getText().toString();
                buyModel.host = uid;
                buyModel.description = et_description.getText().toString();
                autoNum.child(String.valueOf(maxNum + 1)).setValue(buyModel);

                finish();
            }
        });
    }
}
//    public void clickSave(View view){
//
//        scriptModel.title = et_title.getText().toString();
//        scriptModel.description = et_description.getText().toString();
//        scriptModel.imgld = R.drawable.default_1;
//
//
//        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).setValue(scriptModel);
//
//    }
//




/* package com.example.project_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

import model.ScriptModel;

public class WriteActivity extends AppCompatActivity {

    //인스턴스 선언
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String uid;
    EditText et_title;
    EditText et_description;
    Button btn_exit;
    Button btn_save;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //인스턴스 초기화
        firebaseAuth = FirebaseAuth.getInstance();

        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        btn_exit = findViewById(R.id.btn_exit);
        btn_save = findViewById(R.id.btn_save);

//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            uid = user.getUid();
//        }

        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseUser.reload();

        btn_save.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                ScriptModel scriptModel = new ScriptModel();
                uid = firebaseAuth.getCurrentUser().getUid();

                scriptModel.title = et_title.getText().toString();
                scriptModel.description = et_description.getText().toString();
                scriptModel.imgld = R.drawable.default_1;


                FirebaseDatabase.getInstance().getReference().child("Buy").child(uid).setValue(scriptModel);

            }
        });

    }

//    public void clickSave(View view){
//
//        scriptModel.title = et_title.getText().toString();
//        scriptModel.description = et_description.getText().toString();
//        scriptModel.imgld = R.drawable.default_1;
//
//
//        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).setValue(scriptModel);
//
//    }
//


}


//    public void clickSave(View view){
//
//        scriptModel.title = et_title.getText().toString();
//        scriptModel.description = et_description.getText().toString();
//        scriptModel.imgld = R.drawable.default_1;
//
//
//        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).setValue(scriptModel);
//
//    }
//



*/