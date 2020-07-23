package com.example.kingcoffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.HoaDonDAO;
import com.example.kingcoffee.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm | dd-MM-yyyy");
    List<HoaDon> listHD;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonDAO hoaDonDAO;
    public HoaDonAdapter(List<HoaDon> listHD, Activity context) {
        super();
        this.listHD = listHD;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDAO = new HoaDonDAO(context);
    }

    @Override
    public int getCount() {
        return listHD.size();
    }

    @Override
    public Object getItem(int position) {
        return listHD.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        TextView txtId;
        TextView txtGio;
        TextView txtTongtien;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        HoaDonAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new HoaDonAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.line_hoadon,null);
            viewHolder.txtId        = convertView.findViewById(R.id.id);
            viewHolder.txtGio       = convertView.findViewById(R.id.gio);
            viewHolder.txtTongtien  = convertView.findViewById(R.id.tongtien);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (HoaDonAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.txtId.setText(listHD.get(position).getIdTable());
        viewHolder.txtGio.setText(simpleDateFormat.format(listHD.get(position).getGio()));
        viewHolder.txtTongtien.setText(listHD.get(position).getSumMonney()+"");


        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDon> items){
        this.listHD = items;
        notifyDataSetChanged();
    }
}
