package com.example.duan1.customer.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.customer.XemGioHang;
import com.example.duan1.customer.model.NguoiDungMailMode;
import com.example.duan1.customer.model.NguoiDungMode;
import com.example.duan1.customer.model.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHolder> {
    ArrayList<SanPham> list;
    Context context;
    int bd;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvPrice.setText(list.get(position).getPrice());
        Glide.with(holder.image.getContext()).load(list.get(position).getImage()).into(holder.image);
        holder.cardSPPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogThem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvTitle, tvPrice;
        CardView cardSPPB;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imgSanPhamAn);
            tvTitle = view.findViewById(R.id.tvTenSPA);
            tvPrice = view.findViewById(R.id.tvGia);
            cardSPPB = view.findViewById(R.id.cardSPA);

        }
    }

    private void diaLogThem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogthemsanpham, null);
        builder.setView(view);
        Button btnHuy, btnDongY;

        final Dialog dialog = builder.create();
        final TextView tvTitle = view.findViewById(R.id.tvTitle);
        final TextView tvPrice = view.findViewById(R.id.tvPrice);
        final ImageView tvImage = view.findViewById(R.id.imgThemSanPhamPB);
        final ImageView btnCong = view.findViewById(R.id.btnCong);
        final ImageView btnTru = view.findViewById(R.id.btnTru);
        final TextView tvSoLuong = view.findViewById(R.id.tvThemSoLuong);
        final TextView tvDescription = view.findViewById(R.id.tvGioiThieuMonAn);
        final LikeButton heard = view.findViewById(R.id.star_button);
        final Button btnThemSoLuong = view.findViewById(R.id.btnThemSoLuong);
        int gia = Integer.parseInt(list.get(position).getPrice());
        Intent intent = ((Activity) context).getIntent();
        DatabaseReference dataCart = FirebaseDatabase.getInstance().getReference().child("Cart");
        btnThemSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String role = intent.getExtras().getString("role");
                    Log.i("abc", role);
                    if (role.equals("4")) {
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDungMail").child(firebaseUser.getUid());
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                NguoiDungMailMode mode = snapshot.getValue(NguoiDungMailMode.class);
                                assert mode != null;
                                String tenkhachhang = mode.getEmail();
                                String id = databaseReference.push().getKey();
                                String tensp = tvTitle.getText().toString().trim();
                                String soluongsp = tvSoLuong.getText().toString().trim();
                                String gia = tvPrice.getText().toString().trim();
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("id", id);
                                hashMap.put("tenkhachhang", tenkhachhang);
                                hashMap.put("tensp", tensp);
                                hashMap.put("soluongsp", soluongsp);
                                hashMap.put("gia", gia);
                                hashMap.put("tongtien", String.valueOf(Integer.valueOf(soluongsp) * Integer.valueOf(gia)));
                                dataCart.child(id).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                                            builder1.setMessage("Thêm Sản Phẩm Vào Giỏ Hàng Thành Công.");
                                            builder1.setCancelable(true);
                                            builder1.setPositiveButton(
                                                    "Tiếp Tực Mua Sắm!",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            builder1.setNegativeButton(
                                                    "Đi đến Giỏ Hàng",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                          Intent intent = new Intent(context, XemGioHang.class);
                                                          context.startActivity(intent);
                                                            dialog.cancel();
                                                        }
                                                    });
                                            androidx.appcompat.app.AlertDialog alert11 = builder1.create();
                                            alert11.show();
                                        } else {
                                            Toast.makeText(context, "Lỗi: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(v.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    if (role.equals("3") || (role.equals("2"))) {
                        String key = intent.getExtras().getString("key");
                        Log.i("key", key);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(key);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                NguoiDungMode mode = snapshot.getValue(NguoiDungMode.class);
                                assert mode != null;
                                String id = databaseReference.push().getKey();
                                String tenkhachhang = mode.getUsername();
                                String tensp = tvTitle.getText().toString().trim();
                                String soluongsp = tvSoLuong.getText().toString().trim();
                                String gia = tvPrice.getText().toString().trim();
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("id", id);
                                hashMap.put("tenkhachhang", tenkhachhang);
                                hashMap.put("tensp", tensp);
                                hashMap.put("soluongsp", soluongsp);
                                hashMap.put("gia", gia);
                                hashMap.put("tongtien", String.valueOf(Integer.valueOf(soluongsp) * Integer.valueOf(gia)));
                                dataCart.child(id).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                                            builder1.setMessage("Thêm Sản Phẩm Vào Giỏ Hàng Thành Công.");
                                            builder1.setCancelable(true);
                                            builder1.setPositiveButton(
                                                    "Tiếp Tục Mua Sắm!",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            builder1.setNegativeButton(
                                                    "Đi đến Giỏ Hàng",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            Intent intent = new Intent(context, XemGioHang.class);
                                                            context.startActivity(intent);
                                                            dialog.cancel();
                                                        }
                                                    });
                                            androidx.appcompat.app.AlertDialog alert11 = builder1.create();
                                            alert11.show();
                                        } else {
                                            Log.i("loi", task.getException().getMessage());
                                        }
                                    }

                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } catch (NullPointerException e) {
                    ProgressDialog dialog = ProgressDialog.show(((Activity) context), "",
                            "Hình Như Bạn Chưa Đăng Nhập...", true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.cancel();
                        }
                    }, 2000);
                }
            }

        });
        heard.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(context, "Yêu thích", Toast.LENGTH_LONG).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(context, "Bỏ yêu thích", Toast.LENGTH_LONG).show();
            }
        });
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd++;
                tvSoLuong.setText(String.valueOf(bd));
                btnThemSoLuong.setText(String.valueOf(bd * gia) + "đ");
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd <= 1) {
                    tvSoLuong.setText("Chọn Số Lượng");
                    btnThemSoLuong.setText(gia + "đ");
                } else {
                    bd--;
                    tvSoLuong.setText(String.valueOf(bd));
                    btnThemSoLuong.setText(String.valueOf(bd * gia) + "đ");
                }
            }
        });
        //fill vao view cua dialog
        tvTitle.setText(list.get(position).getTitle());
        tvPrice.setText(list.get(position).getPrice());
        tvDescription.setText(list.get(position).getGioithieu());
        Glide.with(tvImage.getContext()).load(list.get(position).getImage()).into(tvImage);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
