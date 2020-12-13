package com.example.duan1.employee.adapterempoyee;

import android.app.Activity;
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
import com.example.duan1.employee.fragmentemployee.ChiTietDonHangActivity;
import com.example.duan1.employee.fragmentemployee.LichSuActivity;
import com.example.duan1.employee.model.LichSu;

import java.util.ArrayList;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<LichSu> list;

    public LichSuAdapter(Context context, ArrayList<LichSu> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichsu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ma.setText(list.get(position).getMa());
        holder.timegiao.setText(list.get(position).getTggiao());
        holder.tongtien.setText(list.get(position).getTongtien()+" đ");
        holder.date.setText(list.get(position).getDate());
        holder.cardHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChiTietDonHangActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  ma,timegiao,tongtien,date;
        CardView cardHD;

        public MyViewHolder(View view) {
            super(view);

            ma = view.findViewById(R.id.MaHD);
            timegiao = view.findViewById(R.id.tggiao);
            tongtien = view.findViewById(R.id.tvTongtien);
            date = view.findViewById(R.id.dategiaohang);
            cardHD = view.findViewById(R.id.cardhoadonmoi);
        }
    }
}
