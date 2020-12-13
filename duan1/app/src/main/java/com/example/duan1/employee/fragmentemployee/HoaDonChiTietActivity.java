package com.example.duan1.employee.fragmentemployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.adapterempoyee.HoaDonChiTietAdapter;
import com.example.duan1.employee.adapterempoyee.HoaDonMoiAdapter;
import com.example.duan1.employee.model.HoaDonChiTiet;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<HoaDonChiTiet> list;
    RecyclerView rcv;
    String role,key;
    String abc;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    HoaDonChiTietAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        rcv = findViewById(R.id.rvhdchitiet);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this, 1));
        Intent intent = getIntent();
        String id = intent.getStringExtra("idhd");
        Toast.makeText(this, "id hd" + id, Toast.LENGTH_SHORT).show();
        FirebaseRecyclerOptions<HoaDonChiTiet> options =
                new FirebaseRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oder").child(id).child("SanPham"), HoaDonChiTiet.class)
                        .build();
        adapter = new HoaDonChiTietAdapter(options, HoaDonChiTietActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);

    }
}