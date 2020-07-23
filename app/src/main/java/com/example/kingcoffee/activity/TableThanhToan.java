package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.ThanhToanAdapter;
import com.example.kingcoffee.databaseDAO.HoaDonDAO;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.model.HoaDon;
import com.example.kingcoffee.model.Oder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TableThanhToan extends AppCompatActivity  {

    long sumPay = 0;
    ListView lvTL;
    ThanhToanAdapter adapter = null;
    OderDAO oderDAO;
    TextView tien;
    String positionn;
    List<Oder> dsTT;
    Button btntt;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_thanh_toan);
        lvTL = findViewById(R.id.listthanhtoan);
        tien = findViewById(R.id.tv_tongtien);


        Intent itent = getIntent();
        Bundle bundle = itent.getExtras();
        positionn = bundle.getString("POSITION");

        oderDAO = new OderDAO(this);
        dsTT = oderDAO.getOrderByIdTable(positionn);

        adapter = new ThanhToanAdapter(dsTT ,this);
        lvTL.setAdapter(adapter);
        lvTL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               showAlertDialog(position);
            }
        });

        for(int i = 0; i < dsTT.size();i++){
            double price = dsTT.get(i).getGiatien();
            double soluong = dsTT.get(i).getSoluong();
            sumPay += (soluong * price);
        }
        tien.setText("Tổng tiền: \t\t"+sumPay);
        btntt = findViewById(R.id.btnThanhToan);
        btntt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TableThanhToan.this);
                builder.setTitle("QUESTION ???");
                builder.setIcon(R.drawable.ic_tb_black_24dp);
                builder.setMessage("Bạn chắc chắn thanh toán ?");
                builder.setCancelable(false);
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TableThanhToan.this, "Yêu cầu được thực hiện", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        OderDAO oderDAO = new OderDAO(TableThanhToan.this);
                        if (oderDAO.deleteOderByIdTable(positionn)> 0 ){
                             hoaDonDAO = new HoaDonDAO(TableThanhToan.this);
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss | yyyy-MM-dd");
                            String date = sd.format(calendar.getTime());

                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setIdTable(positionn);
                            try {
                                hoaDon.setGio(sd.parse(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            hoaDon.setSumMonney(sumPay);
                            if (hoaDonDAO.insertHoaDon(hoaDon)>0){
                                Toast.makeText(TableThanhToan.this, "Bạn đã thanh toán thành công", Toast.LENGTH_SHORT).show();
                            }
                            onBackPressed();
                        }
                        else {
                            Toast.makeText(TableThanhToan.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void showAlertDialog(final int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(TableThanhToan.this);
        builder.setTitle("QUESTION ???");
        builder.setIcon(R.drawable.ic_tb_black_24dp);
        builder.setMessage("Are you sure want to detele product ?");
        builder.setCancelable(false);
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TableThanhToan.this, "Yêu cầu được thực hiện", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            OderDAO oderDAO = new OderDAO(getApplicationContext());
            if (oderDAO.deleteOder(dsTT.get(pos).getTenSp())>0){
                Toast.makeText(TableThanhToan.this, "Xóa đồ uống thành công ", Toast.LENGTH_SHORT).show();
                adapter.changeDataset(oderDAO.getOrderByIdTable(positionn));
                onBackPressed();
            }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void thoat(View view) {
        finish();
    }
}
