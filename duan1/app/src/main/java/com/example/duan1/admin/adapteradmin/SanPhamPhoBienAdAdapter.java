package com.example.duan1.admin.adapteradmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.R;
import com.example.duan1.admin.modeladmin.SanPhamAd;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SanPhamPhoBienAdAdapter extends FirebaseRecyclerAdapter<SanPhamAd, SanPhamPhoBienAdAdapter.CartViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public SanPhamPhoBienAdAdapter(@NonNull FirebaseRecyclerOptions<SanPhamAd> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull SanPhamAd model) {
        holder.tvTitle.setText(model.getTitle());
        holder.tvPrice.setText(model.getPrice());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa sản phẩm này không");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseDatabase.getInstance().getReference()
                                        .child("SanPhamPhoBien")
                                        .child(getRef(position).getKey())
                                        .setValue(null)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });
                androidx.appcompat.app.AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                final View view = inflater.inflate(R.layout.edit_sanpham, null);
                builder.setView(view);

                final EditText tensp = (EditText) view.findViewById(R.id.edit_tensp);
                final EditText gia = (EditText) view.findViewById(R.id.edit_giasp);
                final EditText soluong = (EditText) view.findViewById(R.id.edit_slsp);
                final EditText gioithieu = (EditText) view.findViewById(R.id.edit_gioithieu);
                final ImageView hinh = (ImageView) view.findViewById(R.id.imgPreview_editsp);
                final Button btn_xacnhan = (Button) view.findViewById(R.id.btnUploadImage_editsp);

                tensp.setText(model.getTitle());
                gia.setText(model.getPrice());
                soluong.setText(model.getSoluong());
                gioithieu.setText(model.getGioithieu());
                Glide.with((Activity) context).load(model.getImage()).into(hinh);


                final Dialog dialog = builder.create();
                dialog.show();

                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tensp.getText().toString().isEmpty() || gia.getText().toString().isEmpty() || soluong.getText().toString().isEmpty() || gioithieu.getText().toString().isEmpty()) {
                            Toast.makeText(context, "Không Bỏ Trống Trường Nào", Toast.LENGTH_SHORT).show();
                        } else {
                            Map<String, Object> map = new HashMap<>();
                            map.put("title", tensp.getText().toString());
                            map.put("price", gia.getText().toString());
                            map.put("soluong", soluong.getText().toString());
                            map.put("gioithieu", gioithieu.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("SanPhamPhoBien")
                                    .child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            dialog.dismiss();
                                            Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                        }
                                    });
                        }
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sanphamad, parent, false);
        return new CartViewHolder(view);    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvTitle, tvPrice;
        CardView cardSPPB;
        Button btn_edit,btn_xoa;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgSanPhamAn);
            tvTitle = itemView.findViewById(R.id.tvTenSPA);
            tvPrice = itemView.findViewById(R.id.tvGia);
            cardSPPB = itemView.findViewById(R.id.cardSPA);
            btn_edit = itemView.findViewById(R.id.btn_editsp);
            btn_xoa = itemView.findViewById(R.id.btn_deletesp);
        }


    }
}
