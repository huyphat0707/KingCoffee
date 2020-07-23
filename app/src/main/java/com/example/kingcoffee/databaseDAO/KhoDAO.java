package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.Kho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhoDAO extends DatabaseHelper{
    private SQLiteDatabase db;
    private DatabaseHelper dbHeper;

    public static final String TABLE_NAME = "KHO";
    public static final String SQL_KHO = "CREATE TABLE KHO(sanpham text primary key, ngay date, soluong integer);";
    public static final String TAG = "khoDAO";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    public KhoDAO (Context context){
        super(context);
        dbHeper = new DatabaseHelper(context);
        db = dbHeper.getWritableDatabase();
    }
    public int insertKho(Kho kho) {
        ContentValues values = new ContentValues();
        values.put("sanpham", kho.getSanpham());
        values.put("ngay", sdf.format(kho.getNgay()));
        values.put("soluong", kho.getSoluong());
        try {
            if (db.insert(TABLE_NAME, null,values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }
    public List<Kho> getAllKho() throws ParseException {
        List<Kho> dsKho = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Kho k = new Kho();

            k.setSanpham(cursor.getString(0));
            k.setNgay(sdf.parse(cursor.getString(1)));
            k.setSoluong(cursor.getInt(2));
            dsKho.add(k);
            Log.d("error", k.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsKho;
    }
    public int updateKho(String tenSanPham, Date a, int b ){
        ContentValues values = new ContentValues();
        values.put("ngay",a.toString());
        values.put("soluong",b);
        int result = db.update(TABLE_NAME,values,"maSach=?", new String[]{tenSanPham});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int deleteKhobyID(String sanpham ) {
        int result = db.delete(TABLE_NAME, "sanpham=?", new String[]{sanpham});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
