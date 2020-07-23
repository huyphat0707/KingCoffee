package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.HoaDonDAO;

public class ThongKeActivity extends AppCompatActivity {
TextView txthomnay,txtthang,txtnam;
Button homnay,thang ,nam;
HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        txthomnay = findViewById(R.id.txthomnay);
        txtthang = findViewById(R.id.txtThang);
        txtnam = findViewById(R.id.txtNam);
        homnay = findViewById(R.id.homnay);
        thang = findViewById(R.id.thang);
        nam = findViewById(R.id.nam);
        homnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(ThongKeActivity.this);
                txthomnay.setText("Doanh thu hôm nay:" + hoaDonDAO.getDoanhThuTheoNgay());
            }
        });
        thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(ThongKeActivity.this);
                txtthang.setText("Doanh thu tháng này:" + hoaDonDAO.getDoanhThuTheoThang());
            }
        });
        nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(ThongKeActivity.this);
                txtnam.setText("Doanh thu năm này:" +hoaDonDAO.getDoanhThuTheoNam());
            }
        });
    }
}
