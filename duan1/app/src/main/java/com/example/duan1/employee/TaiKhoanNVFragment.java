package com.example.duan1.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.duan1.customer.CaiDatActivity;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.R;
import com.example.duan1.customer.ThongTinTaiKhoanActivity;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.fragmentemployee.HoaDonActivity;
import com.example.duan1.employee.fragmentemployee.HoaDonChiTietActivity;
import com.example.duan1.employee.fragmentemployee.LichSuActivity;
import com.example.duan1.employee.fragmentvoucher.VoucherActivity;
import com.example.duan1.employee.model.HoaDonMoi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class TaiKhoanNVFragment extends Fragment {
    TextView tvDangXuat,tvTTTK,tvCaiDat,tvvoucher,tvLichsu,tvhoadon;
    TextView tvTenNV;
    ImageView imageView;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String key,role;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTTTK = view.findViewById(R.id.tvThongTinKhachHang);
        tvDangXuat = view.findViewById(R.id.tvDangXuat);
        tvCaiDat = view.findViewById(R.id.tvCaiDat);
        tvvoucher = view.findViewById(R.id.tvKhuyenMai);
        tvLichsu = view.findViewById(R.id.tvLichSuMuaHangNv);
        tvTenNV = view.findViewById(R.id.tvTenNV);
        imageView = view.findViewById(R.id.imageView);
        role = getActivity().getIntent().getExtras().getString("role");
        key = getActivity().getIntent().getExtras().getString("key");
        databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                assert mode != null;
                tvTenNV.setText(mode.getTennguoidung());

                if (mode.getImageUrl().equals("default")){
                    imageView.setImageResource(R.drawable.ic_userrr);
                } else {
                    Glide.with(getContext()).load(mode.getImageUrl()).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tvTTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                key = getActivity().getIntent().getExtras().getString("key");
                Intent intent = new Intent(getContext(), TaiKhoanNVActivity.class);
                intent.putExtra("key1",key);
                startActivity(intent);

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
        tvvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), VoucherActivity.class));
            }
        });
        tvLichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    role = getActivity().getIntent().getExtras().getString("role");
                    key = getActivity().getIntent().getExtras().getString("key");
                    Intent intent = new Intent(getContext(), HoaDonActivity.class);
                    intent.putExtra("role2",role);
                    intent.putExtra("key2",key);
                    startActivity(intent);
                } catch (NullPointerException exception) {

                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tai_khoan_n_v, container, false);
    }
}
