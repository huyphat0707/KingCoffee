package com.example.kingcoffee.databaseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kingcoffee.databasehelper.DatabaseHelper;
import com.example.kingcoffee.model.Oder;
import com.example.kingcoffee.model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableDAO extends DatabaseHelper {
    private SQLiteDatabase db;
    private DatabaseHelper dbHeper;

    public static final String TABLE_NAME = "QL_TABLE";
    public static final String SQL_TABLE = "CREATE TABLE QL_TABLE(id text primary key);";
    public static final String TAG = "TableDAO";


    public TableDAO(Context context){
        super(context);
        dbHeper = new DatabaseHelper(context);
        db = dbHeper.getWritableDatabase();
    }



    public boolean insertTable(Table table) {
        ContentValues values = new ContentValues();
        values.put("id", table.getTableId());
        long result = db.insert(TABLE_NAME, null, values);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public List<Table> getAllTable() {
        List<Table> list = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Table table = new Table();
            table.setTableId(cursor.getString(0));
            list.add(table);
            cursor.moveToNext();
        }
        return list;
    }
    public int deleteTable(String idTable) {
        long rs = db.delete(TABLE_NAME, "id =?", new String[]{idTable});
        if (rs < 1){
            return -1;
        }
        return 1;
    }
}