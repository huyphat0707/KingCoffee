package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.ThucDon;

import java.util.ArrayList;
import java.util.List;

public class ThucDonDAO extends DatabaseHelper {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "MENU";
    public static final String SQL_MENU = "CREATE TABLE MENU(tensanpham text primary key, tentheloai text, giasanpham double);";
    public static final String TAG = "MENU";

    public ThucDonDAO(Context context){
        super(context);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertMenu(ThucDon thucDon) {
        ContentValues values = new ContentValues();
        values.put("tensanpham", thucDon.getTenSanPham());
        values.put("tentheloai", thucDon.getTenTheLoai());
        values.put("giasanpham", thucDon.getGiaSanPham());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;

    }
    public List<ThucDon> getallThucDon(){
        List<ThucDon> dsThucDon = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            ThucDon td = new ThucDon();
            td.setTenSanPham(cursor.getString(0));
            td.setTenTheLoai(cursor.getString(1));
            td.setGiaSanPham(cursor.getDouble(2));
            dsThucDon.add(td);
            Log.d("error", td.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsThucDon;
        }
    public int deletethucdon(String tensanpham ) {
        int result = db.delete(TABLE_NAME, "tensanpham=?", new String[]{tensanpham});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public Double getGiaTienByMaSanPham(String tenSp){
        String sql = "Select giasanpham from MENU where tensanpham like " +  "'"+tenSp+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Double giatien = cursor.getDouble(0);
        return  giatien;
    }

}
