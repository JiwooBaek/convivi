package com.example.project_1;

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


<<<<<<< HEAD
                FirebaseDatabase.getInstance().getReference().child("").child(uid).setValue(scriptModel);
=======
                 FirebaseDatabase.getInstance().getReference().child("Buy").child(uid).setValue(scriptModel);
                 finish();
<<<<<<< HEAD
             }
         });
=======
=======
>>>>>>> c6753f504159257d8aea6e59c9ec5be2833fd4fb
>>>>>>> 892f6b2a62fd221bef38b9264cfdd7c166797565

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
>>>>>>> 3447c123918f468a44cc5c62d8324aa98cf7b8d3

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