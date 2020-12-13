package com.example.duan1.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SuaTTActivity extends AppCompatActivity {
   String role,key;
   FirebaseAuth firebaseAuth;
   String keyEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_t_t);
        EditText ten = findViewById(R.id.suatenkk);
        EditText sdt = findViewById(R.id.suasdtkk);
        EditText email = findViewById(R.id.suamail);
        Button btn_update = findViewById(R.id.btnUploadImagean);
        Button quaylai = findViewById(R.id.quaylai);
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent1 = new Intent();
        role = ThongTinTaiKhoanActivity.role;
        key = ThongTinTaiKhoanActivity.key;
        if (role.equals("3")) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                    assert mode != null;
                    ten.setText(mode.getTennguoidung());
                    sdt.setText(mode.getSdt());
                    email.setText(mode.getGmail());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SuaTTActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (role.equals("4")) {
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            keyEmail = firebaseUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                    assert mode != null;
                    ten.setText(mode.getEmail());
                    email.setText(mode.getEmail());
                    sdt.setText("");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SuaTTActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
                        btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tennguoidung", ten.getText().toString());
                        map.put("sdt", sdt.getText().toString());
                        map.put("gmail", email.getText().toString());

                        if (role.equals("3")) {
                            FirebaseDatabase.getInstance().getReference().child("NguoiDung").child(key).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       Toast.makeText(SuaTTActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                   } else {
                                       Toast.makeText(SuaTTActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                                   }
                                }
                            });

                        } else {
                            FirebaseDatabase.getInstance().getReference().child("NguoiDungMail").child(keyEmail).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SuaTTActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SuaTTActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });




    }
}