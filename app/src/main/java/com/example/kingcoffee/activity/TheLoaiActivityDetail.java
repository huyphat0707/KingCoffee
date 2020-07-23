package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;

public class TheLoaiActivityDetail extends AppCompatActivity {
    EditText tenTL,ViTri;
    String tentl, vitri;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_the_loai_detail);
        tenTL = findViewById(R.id.EdTheLoaiUP);
        ViTri= findViewById(R.id.EdViTriUP);
        theLoaiDAO = new TheLoaiDAO(this);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        tentl=bundle.getString("TENTHELOAI");
        vitri=bundle.getString("VITRI");

        tenTL.setText(tentl);
        ViTri.setText(vitri);

    }

    public void CapNhatTheLoai(View view) {
        if (theLoaiDAO.CapnhatTheLoai(
                tenTL.getText().toString(),
                ViTri.getText().toString())>0){
            Toast.makeText(getApplicationContext(),"cập nhật thành công ",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"cập nhật thất bại ",Toast.LENGTH_SHORT).show();
        }
    }
}
