package com.example.duan1.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.CustomProgressDialog;
import com.example.duan1.R;
import com.example.duan1.customer.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ThemSanPhamPhoBien extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 1;

    private Button chooseImage, btnUploadImage;
    private TextView viewGallery;
    private ImageView imgPreview;
    private EditText addtensp, addgiasp, addsoluong, addgioithieu;
    private ProgressBar uploadProgress;
    private Uri imgUrl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    CustomProgressDialog dialog = new CustomProgressDialog(ThemSanPhamPhoBien.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_pho_bien);
        uploadProgress = findViewById(R.id.uploadProgress);
        chooseImage = findViewById(R.id.chooseImagepb);
        btnUploadImage = findViewById(R.id.btnUploadImagepb);
        imgPreview = findViewById(R.id.imgPreviewsppb);
        addtensp = findViewById(R.id.addtensppb);
        addgiasp = findViewById(R.id.addgiasppb);
        addsoluong = findViewById(R.id.addslsppb);
        addgioithieu = findViewById(R.id.addgioithieupb);

        mStorageRef = FirebaseStorage.getInstance().getReference("SanPhamPhoBien");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SanPhamPhoBien");

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(ThemSanPhamPhoBien.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadImage();
                }
            }
        });


        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChoose();
            }
        });
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
            Picasso.get().load(imgUrl).into(imgPreview);
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        if (imgUrl != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUrl));

            mUploadTask = fileReference.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadProgress.setProgress(0);
                                }
                            }, 500);
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dialog.show();
                                    String uploadID = mDatabaseRef.push().getKey();
                                    String ten = addtensp.getText().toString().trim();
                                    String gia = addgiasp.getText().toString().trim();
                                    String soluong = addsoluong.getText().toString().trim();
                                    String gioithieu = addgioithieu.getText().toString().trim();
                                    if (ten.isEmpty() || gia.isEmpty()|| soluong.isEmpty()|| gioithieu.isEmpty()){
                                        dialog.dismiss();
                                        Toast.makeText(ThemSanPhamPhoBien.this, "Không Bỏ Trống Trường Nào Cả", Toast.LENGTH_SHORT).show();
                                    } else {
                                        SanPham upload = new SanPham(uploadID, ten, gia, soluong, gioithieu, uri.toString());
                                        mDatabaseRef.child(uploadID).setValue(upload);
                                        Toast.makeText(ThemSanPhamPhoBien.this, "Upload successfully", Toast.LENGTH_LONG).show();
                                        addtensp.setText("");
                                        addsoluong.setText("");
                                        addgiasp.setText("");
                                        addgioithieu.setText("");
                                    }
                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ThemSanPhamPhoBien.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            uploadProgress.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(ThemSanPhamPhoBien.this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
