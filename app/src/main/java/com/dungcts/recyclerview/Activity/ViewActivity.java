package com.dungcts.recyclerview.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import com.dungcts.recyclerview.Adapter.SinhVienAdapter;
import com.dungcts.recyclerview.Database.SQLiteHanderl;
import com.dungcts.recyclerview.Model.SinhVien;
import com.dungcts.recyclerview.R;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    SQLiteHanderl db;
    RecyclerView recyclerView;
    ArrayList<SinhVien> sinhViens;
    SinhVienAdapter sinhVienAdapter;
    TextView txttong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        db = new SQLiteHanderl(getApplicationContext());
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        sinhViens = new ArrayList<>();
        sinhViens = getInfo();
        sinhVienAdapter = new SinhVienAdapter(this, sinhViens);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sinhVienAdapter);

        txttong = findViewById(R.id.txttong);
        txttong.setText("Số lượng : " + sinhViens.size());
    }

    private ArrayList<SinhVien> getInfo() {
        Cursor cursor = db.getAllDataSV();
        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String role = cursor.getString(3);
            SinhVien sinhVien = new SinhVien(id, email, password, role);
            sinhViens.add(sinhVien);
        }
        return sinhViens;
    }
    public void showStudentInfoDialog(SinhVien sinhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin sinh viên");
        builder.setMessage("ID: " + sinhVien.getId() + "\n" +
                "Email: " + sinhVien.getEmail() + "\n" +
                "Password: " + sinhVien.getPassword() + "\n" +
                "Role: " + sinhVien.getRole());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Đóng dialog khi người dùng nhấn OK
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
    public void deletesv(String ma){
        db.delete(ma);
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        init();
    }
}
