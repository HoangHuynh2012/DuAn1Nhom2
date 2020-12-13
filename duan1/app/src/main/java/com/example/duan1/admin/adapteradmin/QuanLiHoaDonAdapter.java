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
import com.example.duan1.employee.adapterempoyee.HoaDonMoiAdapter;
import com.example.duan1.employee.fragmentemployee.ChiTietDonHangActivity;
import com.example.duan1.employee.fragmentemployee.HoaDonChiTietActivity;
import com.example.duan1.employee.model.HoaDonMoi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class QuanLiHoaDonAdapter extends FirebaseRecyclerAdapter<HoaDonMoi, QuanLiHoaDonAdapter.CartViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;
    public QuanLiHoaDonAdapter(@NonNull FirebaseRecyclerOptions<HoaDonMoi> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuanLiHoaDonAdapter.CartViewHolder holder, int position, @NonNull HoaDonMoi model) {
        holder.ngaymua.setText(String.valueOf(model.getNgaymua()));
        holder.tongtien.setText(String.valueOf(model.getTongtien()));
        holder.tenKh.setText(String.valueOf(model.getNguoioder()));
        holder.maHd.setText(String.valueOf(model.getOderid()));
        holder.diachiHd.setText(String.valueOf(model.getDiachi()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                intent.putExtra("idhd",model.getOderid());
                context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public QuanLiHoaDonAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hoadonmoi, parent, false);
        return new CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView ngaymua,tongtien,tenKh,maHd,diachiHd;
        CardView cardView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ngaymua = itemView.findViewById(R.id.datehdmoi);
            tongtien = itemView.findViewById(R.id.tVtongtienhd);
            tenKh = itemView.findViewById(R.id.TenKH);
            maHd = itemView.findViewById(R.id.MaHD);
            diachiHd = itemView.findViewById(R.id.DiaChihd);
            cardView = itemView.findViewById(R.id.cardhoadonmoi);
        }
    }
}
