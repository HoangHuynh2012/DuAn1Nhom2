package com.example.duan1.employee.fragmentemployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan1.R;
import com.example.duan1.admin.adapteradmin.QuanLiHoaDonAdapter;
import com.example.duan1.employee.adapterempoyee.HoaDonMoiAdapter;
import com.example.duan1.employee.adapterempoyee.LichSuAdapter;
import com.example.duan1.employee.model.HoaDonMoi;
import com.example.duan1.employee.model.LichSu;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LichSuActivity extends AppCompatActivity {
    ArrayList<HoaDonMoi> list;
    RecyclerView rcv;
    QuanLiHoaDonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        rcv = findViewById(R.id.rcv_ls);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this,1));
        FirebaseRecyclerOptions<HoaDonMoi> options =
                new FirebaseRecyclerOptions.Builder<HoaDonMoi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oder"), HoaDonMoi.class)
                        .build();
        adapter = new QuanLiHoaDonAdapter(options, LichSuActivity.this);
        adapter.startListening();
        rcv.setAdapter(adapter);

    }
}