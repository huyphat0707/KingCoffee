package com.example.kingcoffee.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.kingcoffee.databaseDAO.HoaDonDAO;
import com.example.kingcoffee.databaseDAO.KhoDAO;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.databaseDAO.TableDAO;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KingCoffe";
    public static final int VERSION = 3;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(ThucDonDAO.SQL_MENU);
        db.execSQL(NhanVienDAO.SQL_NHAN_VIEN);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(KhoDAO.SQL_KHO);
        db.execSQL(OderDAO.SQL_OEDR_DRINK);
        db.execSQL(HoaDonDAO.SQL_HOADON);
        db.execSQL(TableDAO.SQL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + ThucDonDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + NguoiDungDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + NhanVienDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + TheLoaiDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + KhoDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + OderDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + HoaDonDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + TableDAO.TABLE_NAME);
        onCreate(db);
    }
}
