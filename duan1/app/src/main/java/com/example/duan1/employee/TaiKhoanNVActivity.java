package com.example.duan1.employee;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.customer.Login.DoiMatKhauActivity;
import com.example.duan1.customer.ThongTinTaiKhoanActivity;
import com.example.duan1.customer.model.NguoiDungMode;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class TaiKhoanNVActivity extends AppCompatActivity {
    Toolbar tbTttk;
    TextView tvDMK,tvTen,tvGmailNhanVien,tvsdtnhanvien;
    ImageView imgHinhnv;
    StorageReference storageReference;
    String  key;

    private static final int CHOOSE_IMAGE = 1;
    private StorageTask mUploadTask;

    private Uri imgUrl;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_n_v);
        tbTttk = findViewById(R.id.toolbarThongTinTaiKhoannv);
        tvDMK = findViewById(R.id.tvDoiMatKhauTTNV);
        imgHinhnv = findViewById(R.id.image_Hinhnv);
        tvTen = findViewById(R.id.tvTenNhanVien);
        tvGmailNhanVien = findViewById(R.id.tvGmailNhanVien);
        tvsdtnhanvien = findViewById(R.id.tvsdtnhanvien);
        Intent intent = getIntent();
        key = intent.getStringExtra("key1");
        storageReference = FirebaseStorage.getInstance().getReference("NguoiDung");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                assert mode != null;
                tvTen.setText(mode.getTennguoidung());
                tvGmailNhanVien.setText(mode.getGmail());
                tvsdtnhanvien.setText(mode.getSdt());

                if (mode.getImageUrl().equals("default")) {
                    imgHinhnv.setImageResource(R.drawable.ic_userrr);
                } else {
                    Glide.with(TaiKhoanNVActivity.this).load(mode.getImageUrl()).into(imgHinhnv);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TaiKhoanNVActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        imgHinhnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        TaiKhoanNVActivity.this,R.style.BottomSheet
                );
                View bottomSheet = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bottm_sheet_layout,
                                (LinearLayout)findViewById(R.id.bottom_sheetCont));

                bottomSheet.findViewById(R.id.tvChonAnh).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showFileChoose();
                    }
                });
                bottomSheet.findViewById(R.id.layout4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUploadTask != null && mUploadTask.isInProgress()) {
                            Toast.makeText(TaiKhoanNVActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
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
            Picasso.get().load(imgUrl).into(imgHinhnv);
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        if (imgUrl != null) {
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
                                        Toast.makeText(TaiKhoanNVActivity.this, "Upload successfully", Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TaiKhoanNVActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(TaiKhoanNVActivity.this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}