package com.example.duan1.admin.thongke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan1.R;

public class ThongKeLoiNhuan extends AppCompatActivity {
    TextView tvTongThu,tvTongGD,tvThuDonTC,tvTongDonTC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_loi_nhuan);
        tvTongThu = findViewById(R.id.tvTongThu);
        tvTongGD = findViewById(R.id.tvTongGiaoDich);
        tvThuDonTC = findViewById(R.id.tvThuDonThanhCong);
        tvTongDonTC = findViewById(R.id.tvGiaoDichDonThanhCong);
    }
}