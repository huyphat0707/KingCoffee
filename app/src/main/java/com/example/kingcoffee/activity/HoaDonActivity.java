package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.HoaDonAdapter;
import com.example.kingcoffee.adapter.KhoAdapter;
import com.example.kingcoffee.databaseDAO.HoaDonDAO;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.model.HoaDon;
import com.example.kingcoffee.model.Kho;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    ListView listHD;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    List<HoaDon> dshoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        listHD = findViewById(R.id.listhoadon);
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        dshoadon = hoaDonDAO.getHoaDon();
        hoaDonAdapter = new HoaDonAdapter(dshoadon, this);
        listHD.setAdapter( hoaDonAdapter);
        listHD.setOnItemClickListener(new AdapterView .OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });
    }
    public void showAlertDialog(final int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this);
        builder.setTitle("QUESTION ???");
        builder.setIcon(R.drawable.ic_tb_black_24dp);
        builder.setMessage("Bạn có chắc chắn xóa hóa đơn ??");
        builder.setCancelable(false);
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HoaDonActivity.this, "Yêu cầu được thực hiện", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hoaDonDAO = new HoaDonDAO(getApplicationContext());
                hoaDonDAO.deleteHoaDon(dshoadon.get(pos).getIdTable());
                dshoadon.remove(pos);
                hoaDonAdapter.notifyDataSetChanged();
                    Toast.makeText(HoaDonActivity.this, "Xóa hóa đơn thành công ", Toast.LENGTH_SHORT).show();



            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
