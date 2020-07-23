package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.ThucDonAdapter;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.model.ThucDon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ThucDonActivityList extends AppCompatActivity {

    FloatingActionButton addMN;
    ListView lvMN;
    ThucDonAdapter adapter = null;
    ThucDonDAO thucDonDAO;
    public static List<ThucDon> dsThucDon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don_list);
        addMN = findViewById(R.id.FlMenu);
        lvMN = findViewById(R.id.listMenu);
        thucDonDAO = new ThucDonDAO(ThucDonActivityList.this);
        dsThucDon = thucDonDAO.getallThucDon();
        adapter = new ThucDonAdapter(dsThucDon, this);
        lvMN.setAdapter(adapter);
        lvMN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(ThucDonActivityList.this,ThucDonActivityDetail.class);
                Bundle bd = new Bundle();
                bd.putString("TENSP",dsThucDon.get(position).getTenSanPham());
                bd.putString("TENTL",dsThucDon.get(position).getTenTheLoai());
                bd.putString("GIABAN", String.valueOf(dsThucDon.get(position).getGiaSanPham()));
                intent.putExtras(bd);
                startActivity(intent);

            }
        });
        addMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThucDonActivityList.this, ThucDonActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsThucDon.clear();
        dsThucDon = thucDonDAO.getallThucDon();
        adapter.changeDataset(thucDonDAO.getallThucDon());

    }
}
