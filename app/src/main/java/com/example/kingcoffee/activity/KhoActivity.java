package com.example.kingcoffee.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.KhoDAO;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.model.Kho;
import com.example.kingcoffee.model.TheLoai;
import com.example.kingcoffee.model.ThucDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class KhoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    EditText soluong, ngaykho;
    Spinner spinsp;
    Button add, huy;
    String tensanpham;
    ThucDonDAO thucDonDAO;
    KhoDAO khoDAO;
    List<ThucDon> listThucdon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("KHO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_kho);
        soluong = findViewById(R.id.edSoLuongKho);
        ngaykho = findViewById(R.id.edDateKho);
        spinsp = findViewById(R.id.spinnerSanPhamkho);
        add = findViewById(R.id.BtnAddKho);
        huy = findViewById(R.id.BtnHuyKho);
        getSanPham();

        spinsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tensanpham = listThucdon.get(spinsp.getSelectedItemPosition()).getTenSanPham(); }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void getSanPham(){
        thucDonDAO = new ThucDonDAO(KhoActivity.this);
        listThucdon = thucDonDAO.getallThucDon();
        ArrayAdapter<ThucDon> dataAdapter = new ArrayAdapter<ThucDon>(
                this,android.R.layout.simple_spinner_item, listThucdon);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinsp.setAdapter(dataAdapter);
    }


    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) { ngaykho.setText(sdf.format(calendar.getTime()));
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }
    public void HuyKho(View view) {
        finish();
    }

    public void addKho(View view) {
        khoDAO = new KhoDAO(KhoActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Kho kho = new Kho(tensanpham,sdf.parse(ngaykho.getText().toString()),
                        Integer.parseInt( soluong.getText().toString()));
                if (khoDAO.insertKho(kho) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
        finish();
    }

    private int validation() {if (soluong.getText().toString().isEmpty()||ngaykho.getText().toString().isEmpty()){
        return -1;
    }
        return 0;
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
