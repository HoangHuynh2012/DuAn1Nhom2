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
import com.example.duan1.customer.model.TinTuc;

import java.util.ArrayList;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.MyViewHolder>  {

    ArrayList<TinTuc> list;
    Context context;

    public TinTucAdapter( Context context,ArrayList<TinTuc> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtintuc, parent, false);
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

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
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
            card = itemView.findViewById(R.id.cardTinTuc);
            title = itemView.findViewById(R.id.tintuc_title);
            des = itemView.findViewById(R.id.tintuc_des);
            relativeLayout = itemView.findViewById(R.id.background_color);

        }

    }

}