package com.sinata.rwxchina.component_entertainment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_entertainment.R;
import com.sinata.rwxchina.component_entertainment.entity.NearKtvEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/18
 * @describe：ktv适配器
 * @modifyRecord:
 */


public class KtvAdapter extends BaseAdapter {
    private Context mC;
    private List<NearKtvEntity.BodyBean> list;

    public KtvAdapter(Context mC) {
        this.mC = mC;
        this.list = new ArrayList<>();
    }

    public List<NearKtvEntity.BodyBean> getList() {
        return list;
    }

    public void setList(List<NearKtvEntity.BodyBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder vh = null;
        if(convertView == null){
            vh = new MyViewHolder();
            convertView = LayoutInflater.from(mC).inflate(R.layout.item_ktv, null);
            vh.ktv_item_image = convertView.findViewById(R.id.item_ktv_image);
            vh.ktv_item_name = convertView.findViewById(R.id.item_ktv_name);
            vh.ktv_item_address = convertView.findViewById(R.id.item_ktv_address);
            vh.ktv_item_price = convertView.findViewById(R.id.item_ktv_price);
            vh.ktv_item_linear = convertView.findViewById(R.id.item_ktv_linear);
            vh.linear_left = convertView.findViewById(R.id.item_ktv_left);
            vh.linear_right = convertView.findViewById(R.id.item_ktv_right);
            convertView.setTag(vh);
            if(position == 0){
                vh.linear_left.setVisibility(View.VISIBLE);
                vh.linear_right.setVisibility(View.GONE);
            }else  if(position == list.size()-1){
                vh.linear_left.setVisibility(View.GONE);
                vh.linear_right.setVisibility(View.VISIBLE);
            }
        }else{
            vh = (MyViewHolder) convertView.getTag();
        }
        ImageUtils.showImage(mC,HttpPath.IMAGEURL + list.get(position).getShop_logo(),vh.ktv_item_image);
        vh.ktv_item_name.setText(list.get(position).getShop_name());
        vh.ktv_item_address.setText(list.get(position).getShop_address());
        vh.ktv_item_price.setText(list.get(position).getShop_people_avgmoney()+"起");
        return convertView;
    }
    private class MyViewHolder {
        public ImageView ktv_item_image;
        public TextView ktv_item_name,ktv_item_address,ktv_item_price;
        public LinearLayout ktv_item_linear,linear_left,linear_right;
    }
}
