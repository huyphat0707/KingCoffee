package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.TheLoaiAdapter;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.model.TheLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivityList extends AppCompatActivity {

    FloatingActionButton addTL;
    ListView lvTL;
    TheLoaiAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;
    public static List<TheLoai> dsTL = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_list);
        addTL = findViewById(R.id.FlTheloai);
        lvTL = findViewById(R.id.listtheloai);
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivityList.this);
        dsTL = theLoaiDAO.getAllTheloai();
        adapter = new TheLoaiAdapter(dsTL, this);

        lvTL.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvTL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(TheLoaiActivityList.this,TheLoaiActivityDetail.class);
                Bundle bd = new Bundle();
                bd.putString("TENTHELOAI",dsTL.get(position).getTenTheLoai());
                bd.putString("VITRI",String.valueOf(dsTL.get(position).getViTri()));
                intent.putExtras(bd);
                startActivity(intent);

            }
        });
        addTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TheLoaiActivityList.this, TheLoaiActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTL.clear();
        dsTL = theLoaiDAO.getAllTheloai();
        adapter.changeDataset(theLoaiDAO.getAllTheloai());

    }
}
