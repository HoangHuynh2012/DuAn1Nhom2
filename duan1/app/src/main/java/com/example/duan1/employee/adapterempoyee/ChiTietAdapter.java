package com.example.duan1.employee.adapterempoyee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.employee.model.ChiTietDonHang;

import java.util.ArrayList;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ChiTietDonHang> list;

    public ChiTietAdapter(Context context, ArrayList<ChiTietDonHang> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_chitietdonhang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stt.setText(list.get(position).getStt());
        holder.tenSP.setText(list.get(position).getTenSP());
        holder.thanhtien.setText(list.get(position).getThanhtien());
        holder.soluong.setText(list.get(position).getSoluong());
        holder.dongia.setText(list.get(position).getDongia());
        holder.cardHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  stt,tenSP,thanhtien,soluong,dongia;
        CardView cardHD;

        public MyViewHolder(View view) {
            super(view);

            stt = view.findViewById(R.id.tvstt);
            thanhtien = view.findViewById(R.id.tvthanhtien);
            soluong = view.findViewById(R.id.tvsoluongchitiet);
            dongia = view.findViewById(R.id.tvdongia);
            tenSP= view.findViewById(R.id.tvtenmon);
            cardHD = view.findViewById(R.id.cardhoadonmoi);
        }
    }
}
