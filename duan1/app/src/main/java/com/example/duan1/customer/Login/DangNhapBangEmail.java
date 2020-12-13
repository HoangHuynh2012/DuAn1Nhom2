package com.example.duan1.customer.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duan1.CustomProgressDialog;
import com.example.duan1.R;
import com.example.duan1.customer.TrangChuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangNhapBangEmail extends AppCompatActivity {
    Toolbar toolbar;
    Button btnTiepTuc;
    TextView tvQuenMatKhau;
    EditText email_dangki,matkhau_dangki;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    public  static String role2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_bang_email);
        btnTiepTuc = findViewById(R.id.btnDangNhapEmail);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhauEmail);
        toolbar = findViewById(R.id.toolbarDangNhapBangEmail);
        firebaseAuth =FirebaseAuth.getInstance();
        email_dangki = findViewById(R.id.ed_maildangnhap);
        matkhau_dangki = findViewById(R.id.ed_passworddangnhap);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CustomProgressDialog dialog = new CustomProgressDialog(DangNhapBangEmail.this);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            String email = email_dangki.getText().toString();
            String password = matkhau_dangki.getText().toString();
                new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                  if (!email.isEmpty() && !password.isEmpty()){
                      Login(email,password);
                      dialog.dismiss();
                  } else {
                      dialog.dismiss();
                      Toast.makeText(DangNhapBangEmail.this, "Không Bỏ Trống Trường Nào Cả", Toast.LENGTH_SHORT).show();
                  }

                }
            },5000);




        }
    });
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LayMatKhauActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
    private void Login(String taikhoan, String matkhau) {
        firebaseAuth.signInWithEmailAndPassword(taikhoan,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(DangNhapBangEmail.this,TrangChuActivity.class);
                    intent.putExtra("role","4");
                    Toast.makeText(DangNhapBangEmail.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else if(task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                    registration(taikhoan,matkhau);
                } else {
                    Toast.makeText(DangNhapBangEmail.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void registration(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user =firebaseAuth.getCurrentUser();
                    String userId = user.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(userId);
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("userId",userId);
                    hashMap.put("email",email);
                    hashMap.put("password",password);
                    hashMap.put("imageUrl","default");
                    hashMap.put("role","4");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(DangNhapBangEmail.this,TrangChuActivity.class);
                                intent.putExtra("role","4");
                                role2 = "4";
                                startActivity(intent);
                            } else{
                                Toast.makeText(DangNhapBangEmail.this,task.getException().getMessage(),Toast.LENGTH_LONG).show() ;
                            }
                        }
                    });

                }else {
                    Toast.makeText(DangNhapBangEmail.this,task.getException().getMessage(),Toast.LENGTH_LONG).show() ;

                }
            }
        });
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}