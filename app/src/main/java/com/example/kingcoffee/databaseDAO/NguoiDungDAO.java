package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.NguoiDung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO extends DatabaseHelper{
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;


    public static final String TABLE_NAME = "NGUOIDUNG";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NGUOIDUNG (username text primary key, password text, hoten text, phone text, ngaysinh date);";
    public static final String TAG = "NguoiDung";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public NguoiDungDAO(Context context){
        super(context);
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public int insertNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassWord());
        values.put("hoten",nd.getFullname());
        values.put("phone", nd.getPhone());
        values.put("ngaysinh",sdf.format(nd.getNgaysinh()));
        try {
            if (database.insert(TABLE_NAME,null,values)== -1){
            return -1;}
        }catch (Exception ex){
        Log.e(TAG,ex.toString());
        } return 1;
    }


    public List<NguoiDung> getAllNguoiDung() throws ParseException {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            NguoiDung ee = new NguoiDung();
            ee.setUserName(cursor.getString(0));
            ee.setPassWord(cursor.getString(1));
            ee.setFullname(cursor.getString(2));
            ee.setPhone(cursor.getString(3));
            ee.setNgaysinh(sdf.parse(cursor.getString(4)));
            dsNguoiDung.add(ee);
            Log.d("//====",ee.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsNguoiDung;
    }

    public int updateNguoiDung(String username, String phone){
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("phone",phone);
        int result = database.update(TABLE_NAME,values, "username=?", new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //DELETE
    public int deleteNguoiDungByID(String username){
        int result = database.delete(TABLE_NAME, "username=?", new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public Boolean Luu(String user,String pass){
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from NguoiDung where username=? AND password=?",new String[]{user,pass});
        if(cursor.getCount() >0){
            return true;
        }else {
            return false;
        }
    }

}
