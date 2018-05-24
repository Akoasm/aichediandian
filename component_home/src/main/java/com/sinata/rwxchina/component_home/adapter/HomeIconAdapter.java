package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/6
 * @describe
 * @modifyRecord
 */

public class HomeIconAdapter extends BaseAdapter {
    private Context mC;
    private List<BaseIconEntity> icons;

    public HomeIconAdapter(Context mC) {
        this.mC = mC;
        this.icons=new ArrayList<BaseIconEntity>();
    }

    public void setIcons(List<BaseIconEntity> icons) {
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return icons.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;
        if (view==null){
            vh=new ViewHolder();
            view=LayoutInflater.from(mC).inflate(R.layout.home_icon_item,null);
            vh.iconLogo=view.findViewById(R.id.icon_item_img);
            vh.iconName=view.findViewById(R.id.icon_item_tv);
            view.setTag(vh);
        }else {
            vh= (ViewHolder) view.getTag();
        }
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+icons.get(position).getImg(),vh.iconLogo);
        vh.iconName.setText(icons.get(position).getTitle());

        return view;
    }

    private class ViewHolder{
        ImageView iconLogo;
        TextView iconName;
    }
}
