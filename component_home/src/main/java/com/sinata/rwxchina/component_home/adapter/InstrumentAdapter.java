package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinata.rwxchina.component_home.R;

import java.util.ArrayList;

/**
 * @author:wj
 * @datetime：2017/12/27
 * @describe：首页右上角弹出框适配器
 * @modifyRecord:
 */


public class InstrumentAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> groups;
    private LayoutInflater mLayoutInflater;

    public InstrumentAdapter(Context context, ArrayList<String> groups) {
        this.mContext = context;
        this.groups = groups;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_pop_home_instrument, null);
            convertView.setTag(viewHolder);
            viewHolder.groupItemTextView = (TextView) convertView.findViewById(R.id.item_pop_home_instrument_title);
            viewHolder.groupItemImageView = (ImageView) convertView.findViewById(R.id.item_pop_home_instrument_icon);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.groupItemTextView.setText(groups.get(position));
        if (position == 0) {
            viewHolder.groupItemImageView.setBackgroundResource(R.mipmap.icon_home_scan);
        } else if (position == 1) {
            viewHolder.groupItemImageView.setBackgroundResource(R.mipmap.icon_home_custom);
        } else if (position == 2) {
            viewHolder.groupItemImageView.setBackgroundResource(R.mipmap.icon_home_coupons);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView groupItemTextView;
        ImageView groupItemImageView;
    }

}
