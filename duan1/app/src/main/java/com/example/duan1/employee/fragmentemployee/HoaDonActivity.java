package com.example.duan1.employee.fragmentemployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.customer.XemGioHang;
import com.example.duan1.customer.adapter.CartAdapter;
import com.example.duan1.customer.model.Cart;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.adapterempoyee.HoaDonMoiAdapter;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    ArrayList<HoaDonMoi> list;
    RecyclerView rcv;
    String abc;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    HoaDonMoiAdapter adapter;
    String role,key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        rcv = findViewById(R.id.rcv_hdm);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this, 1));
        Intent intent = getIntent();
        try {
            role = intent.getStringExtra("role2");
            key = intent.getStringExtra("key2");
            if (role.equals("3") || role.equals("2")) {
                DatabaseReference databaseRedaference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                databaseRedaference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                        assert mode != null;
                        abc = mode.getTennguoidung();
                        FirebaseRecyclerOptions<HoaDonMoi> options =
                                new FirebaseRecyclerOptions.Builder<HoaDonMoi>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oder").orderByChild("nguoioder").equalTo(abc), HoaDonMoi.class)
                                        .build();
                        adapter = new HoaDonMoiAdapter(options, HoaDonActivity.this);
                        adapter.startListening();
                        rcv.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            } else if (role.equals("4")){
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                        assert mode != null;
                        abc = mode.getEmail();
                        FirebaseRecyclerOptions<HoaDonMoi> options =
                                new FirebaseRecyclerOptions.Builder<HoaDonMoi>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oder").orderByChild("nguoioder").equalTo(abc), HoaDonMoi.class)
                                        .build();
                        adapter = new HoaDonMoiAdapter(options, HoaDonActivity.this);
                        adapter.startListening();
                        rcv.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } catch (NullPointerException e) {
            Toast.makeText(this, "Không Xác Định", Toast.LENGTH_SHORT).show();
        }
    }
}