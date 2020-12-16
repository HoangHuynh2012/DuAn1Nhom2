package com.example.duan1.admin.thongke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan1.R;
import com.example.duan1.admin.adapteradmin.DoanhThuAdapter;
import com.example.duan1.admin.modeladmin.DoanhThu;
import com.example.duan1.customer.adapter.SanPhamAdapter;
import com.example.duan1.customer.model.SanPham;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ThongKeDoanhThu extends AppCompatActivity {
    RecyclerView rcv;
    ArrayList<DoanhThu> list;
    DatabaseReference databaseReference;
    DoanhThuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        rcv = findViewById(R.id.rcvDoanhThu);
    }
}