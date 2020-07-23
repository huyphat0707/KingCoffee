package com.example.kingcoffee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    EditText TenTL, ViTri;
    Button AddTL,HuyTL;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        TenTL = findViewById(R.id.EdTheLoai);
        ViTri = findViewById(R.id.EdViTri);
        AddTL = findViewById(R.id.AddTL);
        HuyTL = findViewById(R.id.HuyTL);




    }

    public void AddTL(View view) {
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        try {
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else {
                TheLoai tl = new TheLoai(
                        TenTL.getText().toString(),
                        Integer.parseInt(ViTri.getText().toString()));

                if (theLoaiDAO.insertTheLoai(tl)>0){
                    Toast.makeText(getApplicationContext(),"Thêm thể loại thành công ",Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Thêm thất bại ",Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }

    public void HuyTL(View view) {
        finish();

    }
    public int validation(){
        if (TenTL.getText().toString().isEmpty()||ViTri.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}