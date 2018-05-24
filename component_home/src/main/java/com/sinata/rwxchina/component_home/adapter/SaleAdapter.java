package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.LovelyEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/8
 * @describe
 * @modifyRecord
 */

public class SaleAdapter extends BaseQuickAdapter<CommodityBean,BaseViewHolder> {
    private Context mC;
    public SaleAdapter(@LayoutRes int layoutResId, @Nullable List<CommodityBean> data, Context context) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getDefault_image(), (ImageView) helper.getView(R.id.guesslike_item_img));
    }
}
