package com.example.duan1.customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.duan1.customer.Login.DoiMatKhauActivity;
import com.example.duan1.R;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    Toolbar tbTttk;
    TextView tvDMK,tVtenht, tvTenThongTinCaNhan, tvSdt;
    ImageView imgHinh;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    public static String role, key;
    boolean checkrole;
    private static final int CHOOSE_IMAGE = 1;
    private StorageTask mUploadTask;

    private Uri imgUrl;
    private StorageReference mStorageRef;
    String keyEmail;
    static String tentk,sdttk,emailtk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);
        tbTttk = findViewById(R.id.toolbarThongTinTaiKhoan);
        tvDMK = findViewById(R.id.tvDoiMatKhauTTKH);
        imgHinh = findViewById(R.id.image_Hinh);
        tVtenht = findViewById(R.id.tvTen);
        tvTenThongTinCaNhan = findViewById(R.id.tvTenThongTinCaNhan);
        tvSdt = findViewById(R.id.tvsdt);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Intent intent = getIntent();
        try {
            role = intent.getStringExtra("role1");
            key = intent.getStringExtra("key1");
            if (role.equals("3")) {
                checkrole = true;
                storageReference = FirebaseStorage.getInstance().getReference("NguoiDung");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                        assert mode != null;
                        tVtenht.setText(mode.getTennguoidung());
                        tvTenThongTinCaNhan.setText(mode.getGmail());
                        tvSdt.setText(mode.getSdt());
                        tentk = mode.getTennguoidung();
                        sdttk = mode.getSdt();
                        emailtk = mode.getGmail();
                        if (mode.getImageUrl().equals("default")) {
                            imgHinh.setImageResource(R.drawable.ic_userrr);
                        } else {
                            Glide.with(ThongTinTaiKhoanActivity.this).load(mode.getImageUrl()).into(imgHinh);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ThongTinTaiKhoanActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (role.equals("4")) {
                checkrole = false;
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                keyEmail = firebaseUser.getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                        assert mode != null;
                        tVtenht.setText(mode.getEmail());
                        tvTenThongTinCaNhan.setText(mode.getEmail());
                        tvSdt.setText("");
                        tentk = mode.getEmail();
                        sdttk = "";
                        emailtk  = mode.getEmail();
                        if (mode.getImageUrl().equals("default")) {
                            imgHinh.setImageResource(R.drawable.ic_userrr);
                        } else {
                            Glide.with(ThongTinTaiKhoanActivity.this).load(mode.getImageUrl()).into(imgHinh);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ThongTinTaiKhoanActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } catch (NullPointerException exception) {
            tVtenht.setText("User Name");
            tvTenThongTinCaNhan.setText("");
            tvSdt.setText("");

        }
        Button btn_suatt = findViewById(R.id.suatt);
        btn_suatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent1 = new Intent(ThongTinTaiKhoanActivity.this,SuaTTActivity.class);
               intent.putExtra("role3",role);
               intent.putExtra("key3",key);
               startActivity(intent1);

            }
        });
        setSupportActionBar(tbTttk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DoiMatKhauActivity.class));
                overridePendingTransition(0,0);
            }
        });

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        ThongTinTaiKhoanActivity.this,R.style.BottomSheet
                );
                View bottomSheet = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bottm_sheet_layout,
                                (LinearLayout)findViewById(R.id.bottom_sheetCont));

                bottomSheet.findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {showFileChoose();
                    }
                });
                bottomSheet.findViewById(R.id.layout4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mUploadTask != null && mUploadTask.isInProgress()) {
                            Toast.makeText(ThongTinTaiKhoanActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                        } else {
                            uploadImage();
                        }

                    }
                });
                bottomSheetDialog.setContentView(bottomSheet);
                bottomSheetDialog.show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void showFileChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();
            Picasso.get().load(imgUrl).into(imgHinh);
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        if (imgUrl != null) {
            if (checkrole){
                mStorageRef = FirebaseStorage.getInstance().getReference("NguoiDung");
                final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUrl));
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                mUploadTask = fileReference.putFile(imgUrl)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        uploadProgress.setProgress(0);
                                    }
                                }, 500);
                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        databaseReference.child("imageUrl").setValue(uri.toString());
                                        Toast.makeText(ThongTinTaiKhoanActivity.this, "Upload successfully", Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ThongTinTaiKhoanActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                                uploadProgress.setProgress((int) progress);
                            }
                        });
            } else {
                mStorageRef = FirebaseStorage.getInstance().getReference("NguoiDungMail");
                final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUrl));
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(keyEmail);
                mUploadTask = fileReference.putFile(imgUrl)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        uploadProgress.setProgress(0);
                                    }
                                }, 500);
                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        databaseReference.child("imageUrl").setValue(uri.toString());
                                        Toast.makeText(ThongTinTaiKhoanActivity.this, "Upload successfully", Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ThongTinTaiKhoanActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                                uploadProgress.setProgress((int) progress);
                            }
                        });
            }

        } else {
            Toast.makeText(ThongTinTaiKhoanActivity.this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

}