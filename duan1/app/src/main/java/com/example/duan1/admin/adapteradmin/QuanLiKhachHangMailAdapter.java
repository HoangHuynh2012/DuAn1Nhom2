package com.example.duan1.admin.adapteradmin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.admin.modeladmin.KhachHang;
import com.example.duan1.admin.modeladmin.KhachHangMail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class QuanLiKhachHangMailAdapter extends FirebaseRecyclerAdapter<KhachHangMail, QuanLiKhachHangMailAdapter.CartViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public QuanLiKhachHangMailAdapter(@NonNull FirebaseRecyclerOptions<KhachHangMail> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuanLiKhachHangMailAdapter.CartViewHolder holder, int position, @NonNull KhachHangMail model) {
        holder.id.setText(model.getUserId());
        holder.emailkh.setText(model.getEmail());
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa Khách Hàng này không");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseDatabase.getInstance().getReference()
                                        .child("NguoiDungMail")
                                        .child(getRef(position).getKey())
                                        .setValue(null)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(context, "Xóa Không Thành Công", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });
                androidx.appcompat.app.AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    @NonNull
    @Override
    public QuanLiKhachHangMailAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_khachhangmail, parent, false);
        return new CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView id,emailkh;
        Button btn_xoa;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.useridmail);
            emailkh = itemView.findViewById(R.id.tenkhmail);
            btn_xoa = itemView.findViewById(R.id.btn_xoakhmail);

        }
    }
}
