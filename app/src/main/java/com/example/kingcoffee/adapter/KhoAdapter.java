package com.example.kingcoffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.KhoDAO;
import com.example.kingcoffee.model.Kho;

import java.text.SimpleDateFormat;
import java.util.List;

public class KhoAdapter extends BaseAdapter {
    List<Kho> listKho;
    public Activity context;
    public LayoutInflater inflater;
    KhoDAO khoDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public KhoAdapter(List<Kho> listKho, Activity context) {
        super();
        this.listKho = listKho;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoDAO = new KhoDAO(context);
    }


    @Override
    public int getCount() {
        return listKho.size();
    }

    @Override
    public Object getItem(int position) {
        return listKho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        TextView KhoName;
        TextView KhoSoLuong;
        TextView KhoDate;
        ImageView imgDeleteKho;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        KhoAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new KhoAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.line_kho,null);
            viewHolder.KhoDate = (TextView) convertView.findViewById(R.id.KhoDate);
            viewHolder.KhoName = (TextView) convertView.findViewById(R.id.KhoName);
            viewHolder.KhoSoLuong = (TextView) convertView.findViewById(R.id.KhoSoLuong);
            viewHolder.imgDeleteKho = (ImageView) convertView.findViewById(R.id.imDeleteKho);
            viewHolder.imgDeleteKho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    khoDAO.deleteKhobyID(listKho.get(position).getSanpham());
                    listKho.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context , "Xóa thành công ", Toast.LENGTH_SHORT).show();

                }

            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (KhoAdapter.ViewHolder) convertView.getTag();
        }
        Kho k = listKho.get(position);
        viewHolder.KhoDate.setText(sdf.format(k.getNgay()));
        viewHolder.KhoName.setText("" + k.getSanpham());
        viewHolder.KhoSoLuong.setText("" + k.getSoluong());


        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Kho> items){
        this.listKho = items;
        notifyDataSetChanged();
    }

}