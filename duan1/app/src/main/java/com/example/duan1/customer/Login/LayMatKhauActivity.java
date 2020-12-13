package com.example.duan1.customer.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LayMatKhauActivity extends AppCompatActivity {
    TextView tvQuayLaiDangNhap;
    FirebaseAuth firebaseAuth;
    EditText mail;
    Button btnLayLaiMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_mat_khau);
        tvQuayLaiDangNhap = findViewById(R.id.tvQuayLaiDangNhap);
        mail = findViewById(R.id.edtNhapGmailLayLaiMatKhau);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLayLaiMatKhau = findViewById(R.id.btnLayLaiMatKhau);
        btnLayLaiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.fetchSignInMethodsForEmail(mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.getResult().getSignInMethods().isEmpty()){
                            Toast.makeText(LayMatKhauActivity.this, "Hiện Tại Gmail Đó Chưa Đăng Kí", Toast.LENGTH_SHORT).show();
                        }else {
                            firebaseAuth.sendPasswordResetEmail(mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(LayMatKhauActivity.this, "Check Mail Đi", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LayMatKhauActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

        tvQuayLaiDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LayMatKhauActivity.this,DangNhapActivity.class);
                startActivity(i);
            }
        });
    }

}