package com.example.duan1.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.R;
import com.example.duan1.admin.fragmentgiamgia.VouCherAdActivity;
import com.example.duan1.admin.fragmentqlnv.QuanLyNVActivity;
import com.example.duan1.admin.quanlykh.QLkhachhangActivity;
import com.example.duan1.admin.thongke.ThongKeActivity;
import com.example.duan1.customer.CaiDatActivity;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.employee.TaiKhoanNVActivity;
import com.example.duan1.employee.fragmentemployee.LichSuActivity;
import com.example.duan1.employee.model.LichSu;

public class TaiKhoanAdFragment extends Fragment {
    TextView tvDangXuat,tvTTTK,tvCaiDat,tvvoucher,tvLichsu,tvqlnv,tvqlkh,tvthongke;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTTTK = view.findViewById(R.id.tvThongTin);
        tvDangXuat = view.findViewById(R.id.tvDangXuat);
        tvCaiDat = view.findViewById(R.id.tvCaiDat);
        tvvoucher = view.findViewById(R.id.tvKhuyenMai);
        tvLichsu = view.findViewById(R.id.tvLichSuMuaHang);
        tvqlnv = view.findViewById(R.id.tvThongTinNV);
        tvqlkh = view.findViewById(R.id.tvThongTinKH);
        tvthongke = view.findViewById(R.id.tvThongKe);
        tvTTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TaiKhoanAdActivity.class));
            }
        });
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
            }
        });
        tvCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CaiDatActivity.class));
            }
        });
        tvqlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), QuanLyNVActivity.class));
            }
        });
        tvqlkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), QLkhachhangActivity.class));
            }
        });
        tvvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), VouCherAdActivity.class));
            }
        });
        tvLichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LichSuActivity.class));
            }
        });
        tvthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ThongKeActivity.class));
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tai_khoan_ad, container, false);
    }
}
