package com.example.kingcoffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NhanVienDAO;
import com.example.kingcoffee.model.NhanVien;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
   List<NhanVien> listNV;
   public Activity context;
   public LayoutInflater inflater;
   NhanVienDAO nhanVienDAO;
    public NhanVienAdapter(Activity context, List<NhanVien> listNV) {
        super();
        this.listNV = listNV;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nhanVienDAO = new NhanVienDAO(context);
    }
    @Override
    public int getCount() {
        return listNV.size();
    }

    @Override
    public Object getItem(int position) {
        return listNV.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        ImageView img;
        TextView txtName,txtma;
        TextView thanhtien;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (convertView == null){
         viewHolder = new ViewHolder();
         convertView =inflater.inflate(R.layout.line_nhanvien,null);
         viewHolder.img = convertView.findViewById(R.id.im_logo_nhanvien);
         viewHolder.txtma = convertView.findViewById(R.id.tvMaNhanVien);
         viewHolder.txtName = convertView.findViewById(R.id.tvNameNhanVien);
         viewHolder.imgDelete= convertView.findViewById(R.id.imDeleteNhanVien);
         viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 nhanVienDAO = new NhanVienDAO(context);
                 int  result = nhanVienDAO.deleteNhanVienByID(listNV.get(position).getMaNhanVien());
                 if (result<1){
                     Toast.makeText(context, "Xóa nhân viên thất bại", Toast.LENGTH_SHORT).show();
                 }else {
                     listNV.remove(position);
                     notifyDataSetChanged();
                     Toast.makeText(context, "Xóa nhân viên thành công ", Toast.LENGTH_SHORT).show();
                     Log.e(null, "onClick:  "+result);
                 }
             }
         });
         convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhanVien nv = (NhanVien)listNV.get(position);
        viewHolder.img.setImageResource(R.drawable.user);
        viewHolder.txtma.setText("Mã NV: " + nv.getMaNhanVien());
        viewHolder.txtName.setText("Tên NV: " +nv.getTenNhanVien());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NhanVien> items){
        this.listNV = items;
        notifyDataSetChanged();
    }

}
