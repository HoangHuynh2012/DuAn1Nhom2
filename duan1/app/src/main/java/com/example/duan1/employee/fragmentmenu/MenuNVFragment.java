package com.example.duan1.employee.fragmentmenu;

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
import com.example.duan1.customer.fragment.SanPhamAnFragment;
import com.example.duan1.customer.fragment.SanPhamPhoBienFragment;
import com.example.duan1.customer.fragment.SanPhamUongFragment;
import com.google.android.material.tabs.TabLayout;


public class MenuNVFragment extends Fragment {
    ViewPager viewPager;
    ImageView imgGioHang;

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
        View view = inflater.inflate(R.layout.fragment_menu_n_v,container,false);
        imgGioHang = view.findViewById(R.id.imgGioHangnv);

        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), XemGioHang.class);
                startActivity(i);
            }
        });

        viewPager = view.findViewById(R.id.viewPagernv);
        addTab(viewPager);
        ((TabLayout)view.findViewById(R.id.tabLayoutnv)).setupWithViewPager(viewPager);
        return view;
    }


}