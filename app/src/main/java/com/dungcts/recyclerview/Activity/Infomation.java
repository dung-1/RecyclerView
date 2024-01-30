package com.dungcts.recyclerview.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dungcts.recyclerview.Model.SinhVien;
import com.dungcts.recyclerview.R;

public class Infomation extends AppCompatActivity {
    TextView viewemail,viewpasword,viewrole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        init();
        Getinfomation();
    }

    private void Getinfomation() {
        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("thongtin");
        viewemail.setText("Email : "+sinhVien.getEmail());
        viewpasword.setText("PassWord : "+sinhVien.getPassword());
        viewrole.setText("Role : "+sinhVien.getRole());
    }

    private void init() {
        viewemail = (TextView) findViewById(R.id.viewemail);
        viewpasword = (TextView) findViewById(R.id.viewpassword);
        viewrole = (TextView) findViewById(R.id.viewrole);
    }
}
