package com.example.duan1.customer.fragment;

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
import com.example.duan1.customer.DiaChiQuan;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.R;
import com.example.duan1.customer.ThongTinTaiKhoanActivity;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.fragmentemployee.HoaDonActivity;
import com.example.duan1.employee.fragmentvoucher.VoucherActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class TaiKhoanFragment extends Fragment {
    TextView tvDangXuat,tvTTTK,tvCaiDat,tvMaKhuyenMai,tvLichSuMuaHang;
    TextView tvTenKhachHang,tvLienHe;
    ImageView imageView;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    String role ;
    String key;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTTTK = view.findViewById(R.id.tvThongTinKhachHang);
        tvCaiDat = view.findViewById(R.id.tvCaiDat);
        tvDangXuat = view.findViewById(R.id.tvDangXuat);
        tvMaKhuyenMai = view.findViewById(R.id.tvMaKhuyenMai);
        imageView = view.findViewById(R.id.imageView);
        tvLichSuMuaHang = view.findViewById(R.id.tvLichSuMuaHang);
        tvTenKhachHang = view.findViewById(R.id.tvTenKhachHang);
        tvLienHe = view.findViewById(R.id.tvLienHe);
        try {
            role = getActivity().getIntent().getExtras().getString("role");
            key = getActivity().getIntent().getExtras().getString("key");
            if (role.equals("3")){
                storageReference = FirebaseStorage.getInstance().getReference("NguoiDung");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                        tvTenKhachHang.setText(mode.getTennguoidung());

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
            } else if (role.equals("4")){
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                        assert mode != null;
                        tvTenKhachHang.setText(mode.getEmail());
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

            }
        } catch (NullPointerException exception){
            tvTenKhachHang.setText("User Name");
            tvDangXuat.setEnabled(false);

        }
        tvTTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    role = getActivity().getIntent().getExtras().getString("role");
                    key = getActivity().getIntent().getExtras().getString("key");
                    Intent intent = new Intent(getContext(), ThongTinTaiKhoanActivity.class);
                    intent.putExtra("role1",role);
                    intent.putExtra("key1",key);
                    startActivity(intent);
                } catch (NullPointerException exception) {

                }
            }
        });
        tvLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        tvMaKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), VoucherActivity.class));

            }
        });
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
                FirebaseAuth.getInstance().signOut();
            }
        });

        tvCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DiaChiQuan.class));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }
}