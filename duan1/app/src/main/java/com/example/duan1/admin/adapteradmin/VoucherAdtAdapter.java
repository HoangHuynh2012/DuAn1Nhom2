package com.example.duan1.admin.adapteradmin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.admin.modeladmin.KhachHang;
import com.example.duan1.admin.modeladmin.VoucherAd;
import com.example.duan1.employee.adapterempoyee.VocherAdapter;
import com.example.duan1.employee.model.Voucher;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VoucherAdtAdapter extends FirebaseRecyclerAdapter<Voucher, VoucherAdtAdapter.CartViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public VoucherAdtAdapter(@NonNull FirebaseRecyclerOptions<Voucher> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VoucherAdtAdapter.CartViewHolder holder, int position, @NonNull Voucher model) {
        holder.tenkm.setText(model.getTenKM());
        holder.motakm.setText(model.getMota());
        holder.makm.setText(model.getMakm());
        holder.ngay.setText(model.getNgaybatdau());

        holder.btn_xoavoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Voucher")
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

            }
        });
    }

    @NonNull
    @Override
    public VoucherAdtAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voucherad, parent, false);
        return new CartViewHolder(view);
    }



    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tenkm,motakm,makm,ngay;
        Button btn_xoavoucher;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tenkm = itemView.findViewById(R.id.tvTenKM);
            ngay = itemView.findViewById(R.id.batdaugiamgia);
            motakm = itemView.findViewById(R.id.tvMotaKM);
            makm = itemView.findViewById(R.id.maKMduyet);
            btn_xoavoucher = itemView.findViewById(R.id.btXoa);
        }
    }
}
