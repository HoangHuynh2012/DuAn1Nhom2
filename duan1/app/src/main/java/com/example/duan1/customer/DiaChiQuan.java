package com.example.duan1.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;

public class DiaChiQuan extends AppCompatActivity {
    CardView cv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_quan);
        tv = findViewById(R.id.tvPhone);
        setupToolBar();
        cv = findViewById(R.id.cv_phone);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = tv.getText().toString();
                if (!TextUtils.isEmpty(phone)){
                    String dial = "tel:" + phone;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(DiaChiQuan.this,"Enter a phone number",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDiaChi);

        if (toolbar == null) return;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up
        return false;
    }
}