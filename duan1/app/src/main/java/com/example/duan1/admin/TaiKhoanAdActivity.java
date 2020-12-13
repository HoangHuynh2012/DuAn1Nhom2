package com.example.duan1.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.TaiKhoanNVActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaiKhoanAdActivity extends AppCompatActivity {
    Toolbar tbTttk;
    ImageView imgHinhnv;
    TextView gmail,sdt,ten;
    TextView doimk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_ad);
        tbTttk = findViewById(R.id.toolbarThongTinTaiKhoan);
        imgHinhnv = findViewById(R.id.image_Hinhnv);
        gmail = findViewById(R.id.tVgmailAd);
        sdt = findViewById(R.id.tvsdtAd);
        ten = findViewById(R.id.tvTenAd);
        doimk = findViewById(R.id.doimatkhauad);
        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanAdActivity.this,DoiMatKhauAdminActivity.class));
            }
        });
        String key = DangNhapActivity.key1;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                assert mode != null;
                gmail.setText(mode.getGmail());
                sdt.setText(mode.getSdt());
                ten.setText(mode.getTennguoidung());
                if (mode.getImageUrl().equals("default")) {
                    imgHinhnv.setImageResource(R.drawable.ic_userrr);
                } else {
                    Glide.with(TaiKhoanAdActivity.this).load(mode.getImageUrl()).into(imgHinhnv);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setSupportActionBar(tbTttk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        imgHinhnv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        TaiKhoanAdActivity.this,R.style.BottomSheet
//                );
//                View bottomSheet = LayoutInflater.from(getApplicationContext())
//                        .inflate(R.layout.bottm_sheet_layout,
//                                (LinearLayout)findViewById(R.id.bottom_sheetCont));
//
//                bottomSheet.findViewById(R.id.tvChonAnh).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(TaiKhoanAdActivity.this, "Chọn ảnh !!!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheet);
//                bottomSheetDialog.show();
//
//
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}