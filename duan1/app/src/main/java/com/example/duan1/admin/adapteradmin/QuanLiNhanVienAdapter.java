package com.example.duan1.admin.adapteradmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.admin.modeladmin.NhanVien;
import com.example.duan1.customer.Login.DangNhapActivity;
import com.example.duan1.customer.XemGioHang;
import com.example.duan1.customer.adapter.CartAdapter;
import com.example.duan1.customer.model.Cart;
import com.example.duan1.customer.model.NguoiDungMode;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class QuanLiNhanVienAdapter extends FirebaseRecyclerAdapter<NhanVien, QuanLiNhanVienAdapter.CartViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;

    public QuanLiNhanVienAdapter(@NonNull FirebaseRecyclerOptions<NhanVien> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuanLiNhanVienAdapter.CartViewHolder holder, int position, @NonNull NhanVien model) {
        holder.username.setText(model.getUsername());
        holder.tennv.setText(model.getTennguoidung());
        holder.password.setText(model.getPassword());
        holder.gmail.setText(model.getGmail());
        holder.sdt.setText(model.getSdt());
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                        builder1.setMessage("Bạn có chắc muốn xóa Nhân Viên này không");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("NguoiDung")
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
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                final View view = inflater.inflate(R.layout.edit_nhanvien, null);
                builder.setView(view);

                final EditText ten = (EditText) view.findViewById(R.id.edit_tennv);
                final EditText user = (EditText) view.findViewById(R.id.edit_username);
                final EditText mk = (EditText) view.findViewById(R.id.edit_mk);
                final EditText gmail = (EditText) view.findViewById(R.id.edit_gmail);
                final EditText sdt = (EditText) view.findViewById(R.id.edit_sdt);
                final EditText role = (EditText) view.findViewById(R.id.edit_role);
                final Button btn_xacnhan = (Button) view.findViewById(R.id.btn_xacnhaned);

                ten.setText(model.getTennguoidung());
                user.setText(model.getUsername());
                mk.setText(model.getPassword());
                gmail.setText(model.getGmail());
                sdt.setText(model.getSdt());

                final Dialog dialog = builder.create();
                dialog.show();

                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("tennguoidung", ten.getText().toString());
                        map.put("username", user.getText().toString());
                        map.put("password", mk.getText().toString());
                        map.put("gmail", gmail.getText().toString());
                        map.put("sdt", sdt.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("NguoiDung")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialog.dismiss();
                                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public QuanLiNhanVienAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nhanvien, parent, false);
        return new CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView username, tennv, password, gmail, sdt;
        Button btn_xoa, btn_edit;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.usernamenv);
            tennv = itemView.findViewById(R.id.tennv);
            password = itemView.findViewById(R.id.passwordnv);
            gmail = itemView.findViewById(R.id.gmailnhanvien);
            sdt = itemView.findViewById(R.id.sdtnhanvien);
            btn_xoa = itemView.findViewById(R.id.btn_xoanv);
            btn_edit = itemView.findViewById(R.id.btn_editnv);
        }
    }
}
