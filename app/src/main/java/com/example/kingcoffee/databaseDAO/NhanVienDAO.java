package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends DatabaseHelper {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TAG = "NhanVien";
    public static final String SQL_NHAN_VIEN = "CREATE TABLE NhanVien(manhanvien text primary key, tennhanvien text, time interger, price double);" ;
    public static final String TABLE_NAME = "NhanVien";

    public NhanVienDAO(Context context){
        super(context);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertNhanVien(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        values.put("manhanvien", nhanVien.getMaNhanVien());
        values.put("tennhanvien", nhanVien.getTenNhanVien());
        values.put("time", nhanVien.getTime());
        values.put("price", nhanVien.getPrice());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } return 1;

    }
    public int deleteNhanVienByID(String maNhanVien){
        int result = db.delete(TABLE_NAME,"manhanvien=?",new String[]{maNhanVien});
        if (result == 0){
            return -1;
        }else {
            return 1;
        }

    }
    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> dsNhanVien = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien(cursor.getString(0));
            nv.setTenNhanVien(cursor.getString(1));
            nv.setTime(cursor.getInt(2));
            nv.setPrice(cursor.getDouble(3));
            dsNhanVien.add(nv);
            Log.d("error",nv.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsNhanVien;

    }
    public int CapnhatNhanVien(String maNhanVien, String tenNhanVien,String time,String price){
        ContentValues values = new ContentValues();
        values.put("manhanvien",maNhanVien);
        values.put("tennhanvien", tenNhanVien);
        values.put("time",time);
        values.put("price",price);
        int result = db.update(TABLE_NAME,values,"manhanvien=?",new String[]{maNhanVien});
        if (result == 0){
            return -1;
        }else {
            return 1;
        }
    }
}
