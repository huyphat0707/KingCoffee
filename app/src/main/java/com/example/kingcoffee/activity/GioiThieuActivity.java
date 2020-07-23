package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kingcoffee.Main2Activity;
import com.example.kingcoffee.R;

public class GioiThieuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(1,R.id.itemShare,1,R.string.share);
        item.setIcon(R.drawable.ic_share_black_24dp);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemShare:
                Intent i = new Intent(GioiThieuActivity.this, ShareActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}
