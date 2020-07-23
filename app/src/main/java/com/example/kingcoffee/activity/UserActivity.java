package com.example.kingcoffee.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.kingcoffee.DatePickerFragment;
import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;
import com.example.kingcoffee.model.NguoiDung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText tendangnhap,pass,repass,phone,ngaysinh,fullname;
    Button them,huy,date;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    NguoiDungDAO nguoiDungDAO;
    RadioButton rdioNam,rdioNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tendangnhap = findViewById(R.id.edtendangnhapuser);
        pass = findViewById(R.id.edtmatkhauuser);
        repass = findViewById(R.id.edtNhacLaimatkhauuser);
        fullname = findViewById(R.id.hovatenuser);
        phone = findViewById(R.id.edtSodienthoaiuser);
        ngaysinh= findViewById(R.id.edtNgaySinhuser);
        them = findViewById(R.id.btnaddnguoidung);
        huy = findViewById(R.id.btncancelnguoidung);
        date= findViewById(R.id.datepicker);
        rdioNam= findViewById(R.id.radioButtonnam);
        rdioNu=findViewById(R.id.radioButtonnu);

    }

    public void chon(View view) {
//        PickDateDialog();
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(),"date");

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(Calendar calendar) {
        ngaysinh.setText(sdf.format(calendar.getTime()));
    }

    public void add(View view) {
        nguoiDungDAO = new NguoiDungDAO(UserActivity.this);
        try {
                NguoiDung user = new NguoiDung(
                        tendangnhap.getText().toString(),
                        pass.getText().toString(),
                        fullname.getText().toString(),
                        phone.getText().toString(),
                        sdf.parse(ngaysinh.getText().toString()));
                if (rdioNam.isChecked()){
                    rdioNam.getText();
                }if (rdioNu.isChecked()){
                    rdioNu.isChecked();
            }
                if (validateForm()>0) {
                    if (nguoiDungDAO.insertNguoiDung(user) > 0) {
                        Toast.makeText(getApplicationContext(), "Thêm người dùng thành công ", Toast.LENGTH_LONG).show();
                      finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                    }
                }
        } catch (ParseException e) {
            Log.e("Error",e.toString());
        }

    }

    public void huy(View view) {
        finish();
    }

    public static class DatePickerFragment extends DialogFragment {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(),year,month,day);
        }
    }
    public int validateForm(){
        int check = 1;
        if ( tendangnhap.getText().length()==0||fullname.getText().length()==0
                ||phone.getText().length()==0||pass.getText().length()==0
                ||repass.getText().length()==0||validation()<0){
            Toast.makeText(getApplicationContext(),"bạn phải điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
            check = -1;
        }
        else {
            String passw = pass.getText().toString();
            String repas = repass.getText().toString();
            if (!passw.equals(repas)){
                Toast.makeText(getApplicationContext(),"mật khẩu phải trùng khớp",Toast.LENGTH_LONG).show();
                check = -1;
            }

        }
        return check;
    }
    public int validation(){
        if (ngaysinh.getText().toString().isEmpty() ){
            return -1;
        }
        return 1;
    }
}
