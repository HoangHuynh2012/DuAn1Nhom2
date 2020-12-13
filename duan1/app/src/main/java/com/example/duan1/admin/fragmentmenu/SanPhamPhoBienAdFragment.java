package com.example.duan1.admin.fragmentmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.admin.ThemSanPhamAn;
import com.example.duan1.admin.ThemSanPhamPhoBien;
import com.example.duan1.admin.adapteradmin.SanPhamPhoBienAdAdapter;
import com.example.duan1.admin.modeladmin.SanPhamAd;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SanPhamPhoBienAdFragment extends Fragment {
    RecyclerView rcv;
    ArrayList<SanPhamAd> list;
    SanPhamPhoBienAdAdapter adapter;
    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham_pho_bien_ad,container,false);
        rcv = view.findViewById(R.id.rcv_sanpham);
        list = new ArrayList<>();
        rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        FirebaseRecyclerOptions<SanPhamAd> options =
                new FirebaseRecyclerOptions.Builder<SanPhamAd>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SanPhamPhoBien"), SanPhamAd.class)
                        .build();
        adapter = new SanPhamPhoBienAdAdapter(options, getContext());
        adapter.startListening();
        rcv.setAdapter(adapter);

        floatingActionButton = view.findViewById(R.id.float_addsppb);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ThemSanPhamPhoBien.class);
                startActivity(i);
            }
        });



        return view;
    }

}