package com.example.duan1.customer.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1.CustomProgressDialog;
import com.example.duan1.R;
import com.example.duan1.admin.TrangChuAdminActivity;
import com.example.duan1.customer.TrangChuActivity;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.TrangChuEmloyeeActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap, btnDangKy, btnDangNhapGmail;
    TextView tvQuenMatKhau;
    EditText username, password;
    DatabaseReference databaseReference;
    CheckBox checkBox;
    public static String role1;
    public static String key1;
    public static String tenusername;
    public static final String PREFS_NAME = "MyFile";
    public static String usernamedangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        btnDangNhapGmail = findViewById(R.id.btnGmail);

        CustomProgressDialog dialog = new CustomProgressDialog(DangNhapActivity.this);

        username = findViewById(R.id.userdangnhap);
        password = findViewById(R.id.passworddangnhap);
        checkBox = findViewById(R.id.checkBox);
        loadData();
        final Loading loading = new Loading(DangNhapActivity.this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String taikhoan1 = username.getText().toString();
                String matkhau1 = password.getText().toString();
                if (!taikhoan1.equals("") || !matkhau1.equals("")) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung");
                    databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                            boolean check = checkBox.isChecked();
//                            loading.startLoading();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (taikhoan1.equals(mode.getUsername()) && matkhau1.equals(mode.getPassword())) {
                                        luuTT(taikhoan1, matkhau1, check);
                                        String role = mode.getRole();
                                        role1 = role;
                                        usernamedangnhap = taikhoan1;
                                        String key = snapshot.getKey();
                                        key1 = key;
                                        String ten = mode.getUsername();
                                        tenusername = ten;
                                        if (role.equals("3") || role.equals("4")) {
                                            Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                                            intent.putExtra("role", role);
                                            intent.putExtra("key", key);

                                            Toast.makeText(DangNhapActivity.this, "Layout của Khách Hàng", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                            dialog.dismiss();
//                                            loading.dismissDialog();
                                        } else if (role.equals("2")) {
                                            Intent intent = new Intent(DangNhapActivity.this, TrangChuEmloyeeActivity.class);
                                            intent.putExtra("role", role);
                                            intent.putExtra("key", key);
                                            Toast.makeText(DangNhapActivity.this, "Layout của Nhân Viên", Toast.LENGTH_SHORT).show();

                                            startActivity(intent);
                                            dialog.dismiss();
//                                            loading.dismissDialog();
                                        } else if (role.equals("1")) {
                                            Intent intent = new Intent(DangNhapActivity.this, TrangChuAdminActivity.class);
                                            intent.putExtra("role", role);
                                            intent.putExtra("key", key);
                                            Toast.makeText(DangNhapActivity.this, "Layout của Admin", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();

                                            startActivity(intent);
//                                            loading.dismissDialog();
                                        } else if (taikhoan1.equals("")) {
                                            dialog.dismiss();
                                            Toast.makeText(DangNhapActivity.this, "Mời Bạn Nhập UserName", Toast.LENGTH_SHORT).show();
                                        } else if (matkhau1.equals("")) {
                                            dialog.dismiss();
                                            Toast.makeText(DangNhapActivity.this, "Mời Bạn Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
//                                        Toast.makeText(DangNhapActivity.this, "Sai Tài Khoản Mật Khẩu", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                }

                            }, 2000);
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
                    Toast.makeText(DangNhapActivity.this, "Không bỏ trống trường nào", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKiActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LayMatKhauActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        btnDangNhapGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangNhapBangEmail.class));
                overridePendingTransition(0, 0);
            }
        });
    }

    public void luuTT(String tk, String mk, boolean check) {
        SharedPreferences sh = getSharedPreferences("data.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        boolean chk = checkBox.isChecked();
        if (!chk) {
            editor.clear();
        } else {
            editor.putString("username", tk);
            editor.putString("password", mk);
            editor.putBoolean("savestatus", check);
        }
        editor.commit();

    }

    private void loadData() {
        SharedPreferences pref = getSharedPreferences("data.txt", MODE_PRIVATE);
        boolean check = pref.getBoolean("savestatus", false);
        if (check) {
            username.setText(pref.getString("username", ""));
            password.setText(pref.getString("password", ""));
            checkBox.setChecked(check);
        }
    }

}