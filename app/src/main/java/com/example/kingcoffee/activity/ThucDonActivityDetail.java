package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;

public class ThucDonActivityDetail extends AppCompatActivity {
    EditText tenmon,giaban,thue,soluong;
    String tenMon,giaBan,Thue;
    ThucDonDAO thucDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don_detail);

        tenmon = findViewById(R.id.TenMon);
        tenmon.setEnabled(true);
        giaban = findViewById(R.id.GiaBanmon);
        giaban.setEnabled(true);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        tenMon = bundle.getString("TENSP");
        giaBan = bundle.getString("GIABAN");

        tenmon.setText(tenMon);
        giaban.setText(giaBan);



    }
}
