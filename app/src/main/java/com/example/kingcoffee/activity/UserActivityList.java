package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.NguoiDungAdapter;
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;
import com.example.kingcoffee.model.NguoiDung;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserActivityList extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvUser;
    NguoiDungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        lvUser = findViewById(R.id.listuser);
        nguoiDungDAO = new NguoiDungDAO(UserActivityList.this);
        try {
            dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        } catch (ParseException e) {
            Log.d("Error: ", e.toString());

        }
        adapter = new NguoiDungAdapter(dsNguoiDung, this);
        lvUser.setAdapter(adapter);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(UserActivityList.this, UserActivitydetail.class);
                Bundle b = new Bundle();
                b.putString("username" , dsNguoiDung.get(position).getUserName() );
                b.putString("phone" , dsNguoiDung.get(position).getPhone() );
                i.putExtras(b);
                startActivity(i);


            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        try {
            dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        } catch (ParseException e) {
            Log.d("Error: ", e.toString());
        }
        try {
            adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
        } catch (ParseException e) {
            Log.d("Error: ", e.toString());
        }
    }
}
