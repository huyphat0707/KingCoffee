package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.NhanVienAdapter;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivityList extends AppCompatActivity {
    FloatingActionButton add;
    ListView lvnhanvien;
    NhanVienAdapter adapter = null;
    NhanVienDAO nhanVienDAO;
    public static List<NhanVien> dsNhanVien = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien_list);
        add = findViewById(R.id.floatingActionButton);
    lvnhanvien = findViewById(R.id.listnhanvien);
    nhanVienDAO = new NhanVienDAO(NhanVienActivityList.this);
    dsNhanVien = nhanVienDAO.getAllNhanVien();
    adapter = new NhanVienAdapter( this, dsNhanVien);
    lvnhanvien.setAdapter(adapter);
    lvnhanvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent =new Intent(NhanVienActivityList.this,NhanVienActivityDetail.class);
            Bundle bd = new Bundle();
            bd.putString("MANHANVIEN",dsNhanVien.get(position).getMaNhanVien());
            bd.putString("TENNHANVIEN",dsNhanVien.get(position).getTenNhanVien());
            bd.putString("SOGIO", String.valueOf(dsNhanVien.get(position).getTime()));
            bd.putString("SOTIEN",String.valueOf(dsNhanVien.get(position).getPrice()));
            intent.putExtras(bd);
            startActivity(intent);

        }
    });
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(NhanVienActivityList.this,NhanVienActivity.class);
            startActivity(i);
        }
    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNhanVien.clear();
        dsNhanVien = nhanVienDAO.getAllNhanVien();
        adapter.changeDataset(nhanVienDAO.getAllNhanVien());
    }
}
