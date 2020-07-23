package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.NhanVienListAdapter;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivityListDetail extends AppCompatActivity {
    public List<NhanVien> dsnhanvien = new ArrayList<>();
    NhanVienDAO nhanVienDAO;
    ListView lsnhanvien;
    NhanVienListAdapter adapter = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien_list_detail);
        lsnhanvien = findViewById(R.id.listnhanviendetail);
        nhanVienDAO = new NhanVienDAO(NhanVienActivityListDetail.this);
       dsnhanvien = nhanVienDAO.getAllNhanVien();
       adapter = new NhanVienListAdapter(this, dsnhanvien);
       lsnhanvien.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        dsnhanvien.clear();
        dsnhanvien = nhanVienDAO.getAllNhanVien();
        adapter.changeDataset(nhanVienDAO.getAllNhanVien());
    }
}
