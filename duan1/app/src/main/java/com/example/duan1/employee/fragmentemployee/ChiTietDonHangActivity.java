package com.example.duan1.employee.fragmentemployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.employee.adapterempoyee.ChiTietAdapter;
import com.example.duan1.employee.adapterempoyee.HoaDonChiTietAdapter;
import com.example.duan1.employee.model.ChiTietDonHang;
import com.example.duan1.employee.model.HoaDonChiTiet;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangActivity extends AppCompatActivity {
    RecyclerView rcv;
    ArrayList<HoaDonChiTiet> list;
    HoaDonChiTietAdapter adapter;
    TextView ngay,mahd,ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        rcv = findViewById(R.id.rcv_chitietls);
        ngay = findViewById(R.id.datechitiet);
        mahd = findViewById(R.id.maHDct);
        ten = findViewById(R.id.tenKHct);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this, 1));
        Intent intent = getIntent();
        String id = intent.getStringExtra("idhd");
        Toast.makeText(this, "id hd" + id, Toast.LENGTH_SHORT).show();
        FirebaseRecyclerOptions<HoaDonChiTiet> options =
                new FirebaseRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oder").child(id).child("SanPham"), HoaDonChiTiet.class)
                        .build();
        adapter = new HoaDonChiTietAdapter(options, ChiTietDonHangActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);

        DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReference().child("Oder");
        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HoaDonMoi mode = snapshot.getValue(HoaDonMoi.class);
                ngay.setText(mode.getNgaymua());
                mahd.setText(mode.getOderid());
                ten.setText(mode.getNguoioder());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}