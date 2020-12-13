package com.example.duan1.customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.customer.adapter.SanPhamAdapter;
import com.example.duan1.customer.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SanPhamUongFragment extends Fragment {
    ArrayList<SanPham> list;
    RecyclerView rcv;
    DatabaseReference databaseReference;
    SanPhamAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham_uong,container,false);
        rcv = view.findViewById(R.id.rcv_sanpham);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        databaseReference = FirebaseDatabase.getInstance().getReference("SanPhamUong");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SanPham mode = dataSnapshot.getValue(SanPham.class);
                    list.add(mode);
                }
                adapter = new SanPhamAdapter(getContext(),list);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "OPSS", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}