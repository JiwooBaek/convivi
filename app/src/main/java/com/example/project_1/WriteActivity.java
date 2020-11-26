package com.example.project_1;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import model.BuyModel;
import model.ChatModel;
import com.example.project_1.Item.ImageItem;
import model.ShareModel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_1.Adapter.ImageAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class WriteActivity extends AppCompatActivity {

    //인스턴스 선언
    private FirebaseAuth firebaseAuth;
    private String uid;
    private EditText et_title;
    private EditText et_description;
    private Spinner category;
    private Button btn_exit;
    private Button btn_save;
    private NumberPicker targetNum;
    private DatabaseReference ref_share;
    private DatabaseReference ref_buy;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btn_image;
    private RecyclerView imageItemView;
    private ImageView imageView;
    private ImageAdapter imageAdapter;
    private Uri imageUri;
    private ArrayList<ImageItem> imageList;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    private long shareCount;
    private  long buyCount;

    //채팅방 인스턴스
    private FirebaseDatabase database;
    String roomNumber;




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
        targetNum = (NumberPicker) findViewById(R.id.targetNoP);
        btn_image = (Button) findViewById(R.id.addImageButton);
        imageView = (ImageView) findViewById(R.id.imagePreview2);

        //Image RecyclerView
        imageItemView = (RecyclerView) findViewById(R.id.imagePreview);
        imageList = new ArrayList<>();
        imageAdapter = new ImageAdapter(imageList);
        imageItemView.setAdapter(imageAdapter);


        // 목표인원수 제한 설정 및 설정값 가져오기
        targetNum.setMinValue(1);
        targetNum.setMaxValue(6);

        // Share에서 가장 마지막 글 번호 가져오기
        ref_share = FirebaseDatabase.getInstance().getReference().child("Share");
        ref_share.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot latestItem : dataSnapshot.getChildren()) {
                    ShareModel shareModel = latestItem.getValue(ShareModel.class);

                    shareCount = Long.parseLong(shareModel.id.substring(1));
                    shareCount++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref_buy = FirebaseDatabase.getInstance().getReference().child("Buy");
        ref_buy.orderByChild("id").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot latestItem : dataSnapshot.getChildren()) {
                    BuyModel buyModel = latestItem.getValue(BuyModel.class);

                    buyCount = Long.parseLong(buyModel.id.substring(1));
                    buyCount++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // 이미지 추가 버튼 동작
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        // 게시글 유형 선택 & 작성 완료 버튼 동작
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //'나눔' 선택시
                if(position == 1) {
                    targetNum.setEnabled(false);
                    btn_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uid = firebaseAuth.getCurrentUser().getUid();
                            ShareModel shareModel = new ShareModel();

                            // 게시글 제목과 상세글 null 방지
                            if(et_title.getText().toString().equals("") || et_description.getText().toString().equals("")) {
                                Toast.makeText(getApplicationContext(), "Input Error!", Toast.LENGTH_SHORT).show();
                            } else {
                                shareModel.idNum = Long.toString(shareCount);
                                shareModel.id = setShareId(shareCount);
                                shareModel.title = et_title.getText().toString();
                                shareModel.host = uid;
                                shareModel.description = et_description.getText().toString();
                                ref_share.child(shareModel.id).setValue(shareModel);

                                String path = "Share_image/"+shareModel.id;
                                imageUpload(path);
                            }

                            //나눔 채팅방 자동 생성

                            ChatModel chatModel = new ChatModel();
                            chatModel.host = uid;
                            chatModel.guest = "null";       //초기에 게스트 필드를 null로 초기화
                            chatModel.roomId = shareModel.id;

                            /*
                          database.getInstance().getReference("Share").child(shareModel.id).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    ShareModel shareModel = dataSnapshot.getValue(ShareModel.class);
                                    uid = shareModel.host;
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });*/

                            //chatModel.users.put(uid, true);

                            FirebaseDatabase.getInstance().getReference().child("Chatlist").child(chatModel.roomId).setValue(chatModel);

                            finish();
                        }
                    });

                //'구매' 선택시
                } else {
                    targetNum.setEnabled(true);
                    btn_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uid = firebaseAuth.getCurrentUser().getUid();
                            BuyModel buyModel = new BuyModel();

                            if(et_title.getText().toString().equals("") || et_description.getText().toString().equals("")) {
                                Toast.makeText(getApplicationContext(), "Input Error!", Toast.LENGTH_SHORT).show();
                            } else {
                                buyModel.id = setBuyId(buyCount);
                                buyModel.idNum = Long.toString(buyCount);
                                buyModel.title = et_title.getText().toString();
                                buyModel.host = uid;
                                buyModel.description = et_description.getText().toString();
                                buyModel.currentNOP = 0;
                                buyModel.targetNOP = targetNum.getValue();
                                ref_buy.child(buyModel.id).setValue(buyModel);
                            }

                            //구매 채팅방 자동으로 생성

                                String path = "Buy_image/" + buyModel.id;
                                imageUpload(path);

                            //구매 채팅방 자동 생성
                            ChatModel chatModel = new ChatModel();
                            chatModel.host = uid;
                            chatModel.guest = "null";
                            chatModel.roomId = buyModel.id;

                            FirebaseDatabase.getInstance().getReference().child("Chatlist").child(chatModel.roomId).setValue(chatModel);

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

    // 갤러리에서 이미지 가져오기
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
//            ImageItem imageItem = new ImageItem(imageUri);
//            imageList.add(imageItem);
//            imageAdapter.notifyDataSetChanged();
        }
    }

    private void imageUpload(String path) {
        if(imageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageRef.child(path + "/" +  UUID.randomUUID().toString());
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(WriteActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(WriteActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "imageUri value is NULL", Toast.LENGTH_SHORT).show();
        }

    }

    //나눔게시글 아이디 설정
    private String setShareId(long num) {
        String id = "S" + num;
        return id;
    }

    //공구게시글 아이디 설정
    private String setBuyId(long num) {
        String id = "B" + num;
        return id;
    }

    private void uploadImage() {
        if(imageUri != null) {

        }
    }
}
