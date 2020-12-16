package com.example.duan1.admin.thongke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.duan1.R;
import com.example.duan1.employee.fragmentemployee.LichSuActivity;

public class ThongKeActivity extends AppCompatActivity {
TextView tkdoanhthu,tkloinhuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tkdoanhthu = findViewById(R.id.thongkedoanhthu);
        tkloinhuan = findViewById(R.id.thongkeloinhuan);
        tkdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this, ThongKeDoanhThu.class));
            }
        });
        tkloinhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this, ThongKeLoiNhuan.class));
            }
        });
    }
}