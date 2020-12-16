package com.example.duan1.admin.adapteradmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.admin.modeladmin.DoanhThu;
import com.example.duan1.employee.fragmentemployee.ChiTietDonHangActivity;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DoanhThuAdapter extends FirebaseRecyclerAdapter<DoanhThu, DoanhThuAdapter.CartViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;
    public DoanhThuAdapter(@NonNull FirebaseRecyclerOptions<DoanhThu> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull DoanhThuAdapter.CartViewHolder holder, int position, @NonNull DoanhThu model) {
        holder.stt.setText(String.valueOf(model.getSttDT()));
        holder.tenmon.setText(String.valueOf(model.getTenmonDT()));
        holder.tongtien.setText(String.valueOf(model.getTongDT()));


    }

    @NonNull
    @Override
    public DoanhThuAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doanhthu, parent, false);
        return new CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView stt,tongtien,tenmon;
        CardView cardView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.sttdoanhthu);
            tongtien = itemView.findViewById(R.id.tongtienDT);
            tenmon = itemView.findViewById(R.id.TenKH);

            cardView = itemView.findViewById(R.id.carddoanhthu);
        }
    }
}
