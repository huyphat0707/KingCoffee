package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.HoaDon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends DatabaseHelper {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOADON = "CREATE TABLE HoaDon ( " +
            "mahoadon integer primary key autoincrement," +
            " idtable text," +
            " gio date," +
            " tongtien double);";
    public static final String TAG = "HoaDonDAO";
    SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss yyyy-MM-dd");

    public HoaDonDAO(Context context) {
        super(context);
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public int insertHoaDon(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("idtable", hoaDon.getIdTable());
        values.put("gio", sdf.format(hoaDon.getGio()));
        values.put("tongtien", hoaDon.getSumMonney());
        try {
            if (database.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;

    }

    public List<HoaDon> getHoaDon() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        List<HoaDon> dsHoaDon = new ArrayList<>();
        while (cursor.isAfterLast() == false) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(cursor.getInt(0));
            hoaDon.setIdTable(cursor.getString(1));
            try {
                hoaDon.setGio(sdf.parse(cursor.getString(2)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hoaDon.setSumMonney(cursor.getDouble(3));
            dsHoaDon.add(hoaDon);
            Log.d("error", hoaDon.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return dsHoaDon;
    }

    public int deleteHoaDon(String idTable) {
        int result = database.delete(TABLE_NAME, "idtable=?", new String[]{idTable});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "SELECT sum(tongtien) FROM HoaDon GROUP BY strftime('%d',HoaDon.ngay)";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
       String sSQL = " SELECT sum(tongtien) FROM HoaDon GROUP BY strftime('%m',HoaDon.gio)";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
      String sSQL = "SELECT sum(tongtien) FROM HoaDon GROUP BY strftime('%y',HoaDon.gio)";
        Cursor cursor = database.rawQuery(sSQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

}
