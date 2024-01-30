package com.dungcts.recyclerview.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dungcts.recyclerview.Database.SQLiteHanderl;
import com.dungcts.recyclerview.Model.SinhVien;
import com.dungcts.recyclerview.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    EditText edEmailupdate,edPasswordupdate;
    Spinner sproleupdate;
    Button bntupdate;
    SQLiteHanderl db;
    String role = "";
    String checkjox = "";
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new SQLiteHanderl(getApplicationContext());
        init();
        Getinfo();
        update();
    }
    private void init() {
        edEmailupdate = (EditText) findViewById(R.id.edEmail);
        edPasswordupdate= (EditText) findViewById(R.id.edPassword);
        sproleupdate = (Spinner) findViewById(R.id.sprole);
        bntupdate = (Button) findViewById(R.id.bntupdate);
    }
    private void Getinfo(){
        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("ttupdate");
        id = sinhVien.getId();
        edEmailupdate.setText(sinhVien.getEmail());
        edPasswordupdate.setText(sinhVien.getPassword());
        if(sinhVien.getRole().equalsIgnoreCase("Sinh viên")){
            sproleupdate.setSelection(1);
        }else if (sinhVien.getRole().equalsIgnoreCase("Giảng viên")) {
            sproleupdate.setSelection(2);
        }else if (sinhVien.getRole().equalsIgnoreCase("Khác")){
            sproleupdate.setSelection(3);
        }else if (sinhVien.getRole().equals("")){
            sproleupdate.setSelection(0);
        }

    }
    private void update(){
        sproleupdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        role = "";
                        break;
                    case 1:
                        role = "Sinh viên";
                        break;
                    case 2:
                        role = "Giảng viên";
                        break;
                    case 3:
                        role = "Khác";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bntupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email = edEmailupdate.getText().toString();
                 String password = edPasswordupdate.getText().toString();
                boolean kt = true;
                if (email.equals("")){
                    edEmailupdate.setError("Vui lòng nhập email");
                    edEmailupdate.requestFocus();
                    kt = false;
                }
                if (password.equals("")){
                    edPasswordupdate.setError("Vui lòng nhập password");
                    edPasswordupdate.requestFocus();
                    kt = false;
                }
                if (kt == true) {
                    db.updateDataList( id, email, password, role);
                    Toast.makeText(UpdateActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    edEmailupdate.setText("");
                    edPasswordupdate.setText("");
                    sproleupdate.setSelection(0);
                    Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
