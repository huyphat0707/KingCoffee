package com.example.kingcoffee.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.model.Oder;
import com.example.kingcoffee.model.ThucDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.os.Build.VERSION_CODES.O;

public class TableOder extends AppCompatActivity {
    EditText edsoluong,edNgaygoi,edgiatien;
    Spinner spntensp;
    ThucDonDAO thucDonDAO;
    String tenSp;
    Double giatien;
    OderDAO oderDAO;
    List<ThucDon> dsThucDon = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_oder);

        edsoluong = findViewById(R.id.edsoluongoder);
        edNgaygoi = findViewById(R.id.edngayoder);
        edgiatien = findViewById(R.id.edgiatien);
        spntensp = findViewById(R.id.spnTenspoder);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        position = bundle.getString("POSITION");
        getCurrentDate();
        getTenSanPham();
        spntensp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenSp = dsThucDon.get(spntensp.getSelectedItemPosition()).getTenSanPham();
                giatien = thucDonDAO.getGiaTienByMaSanPham(tenSp);
                edgiatien.setText(String.valueOf(giatien));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String date = sdf.format(calendar.getTime());
        edNgaygoi.setText(date);
    }


    public void getTenSanPham(){
        thucDonDAO = new ThucDonDAO(TableOder.this);
        dsThucDon = thucDonDAO.getallThucDon();
        ArrayAdapter<ThucDon> dataAdapter = new ArrayAdapter<ThucDon>(this,
                android.R.layout.simple_spinner_item, dsThucDon);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spntensp.setAdapter(dataAdapter);
    }

    public void addoder(View view) {
        oderDAO = new OderDAO(this);
        List<String> list = oderDAO.checkMonAnByTenMonAn(tenSp, position);
        if (list.size() > 0){
            int tongSlSpOder = Integer.parseInt(list.get(1)) + Integer.parseInt(edsoluong.getText().toString());
            Oder oder = new Oder();
            oder.setId(Integer.parseInt(list.get(0)));
            oder.setSoluong(tongSlSpOder);
            if (oderDAO.CapnhatOder(oder) > 0){
                Toast.makeText(this, "Đã thêm: "+tenSp+" vào bàn số: "+position, Toast.LENGTH_SHORT).show();
            }
        }else {
            try {
                Oder oder = new Oder(
                        tenSp,
                        giatien,
                        sdf.parse(edNgaygoi.getText().toString()),
                        Integer.parseInt(edsoluong.getText().toString()),
                        position);
                if (oderDAO.insertOder(oder) >0){
                    Toast.makeText(getApplicationContext(),"thêm thành công ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Thêm thất bại ",Toast.LENGTH_LONG).show();
                }
            }catch (Exception ex){
                Log.e("Error",ex.toString());
            }
        }
    }

    public void huy(View view) {
        finish();
    }
}
