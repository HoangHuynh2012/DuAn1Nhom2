package com.example.duan1.admin.fragmentqlnv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duan1.R;
import com.example.duan1.customer.Login.DangKiActivity;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ThemNhanVien extends AppCompatActivity {
    Toolbar toolbar;
    EditText ten,username,password,gmail,sdt;
    Button btn_them;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        toolbar = findViewById(R.id.toolbarThemNhanVien);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ten= findViewById(R.id.add_tennhanvien);
        username= findViewById(R.id.add_username);
        password= findViewById(R.id.add_password);
        gmail= findViewById(R.id.add_gmail);
        sdt= findViewById(R.id.add_sdt);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
        btn_them = findViewById(R.id.add_nhanvien);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tennv = ten.getText().toString().trim();
                String user = username.getText().toString().trim();
                String mk = password.getText().toString().trim();
                String mail = gmail.getText().toString().trim();
                String sđt = sdt.getText().toString().trim();
                if (tennv.isEmpty() || user.isEmpty() || mk.isEmpty() || mail.isEmpty() || sđt.isEmpty()) {
                    Toast.makeText(ThemNhanVien.this, "Không Bỏ Trống 1 Trường Nào Cả", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", databaseReference.push().getKey());
                    hashMap.put("tennguoidung", tennv);
                    hashMap.put("username", user);
                    hashMap.put("password", mk);
                    hashMap.put("sdt", sđt);
                    hashMap.put("gmail", mail);
                    hashMap.put("imageUrl", "default");
                    hashMap.put("role", "2");
                    databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ThemNhanVien.this, "Thêm Nhân Viên Thành Công!!!", Toast.LENGTH_SHORT).show();
                                ten.setText("");
                                username.setText("");
                                password.setText("");
                                gmail.setText("");
                                sdt.setText("");
                            } else {
                                Toast.makeText(ThemNhanVien.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });




    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}