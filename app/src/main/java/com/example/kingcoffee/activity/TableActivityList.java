package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import com.example.kingcoffee.R;
import com.example.kingcoffee.adapter.TableAdapter;
import com.example.kingcoffee.databaseDAO.TableDAO;
import com.example.kingcoffee.model.Table;
import java.util.List;

public class TableActivityList extends AppCompatActivity {
    GridView gvTables;
    List<Table> listroom;
    TableDAO roomDAO;
    TableAdapter adapter;
    TableDAO tableDAO;
    public Activity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);
        gvTables = findViewById(R.id.grban);
        roomDAO = new TableDAO(getApplicationContext());
        showListTable();

        gvTables.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(null, "onItemClickToGetPositon: "+position);
            }
        });
    }

    private void showListTable() {
        TableDAO tableDAO = new TableDAO(this);
        listroom = tableDAO.getAllTable();
        adapter = new TableAdapter(this,R.layout.line_table, listroom);
        gvTables.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        registerForContextMenu(gvTables);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(1,R.id.itemInserttableTable,1,R.string.insert_table);
        item.setIcon(R.drawable.thembanan);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemInserttableTable:
                insertTable();
                break;
        }
        return true;
    }
    private void insertTable() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View custom = inflater.inflate(R.layout.dialog_insert_table,null);
        builder.setView(custom);
        builder.setTitle("Thêm Bàn");
        Dialog dialog = builder.create();
        dialog.show();
        final EditText edthem = dialog.findViewById(R.id.edt_insert_tableName);

        dialog.findViewById(R.id.btn_insertTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableDAO tableDAO = new TableDAO(TableActivityList.this);
                Table table = new Table(edthem.getText().toString());
                if(tableDAO.insertTable(table) == true){
                    Toast.makeText(getApplicationContext(), "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
                    onResume();
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm bàn thất bại vui lòng nhập số bàn", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tableDAO = new TableDAO(this);
        List<Table> newList = tableDAO.getAllTable();
        adapter.updateDate(newList);
        adapter.notifyDataSetChanged();
    }
}
