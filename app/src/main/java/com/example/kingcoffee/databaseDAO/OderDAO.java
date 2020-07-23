package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.Oder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OderDAO extends DatabaseHelper {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TAG = "OderDrink";
    public static final String SQL_OEDR_DRINK = "CREATE TABLE OderDrink ( " +
            " id integer primary key," +
            " tensanpham text" +
            ",giatien double," +
            " ngay date," +
            " soluong integer," +
            " tableid text);";

    public static final String TABLE_NAME = "OderDrink";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public OderDAO(Context context) {
        super(context);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertOder(Oder oder) {
        ContentValues values = new ContentValues();
        values.put("tableid", oder.getIdTable());
        values.put("tensanpham", oder.getTenSp());
        values.put("giatien", oder.getGiatien());
        values.put("ngay", sdf.format(oder.getNgayGoi()));
        values.put("soluong", oder.getSoluong());

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;

    }

    public List<Oder> getOrderByIdTable(String idtable) {
        List<Oder> dsTT = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where tableid like '" + idtable + "'", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Oder tt = new Oder();
            tt.setId(cursor.getInt(0));
            tt.setTenSp(cursor.getString(1));
            tt.setGiatien(cursor.getDouble(2));
            try {
                tt.setNgayGoi(sdf.parse(cursor.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tt.setSoluong(cursor.getInt(4));
            tt.setIdTable(cursor.getString(5));
            dsTT.add(tt);
            Log.d("error", tt.toString());
            cursor.moveToNext();
        }
        Log.e(TAG, "getOrderByIdTable: " + cursor.getCount());
        cursor.close();
        return dsTT;

    }

    public boolean checkExistsOrderTableByIdTable(String tableid) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE tableid LIKE '" + tableid + "'",
                null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public List<String> checkMonAnByTenMonAn(String tensanpham, String idtable) {
        List<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLE_NAME +
                        " WHERE tensanpham LIKE '" + tensanpham + "' AND " +
                        "tableid LIKE '" + idtable + "'",
                null);

        if (cursor != null && cursor.moveToFirst()) {
            list.add(String.valueOf(cursor.getInt(0)));
            list.add(String.valueOf(cursor.getInt(4)));
        }
        return list;
    }

    public int CapnhatOder(Oder oder) {
        ContentValues values = new ContentValues();
        values.put("soluong", oder.getSoluong());
        int result = db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(oder.getId())});
        if (result == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public int deleteOder(String tenSp) {
        int result = db.delete(TABLE_NAME, "tensanpham=?", new String[]{tenSp});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteOderByIdTable(String idtable) {
        int result = db.delete(TABLE_NAME, "tableid=?", new String[]{idtable});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

}