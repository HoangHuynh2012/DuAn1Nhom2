package com.example.duan1.customer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.CustomProgressDialog;
import com.example.duan1.R;
import com.example.duan1.customer.Login.DangKiActivity;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.customer.Login.DangNhapBangEmail;
import com.example.duan1.customer.adapter.CartAdapter;
import com.example.duan1.customer.fragment.TrangChuFragment;
import com.example.duan1.customer.model.Cart;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.employee.fragmentemployee.HoaDonActivity;
import com.example.duan1.employee.fragmentemployee.HoaDonChiTietActivity;
import com.example.duan1.employee.model.HoaDonChiTiet;
import com.example.duan1.employee.model.HoaDonMoi;
import com.example.duan1.employee.model.Voucher;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class XemGioHang extends AppCompatActivity {
    Toolbar toolbar;
    public static RecyclerView rcv;
    ArrayList<Cart> list;
    CartAdapter adapter;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    int bd;
    String nguoidangnhap,keyId,keyMail;
    public static String abc,tongtientatca,tennguoimua;
    public static TextView nguoimua, sdt,hienthitien,tongtienht;
    EditText maudai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_gio_hang);
        setupToolBar();
        String role = DangNhapActivity.role1;
        String key = DangNhapActivity.key1;
        CustomProgressDialog dialog = new CustomProgressDialog(XemGioHang.this);
        nguoimua = findViewById(R.id.tvTenNguoiNhan);
        hienthitien = findViewById(R.id.hienthitongtien);
        tongtienht = findViewById(R.id.tvGiaTongCong);
        maudai = findViewById(R.id.editmaudai);
        sdt = findViewById(R.id.tvSDTNguoiNhan);
        EditText etdiachi = findViewById(R.id.edtDiaChi);
        rcv = findViewById(R.id.rcvXemGioHang);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
        list = new ArrayList<>();
        if (!(key == null)) {
            DatabaseReference databaseRedaference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
            databaseRedaference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                    assert mode != null;
                    abc = mode.getUsername();
                    nguoimua.setText(abc);
                    try {
                        etdiachi.setText(mode.getDiachi());
                    }catch (NullPointerException e){
                        etdiachi.setText(" ");
                    }
                    sdt.setText(mode.getSdt());
                    FirebaseRecyclerOptions<Cart> options =
                            new FirebaseRecyclerOptions.Builder<Cart>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").orderByChild("tenkhachhang").equalTo(abc), Cart.class)
                                    .build();
                    adapter = new CartAdapter(options, XemGioHang.this);
                    adapter.startListening();
                    rcv.setAdapter(adapter);
                    DatabaseReference datacart = FirebaseDatabase.getInstance().getReference().child("Cart");
                    datacart.orderByChild("tenkhachhang").equalTo(abc).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Cart mode2 = dataSnapshot.getValue(Cart.class);
                                list.add(mode2);
                                int tongtien = Integer.parseInt(mode2.getTongtien());
                                int soluong = Integer.parseInt(mode2.getSoluongsp());
                                ArrayList<Integer> list1 = new ArrayList<>();
                                list1.add(tongtien);
                                for (int i = 0; i < list1.size(); i++) {
                                    bd += list1.get(i);
                                }
                                hienthitien.setText(String.valueOf(bd));
                                tongtienht.setText(String.valueOf(bd));
                                tongtientatca = String.valueOf(bd);

                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                    assert mode != null;
                    keyMail = mode.getUserId();
                    abc = mode.getEmail();
                    nguoimua.setText(abc);
                    try {
                        etdiachi.setText(mode.getDiachi());
                    }catch (NullPointerException e){
                        etdiachi.setText(" ");
                    }
                    FirebaseRecyclerOptions<Cart> options =
                            new FirebaseRecyclerOptions.Builder<Cart>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").orderByChild("tenkhachhang").equalTo(abc), Cart.class)
                                    .build();
                    adapter = new CartAdapter(options, XemGioHang.this);
                    adapter.startListening();
                    rcv.setAdapter(adapter);
                    DatabaseReference datacart = FirebaseDatabase.getInstance().getReference().child("Cart");
                    datacart.orderByChild("tenkhachhang").equalTo(abc).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Cart mode2 = dataSnapshot.getValue(Cart.class);
                                list.add(mode2);
                                int tongtien = Integer.parseInt(mode2.getTongtien());
                                ArrayList<Integer> list1 = new ArrayList<>();
                                list1.add(tongtien);
                                for (int i = 0; i < list1.size(); i++) {
                                    bd += list1.get(i);
                                }
                                hienthitien.setText(String.valueOf(bd));
                                tongtienht.setText(String.valueOf(bd));
                                tongtientatca = String.valueOf(bd);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        Date currentTime = Calendar.getInstance().getTime();
        String timesmap = " " + System.currentTimeMillis();
        nguoidangnhap = TrangChuFragment.nguoidangnhap;
        keyId = TrangChuFragment.keyId;
        Button btn_oder = findViewById(R.id.dathang);
        btn_oder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (tongtienht.getText().toString().equals("")) {
                    Toast.makeText(XemGioHang.this, "Giỏ Hàng Trống", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    EditText etghiChu = findViewById(R.id.edtGhiChu);
                    String diachi = etdiachi.getText().toString().trim();
                    String ghichu = etghiChu.getText().toString().trim();
                    TextView hienthitongtien1 = findViewById(R.id.hienthitongtien);
                    String tongtien = hienthitongtien1.getText().toString();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("oderid", timesmap);
                    hashMap.put("nguoioder", nguoidangnhap);
                    hashMap.put("ngaymua", String.valueOf(currentTime));
                    hashMap.put("diachi", diachi);
                    hashMap.put("ghichu", ghichu);
                    hashMap.put("tongtien", tongtien);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Oder");
                    if (!diachi.isEmpty()) {
                        if (!(key==null)) {
                            DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
                            data.child(key).child("diachi").setValue(diachi);
                        } else {
                            DatabaseReference data1 = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                            data1.child("diachi").setValue(diachi);
                        }
                        //
                        databaseReference.child(timesmap).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                for (int i = 0; i < list.size(); i++) {
                                    String tensp = list.get(i).getTensp();
                                    String giasp = list.get(i).getGia();
                                    String soluong = list.get(i).getSoluongsp();
                                    String tien = String.valueOf(Integer.valueOf(list.get(i).getGia()) * Integer.valueOf(list.get(i).getSoluongsp()));
                                    String id = list.get(i).getId();
                                    HashMap<String, String> hashMap1 = new HashMap<>();
                                    hashMap1.put("tensp", tensp);
                                    hashMap1.put("giasp", giasp);
                                    hashMap1.put("soluong", soluong);
                                    hashMap.put("tien", tien);
                                    databaseReference.child(timesmap).child("SanPham").push().setValue(hashMap1);
                                    dialog.dismiss();
                                    DatabaseReference xoadata = FirebaseDatabase.getInstance().getReference().child("Cart");
                                    xoadata.child(id).removeValue();
                                    finish();

                                }
                            }
                        });
                    } else {
                        Toast.makeText(XemGioHang.this, "Mời bạn nhập địa chỉ", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        Button btn_checkkm = findViewById(R.id.checkmaKm);
        btn_checkkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                EditText makm = findViewById(R.id.editmaudai);
                String maKm = makm.getText().toString().trim();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Voucher");
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Voucher mode = snapshot.getValue(Voucher.class);
                        if (maKm.equals(mode.getMakm())){
                            String giatri = mode.getGiatri();
                            tongtienht.setText(String.valueOf((Float.parseFloat(giatri) * Integer.valueOf(tongtientatca)))+ "đ");
                            hienthitien.setText(String.valueOf((Float.parseFloat(giatri) * Integer.valueOf(tongtientatca))));
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(XemGioHang.this, "Không Có Mã Khuyến Mãi Này", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarXemGioHang);

        if (toolbar == null) return;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up
        return false;
    }
}