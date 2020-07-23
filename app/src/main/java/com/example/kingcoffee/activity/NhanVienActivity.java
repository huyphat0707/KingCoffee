package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.model.NhanVien;

public class NhanVienActivity extends AppCompatActivity {
EditText manhanvien,tennhanvien,sogio,sotien;
Button addnhanvien,huynhanvien;
NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        manhanvien = findViewById(R.id.manhanvienadd);
        tennhanvien= findViewById(R.id.tennhanvienadd);
        sogio = findViewById(R.id.sogionhanvienadd);
        sotien = findViewById(R.id.sotiennhanvienadd);
        addnhanvien = findViewById(R.id.addnhanvienadd);
        huynhanvien = findViewById(R.id.huynhanvienhadd);



    }

    public void addnhanvien(View view) {
        nhanVienDAO = new NhanVienDAO(NhanVienActivity.this);
        try {
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else {
            NhanVien nv = new NhanVien(
                manhanvien.getText().toString(),
                tennhanvien.getText().toString(),
                Integer.parseInt(sogio.getText().toString()),
                Double.parseDouble(sotien.getText().toString()));

            if (nhanVienDAO.insertNhanVien(nv)>0){
                Toast.makeText(getApplicationContext(),"Thêm nhân viên thành công ",Toast.LENGTH_LONG).show();
                Intent i = new Intent(NhanVienActivity.this,NhanVienActivityList.class);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(),"Thêm thất bại ",Toast.LENGTH_LONG).show();
            }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }

    public void huynhanvien(View view) {
        manhanvien.setText("");
        tennhanvien.setText("");
                sogio.setText("");
                sotien.setText("");
                finish();

    }
    public int validation(){
        if (manhanvien.getText().toString().isEmpty()||tennhanvien.getText().toString().isEmpty()
        ||sogio.getText().toString().isEmpty()||sotien.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
