package com.dungcts.recyclerview.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dungcts.recyclerview.Activity.UpdateActivity;
import com.dungcts.recyclerview.Activity.ViewActivity;
import com.dungcts.recyclerview.Model.SinhVien;
import com.dungcts.recyclerview.R;
import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {

    private ViewActivity context;
    private List<SinhVien> sinhViens;

    public SinhVienAdapter(ViewActivity context, List<SinhVien> sinhViens) {
        this.context = context;
        this.sinhViens = sinhViens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final SinhVien sinhVien = sinhViens.get(position);

        holder.txtemail.setText(sinhVien.getEmail());
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa :" + sinhVien.getEmail() + " ra khỏi danh sách không");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.deletesv(sinhVien.getId());
                    }
                });
                builder.setNeutralButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Không làm gì nếu người dùng chọn "Không"
                    }
                });
                builder.show();
            }
        });

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("ttupdate", sinhViens.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sinhViens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtemail;
        ImageView imgedit, imgdelete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtemail = itemView.findViewById(R.id.txtEmail);
            imgedit = itemView.findViewById(R.id.imgedit);
            imgdelete = itemView.findViewById(R.id.imgdelete);

            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Gọi hàm hiển thị dialog thông tin sinh viên
                        context.showStudentInfoDialog(sinhViens.get(position));
                    }
                }
            });
        }
    }

}
