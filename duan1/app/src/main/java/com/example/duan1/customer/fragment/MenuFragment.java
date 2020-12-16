package com.example.duan1.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.duan1.R;
import com.example.duan1.customer.XemGioHang;
import com.example.duan1.customer.adapter.TabLayoutAdapter;
import com.example.duan1.employee.fragmentmenu.XemGioHangNV;
import com.google.android.material.tabs.TabLayout;


public class MenuFragment extends Fragment {
    ViewPager viewPager;
    ImageView imgGioHang;
    String key,role;

    public void addTab(ViewPager viewPager){
        TabLayoutAdapter adapter = new TabLayoutAdapter(getChildFragmentManager());
        adapter.add(new SanPhamPhoBienFragment(),"Phổ biến");
        adapter.add(new SanPhamUongFragment(),"Loại uống");
        adapter.add(new SanPhamAnFragment(),"Loại ăn");

        viewPager.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        imgGioHang = view.findViewById(R.id.imgGioHang);
        try {
            key = getActivity().getIntent().getExtras().getString("key");
            role = getActivity().getIntent().getExtras().getString("role");

            imgGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (role.equals("3") || role.equals("4")) {
                        Intent i = new Intent(getActivity(), XemGioHang.class);
                        startActivity(i);
                    } else if (role.equals("2")) {
                        Intent i = new Intent(getActivity(), XemGioHangNV.class);
                        startActivity(i);
                    }
                }
            });
        }catch (NullPointerException e){

        }

        viewPager = view.findViewById(R.id.viewPager);
        addTab(viewPager);
        ((TabLayout)view.findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
        return view;
    }


}