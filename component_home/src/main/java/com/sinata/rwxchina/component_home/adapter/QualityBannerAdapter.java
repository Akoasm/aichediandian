package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.QualityBannerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/6
 * @describe
 * @modifyRecord
 */

public class QualityBannerAdapter extends BaseAdapter {
    private Context mC;
    private List<QualityBannerEntity> quality;

    public QualityBannerAdapter(Context mC) {
        this.mC = mC;
        this.quality=new ArrayList<QualityBannerEntity>();
    }

    public void setQuality(List<QualityBannerEntity> quality) {
        this.quality = quality;
    }

    @Override
    public int getCount() {
        return quality.size();
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
        ViewHolder vh=null;
        if (view==null){
            vh=new ViewHolder();
            view= LayoutInflater.from(mC).inflate(R.layout.home_qualitylife_item,null);
            vh.imageView=view.findViewById(R.id.home_qualitylife_logo);
            vh.left=view.findViewById(R.id.home_qualitylife_left);
            vh.right=view.findViewById(R.id.home_qualitylife_right);
            view.setTag(vh);
        }else {
            vh= (ViewHolder) view.getTag();
        }
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+quality.get(position).getUrlsmall(),vh.imageView);
        if (position==0){
            vh.left.setVisibility(View.VISIBLE);
            vh.right.setVisibility(View.GONE);
        }else if (position==quality.size()-1){
            vh.left.setVisibility(View.GONE);
            vh.right.setVisibility(View.VISIBLE);
        }

        return view;
    }
    private class ViewHolder{
        LinearLayout left,right;
        ImageView imageView;
    }
}
