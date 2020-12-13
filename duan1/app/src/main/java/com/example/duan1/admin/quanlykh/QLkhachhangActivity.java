package com.example.duan1.admin.quanlykh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.duan1.R;
import com.example.duan1.admin.adapteradmin.QuanLiKhachHangAdapter;
import com.example.duan1.admin.adapteradmin.QuanLiKhachHangMailAdapter;
import com.example.duan1.admin.adapteradmin.QuanLiNhanVienAdapter;
import com.example.duan1.admin.fragmentqlnv.QuanLyNVActivity;
import com.example.duan1.admin.modeladmin.KhachHang;
import com.example.duan1.admin.modeladmin.KhachHangMail;
import com.example.duan1.admin.modeladmin.NhanVien;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QLkhachhangActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<KhachHang> list;
    ArrayList<KhachHangMail> listmail;
    RecyclerView rcv,rcv_mail;
    QuanLiKhachHangAdapter adapter;
    QuanLiKhachHangMailAdapter adapter_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_lkhachhang);

        toolbar = findViewById(R.id.toolbarQuanLyNhanVien);
        rcv = findViewById(R.id.rcv_quanlykh);

        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(QLkhachhangActivity.this, 1));
        FirebaseRecyclerOptions<KhachHang> options =
                new FirebaseRecyclerOptions.Builder<KhachHang>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("NguoiDung").orderByChild("role").equalTo("3"), KhachHang.class)
                        .build();
        adapter = new QuanLiKhachHangAdapter(options, QLkhachhangActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);
        //
        listmail = new ArrayList<>();
        rcv_mail = findViewById(R.id.rcv_quanlykhmail);
        rcv_mail.setLayoutManager(new GridLayoutManager(QLkhachhangActivity.this, 1));
        FirebaseRecyclerOptions<KhachHangMail> options_mail =
                new FirebaseRecyclerOptions.Builder<KhachHangMail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("NguoiDungMail"), KhachHangMail.class)
                        .build();
        adapter_mail = new QuanLiKhachHangMailAdapter(options_mail, QLkhachhangActivity.this);
        adapter_mail.startListening();
        rcv_mail.setAdapter(adapter_mail);
        //
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}