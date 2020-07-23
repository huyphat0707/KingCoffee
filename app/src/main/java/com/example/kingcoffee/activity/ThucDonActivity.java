package com.example.kingcoffee.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.model.ThucDon;
import com.example.kingcoffee.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ThucDonActivity extends AppCompatActivity {
    EditText TenSP,GiaSP;
    Button AddMenu,HuyMenu;
    Spinner spnMN;
    ThucDonDAO thucDonDAO;
    TheLoaiDAO theLoaiDAO;
    String tenTheLoai;
    List<TheLoai> listTheLoai = new ArrayList<>();
    ImageView imgtheloai;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don);
        TenSP = findViewById(R.id.TenMon);
        imgtheloai = findViewById(R.id.imgTheloai);
        GiaSP = findViewById(R.id.GiaBan);
        AddMenu = findViewById(R.id.BtnAddMenu);
        HuyMenu = findViewById(R.id.BtnHuyMenu);
        spnMN = findViewById(R.id.spinnerMenu);
        getTheLoai();

        spnMN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenTheLoai = listTheLoai.get(spnMN.getSelectedItemPosition()).getTenTheLoai(); }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imgtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThucDonActivity.this,TheLoaiActivity.class);
                startActivity(i);
            }
        });
    }
    public void getTheLoai(){
        theLoaiDAO = new TheLoaiDAO(ThucDonActivity.this);
        listTheLoai = theLoaiDAO.getAllTheloai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMN.setAdapter(dataAdapter);
    }

    public void addmenu(View view) {
      thucDonDAO = new ThucDonDAO(ThucDonActivity.this);
        if (validation()<0){
            Toast.makeText(getApplicationContext(), "bạn phải điền đầy đủ thông tin     ", Toast.LENGTH_LONG).show();
        }else{
      ThucDon thucDon = new ThucDon(
       tenTheLoai,TenSP.getText().toString(),
              Double.parseDouble(GiaSP.getText().toString()));

        try {
            if (thucDonDAO.insertMenu(thucDon) > 0){
                Toast.makeText(getApplicationContext(), "thêm thực đơn thành công", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), "thêm thất bại", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Log.e("Error", ex.toString());
        }
        }


    }

    public void HuyMenu(View view) {
        finish();

    }
    public int checkPositionTheLoai(String strTheLoai){
        for (int i = 0; i <listTheLoai.size(); i++){
            if (strTheLoai.equals(listTheLoai.get(i).getTenTheLoai())){
                return i;
            }
        }
        return 0;
    }
    public int validation(){
        if (TenSP.getText().toString().isEmpty()
                ||GiaSP.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
