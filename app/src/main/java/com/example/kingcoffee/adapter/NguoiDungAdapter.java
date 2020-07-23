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
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;
import com.example.kingcoffee.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> listND;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(List<NguoiDung> listND, Activity context) {
        super();
        this.listND = listND;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return listND.size();
    }

    @Override
    public Object getItem(int position) {
        return listND.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.line_user,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.im_logo_TL);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.imDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungByID(listND.get(position).getUserName());
                    listND.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context , "Xóa người dùng thành công ", Toast.LENGTH_SHORT).show();

                }

            });
            convertView.setTag(viewHolder);
        }else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        NguoiDung end = listND.get(position);
        viewHolder.txtName.setText(end.getUserName());
        viewHolder.txtPhone.setText(end.getPhone());

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> items){
        this.listND = items;
        notifyDataSetChanged();
    }

}
