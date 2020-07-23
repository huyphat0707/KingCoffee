package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.KhoAdapter;
import com.example.kingcoffee.databaseDAO.KhoDAO;
import com.example.kingcoffee.model.Kho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class KhoActivityList extends AppCompatActivity {

    FloatingActionButton addKho;
    ListView lvKho;
    KhoAdapter adapter = null;
    KhoDAO khoDAO;
    public static List<Kho> dsKho = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Danh Sách Kho");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_kho_list);
        addKho = findViewById(R.id.flkho);
        lvKho = findViewById(R.id.listkho);
        khoDAO = new KhoDAO(KhoActivityList.this);
        try {
            dsKho = khoDAO.getAllKho();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new KhoAdapter(dsKho, this);
        lvKho.setAdapter( adapter);
        lvKho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bd = new Bundle();
                bd.putString("TENSP",dsKho.get(position).getSanpham());
                bd.putString("SOLUONG", String.valueOf(dsKho.get(position).getSoluong()));

            }
        });
        addKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KhoActivityList.this, KhoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsKho.clear();
        try {
            dsKho = khoDAO.getAllKho();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            adapter.changeDataset(khoDAO.getAllKho());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
