package com.example.duan1.admin.fragmentqlnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.admin.adapteradmin.QuanLiNhanVienAdapter;
import com.example.duan1.admin.modeladmin.NhanVien;
import com.example.duan1.customer.adapter.SanPhamAdapter;
import com.example.duan1.customer.model.Cart;
import com.example.duan1.customer.model.SanPham;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyNVActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView iv;
    ArrayList<NhanVien> list;
    RecyclerView rcv;
    QuanLiNhanVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_n_v);
        toolbar = findViewById(R.id.toolbarQuanLyNhanVien);
        iv = findViewById(R.id.ivThemNhanVien);
        rcv = findViewById(R.id.rcv_nhanvien);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(QuanLyNVActivity.this, 1));
        FirebaseRecyclerOptions<NhanVien> options =
                new FirebaseRecyclerOptions.Builder<NhanVien>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("NguoiDung").orderByChild("role").equalTo("2"), NhanVien.class)
                        .build();
        adapter = new QuanLiNhanVienAdapter(options, QuanLyNVActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ThemNhanVien.class));
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
