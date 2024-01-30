package com.dungcts.recyclerview.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dungcts.recyclerview.Database.SQLiteHanderl;
import com.dungcts.recyclerview.R;


public class AddActivity extends AppCompatActivity {
    EditText edEmail,edPassword;
    Spinner sprole;
    Button bntadd;
    SQLiteHanderl db;
    String role = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = new SQLiteHanderl(getApplicationContext());
        init();
        them();
    }

    private void init() {
        edEmail= (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);
        sprole = (Spinner) findViewById(R.id.sprole);
        bntadd = (Button) findViewById(R.id.bntadd);
    }
    private void them(){
        sprole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        role = " ";
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
        bntadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                boolean kt = true;
                if (email.equals("")){
                    edEmail.setError("Vui lòng nhập Email");
                    edEmail.requestFocus();
                    kt = false;
                }
                if (password.equals("")){
                    edPassword.setError("Vui lòng nhập PassWord");
                    edPassword.requestFocus();
                    kt = false;
                }
                if (kt == true){
                    db.themsv(email,password,role);
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edEmail.setText("");
                    edPassword.setText("");
                    sprole.setSelection(0);
                }
            }
        });
    }
}
