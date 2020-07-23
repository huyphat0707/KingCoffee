package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;

public class UserActivitydetail extends AppCompatActivity {
    EditText ten, phone;
    NguoiDungDAO nguoiDungDAO;
    String edten,edphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activitydetail);
        ten = findViewById(R.id.edtendangnhapuser);
        phone = findViewById(R.id.edtSodienthoaiuser);
        nguoiDungDAO = new NguoiDungDAO(UserActivitydetail.this);
        Intent i = getIntent();
        Bundle bundle =i.getExtras();
        edten = bundle.getString("username");
        edphone = bundle.getString("phone");

        ten.setText(edten);
        phone.setText(edphone);


    }

    public void huy(View view) {
        finish();
    }

    public void Capnhat(View view) {
        if (nguoiDungDAO.updateNguoiDung(
                ten.getText().toString(),
                phone.getText().toString())> 0 ){
            Toast.makeText(getApplicationContext(),"Lưu thành công  ",Toast.LENGTH_SHORT).show();

            finish();
        }else {
            Toast.makeText(getApplicationContext(),"Lưu thất bại  ",Toast.LENGTH_SHORT).show();


        }
    }
}
