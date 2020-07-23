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

import androidx.appcompat.view.menu.MenuAdapter;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.model.ThucDon;

import java.util.List;

public class ThucDonAdapter extends BaseAdapter {
    List<ThucDon> listThucDon;
    public Activity context;
    public LayoutInflater inflater;
    ThucDonDAO thucDonDAO;

    public ThucDonAdapter(List<ThucDon> listThucDon, Activity context) {
        super();
        this.listThucDon = listThucDon;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        thucDonDAO = new ThucDonDAO(context);
    }

    @Override
    public int getCount() {
        return listThucDon.size();
    }

    @Override
    public Object getItem(int position) {
        return listThucDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        TextView txtTenSP;
        TextView txtGiaSP;
        TextView txtTenTL;
        ImageView imgDeleteMN;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ThucDonAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ThucDonAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.line_menu,null);
            viewHolder.txtTenSP = (TextView) convertView.findViewById(R.id.Mon);
            viewHolder.txtGiaSP = (TextView) convertView.findViewById(R.id.SoTien);
            viewHolder.txtTenTL = (TextView) convertView.findViewById(R.id.Theloai);
            viewHolder.imgDeleteMN = (ImageView) convertView.findViewById(R.id.imDeleteMN);
            viewHolder.imgDeleteMN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thucDonDAO.deletethucdon(listThucDon.get(position).getTenSanPham());
                    listThucDon.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context , "Xóa menu thành công ", Toast.LENGTH_SHORT).show();

                }

            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ThucDonAdapter.ViewHolder) convertView.getTag();
        }
        ThucDon end = listThucDon.get(position);
        viewHolder.txtTenSP.setText("" + end.getTenSanPham());
        viewHolder.txtTenTL.setText( ""+end.getTenTheLoai());
        viewHolder.txtGiaSP.setText("" +end.getGiaSanPham());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<ThucDon> items){
        this.listThucDon = items;
        notifyDataSetChanged();
    }

}