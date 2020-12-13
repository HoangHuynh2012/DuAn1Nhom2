package com.example.duan1.employee.adapterempoyee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.employee.model.HoaDonChiTiet;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class HoaDonChiTietAdapter  extends FirebaseRecyclerAdapter<HoaDonChiTiet, HoaDonChiTietAdapter.CartViewHolder> {
    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HoaDonChiTietAdapter(@NonNull FirebaseRecyclerOptions<HoaDonChiTiet> options,Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull HoaDonChiTietAdapter.CartViewHolder holder, int position, @NonNull HoaDonChiTiet model) {
        holder.tensp.setText(model.getTensp());
        holder.giasp.setText(model.getGiasp());
        holder.soluongsp.setText(model.getSoluong());
    }

    @NonNull
    @Override
    public HoaDonChiTietAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hoadonchitiet, parent, false);
        return new HoaDonChiTietAdapter.CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tensp,giasp,soluongsp;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.tensphd);
            giasp = itemView.findViewById(R.id.giasphd);
            soluongsp = itemView.findViewById(R.id.soluongsphd);
        }
    }
}
