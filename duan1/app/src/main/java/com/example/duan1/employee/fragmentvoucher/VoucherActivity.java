package com.example.duan1.employee.fragmentvoucher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.customer.adapter.SanPhamAdapter;
import com.example.duan1.customer.model.SanPham;
import com.example.duan1.employee.adapterempoyee.VocherAdapter;
import com.example.duan1.employee.model.Voucher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoucherActivity extends AppCompatActivity {
    ArrayList<Voucher> list;
    RecyclerView rcv;
    DatabaseReference databaseReference;
    VocherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        rcv = findViewById(R.id.rcv_vcchay);

        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(this,1));
        databaseReference = FirebaseDatabase.getInstance().getReference("Voucher");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Voucher mode = dataSnapshot.getValue(Voucher.class);
                    list.add(mode);
                }
                adapter = new VocherAdapter(VoucherActivity.this,list);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VoucherActivity.this, "OPSS", Toast.LENGTH_SHORT).show();
            }
        });



    }

}