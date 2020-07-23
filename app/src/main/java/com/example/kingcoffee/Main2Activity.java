package com.example.kingcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kingcoffee.activity.GioiThieuActivity;
import com.example.kingcoffee.activity.HoaDonActivity;
import com.example.kingcoffee.activity.KhoActivityList;
import com.example.kingcoffee.activity.LoginActivity;
import com.example.kingcoffee.activity.TableActivityList;
import com.example.kingcoffee.activity.NhanVienActivityList;
import com.example.kingcoffee.activity.ShareActivity;
import com.example.kingcoffee.activity.TheLoaiActivityList;
import com.example.kingcoffee.activity.ThongKeActivity;
import com.example.kingcoffee.activity.ThucDonActivityList;
import com.example.kingcoffee.activity.UserActivity;
import com.example.kingcoffee.activity.UserActivityList;
import com.example.kingcoffee.map.MapsActivity;

public class Main2Activity extends AppCompatActivity {
Button menu ,goimon,map,gioithieu,nhanvien,thongke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        menu = findViewById(R.id.btnmenumain);
        goimon = findViewById(R.id.btngoimonmain);
        map = findViewById(R.id.btnmapmain);
        gioithieu = findViewById(R.id.btngioithieumain);
        nhanvien = findViewById(R.id.btnnhanvienmain);
        thongke = findViewById(R.id.btnthongkemain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_user:
                Intent intent = new Intent(Main2Activity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list_user:
                Intent i = new Intent(Main2Activity.this, UserActivityList.class);
                startActivity(i);
                break;
            case R.id.menu_dang_xuat:
                showAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("QUESTION ???");
        builder.setIcon(R.drawable.ic_tb_black_24dp);
        builder.setMessage("Are you sure want to log out ?");
        builder.setCancelable(false);
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Main2Activity.this, "Yêu cầu được thực hiện", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.clear();
                edit.commit();
                Intent intent2 = new Intent(Main2Activity.this, LoginActivity.class);
                startActivity(intent2);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void NhanVien(View view) {
        Intent i = new Intent(Main2Activity.this, NhanVienActivityList.class);
        startActivity(i);
    }

    public void menu(View view) {
        Intent i = new Intent(Main2Activity.this, ThucDonActivityList.class);
        startActivity(i);
    }

    public void theloai(View view) {
        Intent i = new Intent(Main2Activity.this, TheLoaiActivityList.class);
        startActivity(i);
    }

    public void table(View view) {
        Intent i = new Intent(Main2Activity.this, TableActivityList.class);
        startActivity(i);
    }

    public void Kho(View view) {
        Intent i = new Intent(Main2Activity.this, KhoActivityList.class);
        startActivity(i);
    }

    public void map(View view) {
        Intent i = new Intent(Main2Activity.this, MapsActivity.class);
        startActivity(i);
    }

    public void hoadon(View view) {
        Intent i = new Intent(Main2Activity.this, HoaDonActivity.class);
        startActivity(i);
    }

    public void gioithieu(View view) {
        Intent i = new Intent(Main2Activity.this, GioiThieuActivity.class);
        startActivity(i);
    }

    public void ThongKe(View view) {
        Intent i = new Intent(Main2Activity.this, ThongKeActivity.class);
        startActivity(i);
    }
}
