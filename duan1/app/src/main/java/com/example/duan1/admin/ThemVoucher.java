package com.example.duan1.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ThemVoucher extends AppCompatActivity {
    Button btDuyet;
    EditText tenkm,mota,makm,giatri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_voucher);
        btDuyet = findViewById(R.id.btDuyetVoucher);
        tenkm = findViewById(R.id.tenkhuyenmai);
        mota = findViewById(R.id.motavoucher);
        makm = findViewById(R.id.makhuyenmai);
        Button btnchonngay = findViewById(R.id.btn_chonngay);
        giatri = findViewById(R.id.giatrikm);
        TextView hienthingay = findViewById(R.id.tVhienthingaythang);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        btnchonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemVoucher.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i, i1, i2);
                        hienthingay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, years, months, dayOfWeek);
                datePickerDialog.show();
            }
        });

        btDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tenkm.getText().toString().isEmpty() || mota.getText().toString().isEmpty() || makm.getText().toString().isEmpty() || hienthingay.getText().toString().isEmpty() || giatri.getText().toString().isEmpty()) {
                    Toast.makeText(ThemVoucher.this, "Không Bỏ Trống Trường Nào", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference mDatabaseRef;
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Voucher");
                    String id = mDatabaseRef.push().getKey();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", id);
                    hashMap.put("tenKM", tenkm.getText().toString());
                    hashMap.put("mota", mota.getText().toString());
                    hashMap.put("makm", makm.getText().toString());
                    hashMap.put("ngaybatdau", hienthingay.getText().toString());
                    float giatrikm = (Float.parseFloat(giatri.getText().toString()));
                    float giatrikm1 = Float.parseFloat(String.valueOf(giatrikm / 100));
                    hashMap.put("giatri", String.valueOf(giatrikm1));
                    mDatabaseRef.child(id).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ThemVoucher.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}