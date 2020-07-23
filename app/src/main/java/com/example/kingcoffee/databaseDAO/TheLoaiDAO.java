package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO extends DatabaseHelper {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static final String SQL_THE_LOAI = "CREATE TABLE THELOAI(tentheloai text primary key, vitri interger); " ;
    public static final String TABLE_NAME = "THELOAI";
    public static final String TAG = "THELOAI";

    public TheLoaiDAO(Context context){
        super(context);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertTheLoai(TheLoai theloai) {
        ContentValues values = new ContentValues();
        values.put("tentheloai", theloai.getTenTheLoai());
        values.put("vitri", theloai.getViTri());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;

    }


    public List<TheLoai> getAllTheloai() {
        List<TheLoai> dsTheloai = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            TheLoai tl = new TheLoai();
            tl.setTenTheLoai(cursor.getString(0));
            tl.setViTri(cursor.getInt(1));
            dsTheloai.add(tl);
            Log.d("error", tl.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsTheloai;

    }

    public int deleteTheLoaibyID(String tenTheLoai) {
        int result = db.delete(TABLE_NAME, "tenTheLoai=?", new String[]{tenTheLoai});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int CapnhatTheLoai(String tenTheLoai, String viTri){
        ContentValues values = new ContentValues();
        values.put("tentheloai",tenTheLoai);
        values.put("vitri", viTri);
        int result = db.update(TABLE_NAME,values,"tentheloai=?",new String[]{tenTheLoai});
        if (result == 0){
            return -1;
        }else {
            return 1;
        }
    }
}
