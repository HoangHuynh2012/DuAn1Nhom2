package com.example.duan1.admin.fragmentgiamgia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.admin.ThemVoucher;
import com.example.duan1.admin.adapteradmin.QuanLiNhanVienAdapter;
import com.example.duan1.admin.adapteradmin.VoucherAdtAdapter;
import com.example.duan1.admin.fragmentqlnv.QuanLyNVActivity;
import com.example.duan1.admin.modeladmin.NhanVien;
import com.example.duan1.admin.modeladmin.VoucherAd;
import com.example.duan1.employee.adapterempoyee.VocherAdapter;
import com.example.duan1.employee.fragmentvoucher.VoucherActivity;
import com.example.duan1.employee.model.Voucher;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VouCherAdActivity extends AppCompatActivity {
    ArrayList<Voucher> list;
    RecyclerView rcv;
    DatabaseReference databaseReference;
    VoucherAdtAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vou_cher_ad);
        Button btn_themvoucher = findViewById(R.id.btn_themvoucher);
        btn_themvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(VouCherAdActivity.this, ThemVoucher.class));
            }
        });
        rcv = findViewById(R.id.rcv_voucherad);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this,1));
        FirebaseRecyclerOptions<Voucher> options =
                new FirebaseRecyclerOptions.Builder<Voucher>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Voucher"), Voucher.class)
                        .build();
        adapter = new VoucherAdtAdapter(options, VouCherAdActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);


    }
}