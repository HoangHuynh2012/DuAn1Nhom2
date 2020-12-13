package com.example.duan1.admin.fragmentmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.duan1.R;
import com.example.duan1.admin.ThemSanPhamAn;
import com.example.duan1.customer.adapter.TabLayoutAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class MenuAdFragment extends Fragment {
    ViewPager viewPager;
//    FloatingActionButton floatingActionButton;

    public void addTab(ViewPager viewPager){
        TabLayoutAdapter adapter = new TabLayoutAdapter(getChildFragmentManager());
        adapter.add(new SanPhamPhoBienAdFragment(),"Phổ biến");
        adapter.add(new SanPhamUongAdFragment(),"Loại uống");
        adapter.add(new SanPhamAnAdFragment(),"Loại ăn");

        viewPager.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_ad,container,false);

        viewPager = view.findViewById(R.id.viewPagerad);
        addTab(viewPager);
        ((TabLayout)view.findViewById(R.id.tabLayoutad)).setupWithViewPager(viewPager);
        return view;
    }


}