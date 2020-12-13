package com.example.duan1.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.R;
import com.example.duan1.customer.adapter.DanhChoBanAdapter;
import com.example.duan1.customer.adapter.TinTucAdapter;
import com.example.duan1.customer.model.DanhChoBan;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.customer.model.TinTuc;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment implements TinTucAdapter.ListItemClickListener {

    private ImageSlider slide;
    RecyclerView rcvTinTuc,rcvUuDai;
    Button btnDangNhap;
    List<TinTuc> list;
    List<DanhChoBan> list1;
    String role,key;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    public static  String nguoidangnhap;
    public static  String keyId;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu,container,false);
        slide = view.findViewById(R.id.ImageSlide);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel
                ("https://file.hstatic.net/1000075078/file/go_green_1_a4bd9d34a2aa4b8e813dccff97553671_1024x1024.jpg","The Coffee House đồng hành cùng bạn \"Go Green\"!"));
        slideModels.add(new SlideModel
                ("https://file.hstatic.net/1000075078/file/hinode_-_img_9322_e733cde7255641d0be8a31afc879b379_1024x1024.jpg","Top 10 cửa hàng The Coffee House bạn nên trải nghiệm tại Hà Nội"));
        slideModels.add(new SlideModel
                ("https://file.hstatic.net/1000075078/file/freeupsize_vuong_b96b42170b864cecb3e4a5a0876229ce.jpg","7 NGÀY FREE UPSIZE - YÊU BẠN NỮ KHÔNG THỂ NÀO CAI"));
        slide.setImageList(slideModels,true);
        //Hooks
        rcvTinTuc = view.findViewById(R.id.my_recycler);
        rcvUuDai = view.findViewById(R.id.my_recycler2);

        rcvTinTuc.setHasFixedSize(true);
        list = new ArrayList<>();
        list.add(new TinTuc(R.drawable.s1, "Với bộ ba trái cây tại THE COFFEE HOUSE \"Go Green\"!","Năm nay, The Coffee House mang lại sự đột phá trong hương vị" ));
        list.add(new TinTuc(R.drawable.s2, "Giảm 25% cho khách VinID","Trà Ổi Thanh Long Macchiato sử dụng," +
                " trà hoa lài nguyên lá hảo hạng phối trộn với nước ép ổi thơm ngát,"));
        list.add(new TinTuc(R.drawable.s3, "Đánh gái về hệ thống cửa hàng","THE COFFEE HOUSE là một hệ thống cửa hàng, chuối CAFÉ với không gian lớn, " +
                " tại các vị trí rất đẹp tại trung tâm các tỉnh, thành phố,"));
        list.add(new TinTuc(R.drawable.s4, "Khám phá trà ổi Macchiato","Trà Ổi Thanh Long Macchiato sử dụng" +
                " trà hoa lài nguyên lá hảo hạng phối trộn với nước ép ổi thơm ngát,"));

        TinTucAdapter adapter = new TinTucAdapter(getActivity(), (ArrayList<TinTuc>) list);

        rcvTinTuc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvTinTuc.setAdapter(adapter);


        rcvUuDai.setHasFixedSize(true);
        list1 = new ArrayList<>();
        list1.add(new DanhChoBan(R.drawable.uudai1, "Giảm 25% cho khách VinID","Từ ngày 16/03/2020 – 03/04/2020, mỗi khách sẽ có cơ hội nhận được voucher giảm giá 25% các món nước khi người dùng app VinID"));
        list1.add(new DanhChoBan(R.drawable.uudai2, "Khám phá trà ổi Macchiato ","Trà Ổi Thanh Long Macchiato sử dụng " +
                "trà hoa lài nguyên lá hảo hạng phối trộn với nước ép ổi thơm ngát," ));
        list1.add(new DanhChoBan(R.drawable.uudai3, "Đánh gái về hệ thống cửa hàng","THE COFFEE HOUSE là một hệ thống cửa hàng, chuối CAFÉ với không gian lớn, tại các vị trí rất đẹp tại trung tâm các tỉnh, thành phố"));
        list1.add(new DanhChoBan(R.drawable.uudai4, "SỔ ƯU ĐÃI MỪNG 300 QUÁN","Với 300 quán trải dọc đất nước," +
                " Highlands Coffee tự hào là nơi lưu giữ trăm chuyện đậm đà của người Việt khắp 3 miền"));
        list1.add(new DanhChoBan(R.drawable.uudai5, "\"Đổi gió\" Với bộ ba trái cây tại THE COFFEE HOUSE","Năm nay, The Coffee House mang lại sự đột phá trong hương vị"));

        DanhChoBanAdapter adapter1 = new DanhChoBanAdapter(getActivity(), (ArrayList<DanhChoBan>) list1);

        rcvUuDai.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvUuDai.setAdapter(adapter1);

        btnDangNhap = view.findViewById(R.id.btnDangNhapTrangChu);
        btnDangNhap = view.findViewById(R.id.btnDangNhapTrangChu);
        try {
            role = getActivity().getIntent().getExtras().getString("role");
            key = getActivity().getIntent().getExtras().getString("key");
            if (role.equals("3")) {
                storageReference = FirebaseStorage.getInstance().getReference("NguoiDung");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                        assert mode != null;
                        keyId = mode.getUserid();
                        btnDangNhap.setText(mode.getTennguoidung());
                        nguoidangnhap = mode.getTennguoidung();
                        btnDangNhap.setEnabled(false);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (role.equals("4")) {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                        assert mode != null;
                        btnDangNhap.setText(mode.getEmail());
                        keyId = mode.getUserId();
                        nguoidangnhap = mode.getEmail();
                        btnDangNhap.setEnabled(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (role.equals("2")){
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                        btnDangNhap.setText(mode.getTennguoidung());
                        keyId = mode.getUserid();
                        nguoidangnhap = mode.getTennguoidung();
                        btnDangNhap.setEnabled(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else if (role.equals("1")){
                btnDangNhap.setText("Admin");
                btnDangNhap.setEnabled(false);
            }
        } catch (NullPointerException exception) {
            btnDangNhap.setText("Đăng Nhập");

        }
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));

            }
        });

        super.onViewCreated(view, savedInstanceState);
        return view;
    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {
    }


}