package com.example.duan1.customer.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.customer.Login.Loading;
import com.example.duan1.customer.XemGioHang;
import com.example.duan1.customer.model.Cart;
import com.example.duan1.customer.model.NguoiDungMode;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.duan1.customer.XemGioHang.rcv;

public class CartAdapter extends FirebaseRecyclerAdapter<Cart, CartAdapter.CartViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;

    public CartAdapter(@NonNull FirebaseRecyclerOptions options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
        holder.tensp.setText(String.valueOf(model.getTensp()));
        holder.giasp.setText(String.valueOf(model.getGia()));
        holder.soluongsp.setText(String.valueOf(model.getSoluongsp()));
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Cart")
                        .child(getRef(position).getKey())
                        .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Xóa Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                                            ((Activity) context).recreate();
                                        }
                                    },1000);
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
                .inflate(R.layout.item_xemgiohang, parent, false);
        return new CartViewHolder(view);

    }
    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tensp, giasp;
        TextView soluongsp;
        ImageView btn_xoa;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            giasp = (TextView) itemView.findViewById(R.id.tvXemGiaTien);
            tensp = (TextView) itemView.findViewById(R.id.tvXemTenSP);
            soluongsp = (TextView) itemView.findViewById(R.id.tvThemSoLuong);
            btn_xoa = (ImageView) itemView.findViewById(R.id.btn_xoasp);
        }
    }

}
