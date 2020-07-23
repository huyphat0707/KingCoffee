package com.example.kingcoffee.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kingcoffee.R;
import com.example.kingcoffee.activity.TableOder;
import com.example.kingcoffee.activity.TableThanhToan;
import com.example.kingcoffee.databaseDAO.OderDAO;
import com.example.kingcoffee.databaseDAO.TableDAO;
import com.example.kingcoffee.databaseDAO.ThucDonDAO;
import com.example.kingcoffee.model.Table;
import java.util.List;

public class TableAdapter extends BaseAdapter {
    Activity context;
    int resource;
    TableDAO tableDAO;
    List<Table> listRoom;
    ViewHolderTable holderTable;
     ThucDonDAO thucDonDAO;
    public TableAdapter(Activity context, int resource, List<Table> objects){
        this.context = context;
        this.resource = resource;
        this.listRoom = objects;
        tableDAO = new TableDAO(context);
        thucDonDAO = new ThucDonDAO(context);
    }
    @Override
    public int getCount() {
        return listRoom.size();
    }

    @Override
    public Object getItem(int position) {
        return listRoom.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


    public class ViewHolderTable{
        ImageView imgTable, imgOrder, imgthanhtoan, imgHideButton;
        TextView tvTableName;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {


        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holderTable = new ViewHolderTable();
            // attach custom layout to view
            view = inflater.inflate(R.layout.line_table,viewGroup,false);
            holderTable.imgTable = view.findViewById(R.id.img_table);
            holderTable.imgOrder = view.findViewById(R.id.img_table_order);
            holderTable.imgthanhtoan = view.findViewById(R.id.img_thanhtoan);
            holderTable.imgHideButton = view.findViewById(R.id.img_hideButton);
            holderTable.tvTableName = view.findViewById(R.id.tv_table_name);
            view.setTag(holderTable);
        } else {
            holderTable = (ViewHolderTable) view.getTag();
        }
        showButton();

        holderTable.tvTableName.setText("Bàn: "+listRoom.get(position).getTableId());
        holderTable.imgTable.setTag(position);

        //Gọi món
        holderTable.imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , TableOder.class);
                Bundle bundle = new Bundle();
                bundle.putString("POSITION", listRoom.get(position).getTableId());
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

        OderDAO oderDAO = new OderDAO(context);
        if (oderDAO.checkExistsOrderTableByIdTable(listRoom.get(position).getTableId())){
            holderTable.imgTable.setImageResource(R.drawable.bantrue);
        }else {
            holderTable.imgTable.setImageResource(R.drawable.ban);
        }

//        Thanh toán
        holderTable.imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , TableThanhToan.class);
                Bundle bd = new Bundle();
                bd.putString("POSITION", listRoom.get(position).getTableId());
                intent.putExtras(bd);
                context.startActivity(intent);
            }
        });
//        Xóa bàn
        final TableDAO tableDAO = new TableDAO(context);
        holderTable.imgHideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_tb_black_24dp);
                builder.setTitle("Xóa bàn sẽ xóa tất cả dữ liệu của bàn !");
                builder.setMessage("Bạn vẫn xóa ?");
                builder.setCancelable(false);
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Yêu cầu được thực hiện", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                OderDAO oderDAO = new OderDAO(context);
                if (oderDAO.deleteOderByIdTable(listRoom.get(position).getTableId()) > 0){
                    Log.e(TAG, "onClick: da xoa menu cua ban "+listRoom.get(position).getTableId());
                }

                if (tableDAO.deleteTable(listRoom.get(position).getTableId()) == 1){
                    Toast.makeText(context, "Đã xóa bàn số: "+listRoom.get(position).getTableId(),
                            Toast.LENGTH_SHORT).show();
                }

                listRoom.remove(position);
                notifyDataSetChanged();
                hideButton(true);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        return view;
    }

    private static final String TAG = "TableAdapter";
    private void showButton(){
        holderTable.imgOrder.setVisibility(View.VISIBLE);
        holderTable.imgthanhtoan.setVisibility(View.VISIBLE);
        holderTable.imgHideButton.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.effect_show_button);
        holderTable.imgOrder.startAnimation(animation);
        holderTable.imgthanhtoan.startAnimation(animation);
        holderTable.imgHideButton.startAnimation(animation);
    }
    private void hideButton(boolean effect){
        holderTable.imgOrder.setVisibility(View.INVISIBLE);
        holderTable.imgthanhtoan.setVisibility(View.INVISIBLE);
        holderTable.imgHideButton.setVisibility(View.INVISIBLE);

        if(effect){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.effect_hide_button);
            holderTable.imgOrder.startAnimation(animation);
            holderTable.imgthanhtoan.startAnimation(animation);
            holderTable.imgHideButton.startAnimation(animation);
        }

    }
    public void updateDate(List<Table> newList){
        this.listRoom = newList;
        notifyDataSetChanged();
    }
}
