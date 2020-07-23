package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivityDetail extends AppCompatActivity {
    TextView tienluong;
    EditText manhanvien,tennhanvien,sogio,sotien;
    public List<NhanVien> dsNhanVien = new ArrayList<>();
    Button thanhtoan;
    NhanVienDAO nhanVienDAO;
    String manv,tennv,sogionv,sotiennv;
    double thanhTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien_detail);
        manhanvien = findViewById(R.id.manhanvien);
        tennhanvien= findViewById(R.id.tennhanvien);
        sogio = findViewById(R.id.sogio);
        sotien = findViewById(R.id.sotien);
        thanhtoan = findViewById(R.id.thanhtoan);
        tienluong = findViewById(R.id.tienluong);
        nhanVienDAO = new NhanVienDAO(this);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        manv=bundle.getString("MANHANVIEN");
        tennv=bundle.getString("TENNHANVIEN");
        sogionv=bundle.getString("SOGIO");
        sotiennv = bundle.getString("SOTIEN");

        manhanvien.setText(manv);
        tennhanvien.setText(tennv);
        sogio.setText(sogionv);
        sotien.setText(sotiennv);

    }

    public void ThanhtoanNhanVien(View view) {
        nhanVienDAO = new NhanVienDAO(NhanVienActivityDetail.this);
        thanhTien = 0;
        thanhTien = Double.parseDouble(sogionv) * Double.parseDouble(sotiennv);
        tienluong.setText("Tiền lương :" + thanhTien);
    }

    public void CapNhatNhanVien(View view) {
        if (nhanVienDAO.CapnhatNhanVien(manhanvien.getText().toString(),
                tennhanvien.getText().toString(),sogio.getText().
                        toString(),sotien.getText().toString())>0){
            Toast.makeText(getApplicationContext(),"Lưu thành công ",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"Lưu thất bại ",Toast.LENGTH_SHORT).show();
        }
    }
}
