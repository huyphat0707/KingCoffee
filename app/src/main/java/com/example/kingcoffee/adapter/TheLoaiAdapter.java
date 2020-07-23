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
import com.example.kingcoffee.databaseDAO.TheLoaiDAO;
import com.example.kingcoffee.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> listTL;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(List<TheLoai> listTL, Activity context) {
        super();
        this.listTL = listTL;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return listTL.size();
    }

    @Override
    public Object getItem(int position) {
        return listTL.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        TextView txtTenTL;
        TextView txtViTri;
        ImageView imgDeleteTL;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TheLoaiAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new TheLoaiAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.line_theloai,null);
            viewHolder.txtTenTL = (TextView) convertView.findViewById(R.id.lineTheLoai);
            viewHolder.txtViTri = (TextView) convertView.findViewById(R.id.LineViTri);
            viewHolder.imgDeleteTL = (ImageView) convertView.findViewById(R.id.imDeleteTL);
            viewHolder.imgDeleteTL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteTheLoaibyID(listTL.get(position).getTenTheLoai());
                    listTL.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context , "Xóa thành công ", Toast.LENGTH_SHORT).show();

                }

            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (TheLoaiAdapter.ViewHolder) convertView.getTag();
        }
        TheLoai tl = listTL.get(position);
        viewHolder.txtTenTL.setText("" + tl.getTenTheLoai());
        viewHolder.txtViTri.setText("" + tl.getViTri());


        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<TheLoai> items){
        this.listTL = items;
        notifyDataSetChanged();
    }

}