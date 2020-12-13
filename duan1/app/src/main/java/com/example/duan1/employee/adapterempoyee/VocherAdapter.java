package com.example.duan1.employee.adapterempoyee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.employee.model.Voucher;

import java.util.ArrayList;

public class VocherAdapter extends RecyclerView.Adapter<VocherAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Voucher> list;

    public VocherAdapter(Context context, ArrayList<Voucher> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getTenKM());
        holder.tvmota.setText(list.get(position).getMota());
        holder.maKM.setText(list.get(position).getMakm());
        holder.batdau.setText(list.get(position).getNgaybatdau());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvName, tvmota,batdau,maKM;
        CardView cardDC;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.ivKM);

            tvName = view.findViewById(R.id.tvTenKM);
            tvmota = view.findViewById(R.id.tvMotaKM);
            batdau = view.findViewById(R.id.batdaugiamgia);
            maKM = view.findViewById(R.id.maKM);
            cardDC = view.findViewById(R.id.voucherdgchay);
        }
    }
}
