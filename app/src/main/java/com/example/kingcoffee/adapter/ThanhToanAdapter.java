package com.example.kingcoffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.model.Oder;

import java.util.List;

public class ThanhToanAdapter extends BaseAdapter {
    List<Oder> listTT;
    public Activity context;
    public LayoutInflater inflater;
    OderDAO oderDAO;
    private static final String TAG = "ThanhToanAdapter";
    public ThanhToanAdapter(List<Oder> listTT, Activity context) {
        super();
        this.listTT = listTT;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oderDAO = new OderDAO(context);
    }

    @Override
    public int getCount() {
        return listTT.size();
    }

    @Override
    public Object getItem(int position) {
        return listTT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        TextView txtTenSP;
        TextView txtSoLuong;
        TextView txtGiaSP;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ThanhToanAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ThanhToanAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.line_thanhtoan,null);
            viewHolder.txtTenSP     = convertView.findViewById(R.id.TenSP_TT);
            viewHolder.txtSoLuong   = convertView.findViewById(R.id.SoLuong_TT);
            viewHolder.txtGiaSP     = convertView.findViewById(R.id.GiaSP_TT);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ThanhToanAdapter.ViewHolder) convertView.getTag();
        }
        Oder tt = listTT.get(position);
        viewHolder.txtTenSP.setText("" + tt.getTenSp());
        viewHolder.txtSoLuong.setText("" + tt.getSoluong());
        viewHolder.txtGiaSP.setText("" + tt.getGiatien());

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Oder> items){
        this.listTT = items;
        notifyDataSetChanged();
    }

}