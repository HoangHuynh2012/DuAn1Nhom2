package com.example.duan1.customer.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.duan1.R;
import com.example.duan1.customer.model.NguoiDungMode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoiMatKhauActivity extends AppCompatActivity {
    Toolbar tbDMK;
    EditText username,mkcu,mkmoi,mkmoi2;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        tbDMK = findViewById(R.id.toolbarDoiMatKhau);
        username =  (EditText) findViewById(R.id.editnhaptentaikhoan);
        mkcu = findViewById(R.id.edtNhapMatKhauCu);
        mkmoi = findViewById(R.id.edtNhapMatKhauMoi);
        mkmoi2 = findViewById(R.id.edtNhapLaiMatKhauMoi);
        Button btn_huy = findViewById(R.id.btnhuy);
        Button btn_capnhat = findViewById(R.id.btnCapNhap);


        username.setText(DangNhapActivity.usernamedangnhap);




        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenk = username.getText().toString();
                String matkhau = mkcu.getText().toString();
                String matkhaukmoi = mkmoi.getText().toString();
                String matkhaukmoi2 = mkmoi2.getText().toString();
                if (matkhaukmoi.equals(matkhaukmoi2)){
                    Toast.makeText(DoiMatKhauActivity.this, "abc" + matkhau, Toast.LENGTH_SHORT).show();
                   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
                  databaseReference.orderByChild("username").equalTo(tenk).addChildEventListener(new ChildEventListener() {
                      @Override
                      public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                          NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                          Log.d("tk",mode.getUsername());
                          Log.d("mk",mode.getPassword());
                          key = snapshot.getKey();
                          Log.d("key123",key);
                          if (matkhau.equals(mode.getPassword())){
                              Toast.makeText(DoiMatKhauActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                              databaseReference.child(key).child("password").setValue(matkhaukmoi);
                              finish();
                          }else if (matkhau.equals("")){
                              Toast.makeText(DoiMatKhauActivity.this,"Mời bạn nhập mật khẩu cũ",Toast.LENGTH_SHORT).show();
                          }else if (matkhaukmoi.equals("")){

                              Toast.makeText(DoiMatKhauActivity.this,"Mời bạn nhập mật khẩu mới",Toast.LENGTH_SHORT).show();
                          }else if (matkhaukmoi2.equals("")){
                              Toast.makeText(DoiMatKhauActivity.this,"Mời bạn xác nhân mật khẩu mới",Toast.LENGTH_SHORT).show();
                          }
                          else {
                              Toast.makeText(DoiMatKhauActivity.this, "Bạn nhập sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                          }

                      }

                      @Override
                      public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                      }

                      @Override
                      public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                      }

                      @Override
                      public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                      }

                      @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
               } else {
                   Toast.makeText(DoiMatKhauActivity.this, "2 Mật Khẩu Chưa Trùng Nhau", Toast.LENGTH_SHORT).show();
               }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  finish();
            }
        });
        setSupportActionBar(tbDMK);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}