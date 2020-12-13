package com.example.duan1.customer.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.duan1.CustomProgressDialog;
import com.example.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangKiActivity extends AppCompatActivity {
    TextView tvDaCoTaiKhoan;
    Button btnDangKyTaiKhoan;
    DatabaseReference databaseReference;
    EditText edtNhapTenNguoiDung,edtNhapUserName,edtNhapPassword,edtNhapLaiPassword,edtNhapSoDienThoai,edtNhapGmail;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        CustomProgressDialog dialog = new CustomProgressDialog(DangKiActivity.this);

        tvDaCoTaiKhoan = findViewById(R.id.tvDaCoTaiKhoan);
        btnDangKyTaiKhoan = findViewById(R.id.btnDangKyTaiKhoan);
        edtNhapTenNguoiDung = findViewById(R.id.edtNhapTenNguoiDung);
        edtNhapUserName = findViewById(R.id.edtNhapUserName);
        edtNhapPassword = findViewById(R.id.edtNhapPassword);
        edtNhapLaiPassword = findViewById(R.id.edtNhapLaiPassword);
        edtNhapSoDienThoai = findViewById(R.id.edtNhapSoDienThoai);
        edtNhapGmail = findViewById(R.id.edtNhapGmail);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
//       Check validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//       Check tên người dùng
        awesomeValidation.addValidation(this,R.id.edtNhapTenNguoiDung, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
//       Check username
        awesomeValidation.addValidation(this,R.id.edtNhapUserName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
//       Check số điện thoại
        awesomeValidation.addValidation(this,R.id.edtNhapSoDienThoai,"[0-9]{1}[0-9]{9}$",R.string.invalid_phone);
//       Check gmail
        awesomeValidation.addValidation(this,R.id.edtNhapGmail,Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String tennguoidung = edtNhapTenNguoiDung.getText().toString();
                String username = edtNhapUserName.getText().toString();
                String password = edtNhapPassword.getText().toString();
                String passwordss2 = edtNhapLaiPassword.getText().toString();
                String sdt = edtNhapSoDienThoai.getText().toString();
                String gmail = edtNhapGmail.getText().toString();
                if (password.length() > 6) {
                    dialog.dismiss();
                    Toast.makeText(DangKiActivity.this, "Mật Khẩu Phải Từ 6 Kí Tự Trở Lên", Toast.LENGTH_SHORT).show();
                } else {
                    int role = 3;
                    if (password.equals(passwordss2)) {
                        if (!tennguoidung.equals("") && !username.equals("") && !password.equals("") && !passwordss2.equals("") && !sdt.equals("") && !gmail.equals("")) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("userId", databaseReference.push().getKey());
                            hashMap.put("tennguoidung", tennguoidung);
                            hashMap.put("username", username);
                            hashMap.put("password", password);
                            hashMap.put("sdt", sdt);
                            hashMap.put("gmail", gmail);
                            hashMap.put("diachi", "");
                            hashMap.put("imageUrl", "default");
                            hashMap.put("role", String.valueOf(role));
                            databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                        Toast.makeText(DangKiActivity.this, "Đăng Kí Thành Công!!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));
                                    } else {
                                        Toast.makeText(DangKiActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                }
                            });
                        } else {
                            awesomeValidation.validate();
                        }
                    } else {
                        Toast.makeText(DangKiActivity.this, "Hai Mật Khẩu Phải Trùng Nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvDaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangKiActivity.this, DangNhapActivity.class);
                startActivity(i);
            }
        });
    }

}