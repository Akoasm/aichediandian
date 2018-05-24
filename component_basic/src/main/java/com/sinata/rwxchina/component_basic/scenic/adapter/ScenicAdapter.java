package com.sinata.rwxchina.component_basic.scenic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.basiclib.commonclass.entity.ScenicEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/11/30
 * @describe 景区列表适配器
 * @modifyRecord
 */

public class ScenicAdapter extends BaseQuickAdapter<ScenicEntity,BaseViewHolder>{
    private Context mContext;

    public ScenicAdapter(@LayoutRes int layoutResId, @Nullable List<ScenicEntity> data, Context mContext) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicEntity item) {
        ImageUtils.showImage(mContext, HttpPath.IMAGEURL+item.getDefault_image(), (ImageView) helper.getView(R.id.secnic_item_img));
        helper.getView(R.id.secnic_item_transparency).setBackgroundColor(Color.argb((int) 60, 102,102,102));
        helper.setText(R.id.secnic_item_name,item.getGoods_name());
        helper.setText(R.id.secnic_item_address,"地址："+item.getGoods_address());
        helper.setText(R.id.secnic_item_mileage,item.getDistance()+"km");
        helper.setText(R.id.scenic_item_subtitle,item.getGoods_subtitle());
    }

}
