package com.example.duan1.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.customer.WebView;
import com.example.duan1.customer.model.DanhChoBan;

import java.util.ArrayList;

public class DanhChoBanAdapter extends RecyclerView.Adapter<DanhChoBanAdapter.MyViewHolder>  {

    ArrayList<DanhChoBan> list;
    Context context;

    public DanhChoBanAdapter(Context context, ArrayList<DanhChoBan> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhchoban, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.image.setImageResource(list.get(position).getImage());
        holder.des.setText(list.get(position).getDescrip());
        holder.title.setText(list.get(position).getTitle());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, WebView.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title,des;
        CardView card;
        RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks
            image = itemView.findViewById(R.id.imageBanner);
            card = itemView.findViewById(R.id.cardDanhChoBan);
            title = itemView.findViewById(R.id.tintuc_title);
            des = itemView.findViewById(R.id.tintuc_des);
            relativeLayout = itemView.findViewById(R.id.background_color);

        }

    }

}