package com.sinata.rwxchina.component_entertainment.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_entertainment.R;
import com.sinata.rwxchina.component_entertainment.entity.NearScenicEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/19
 * @describe：景区列表适配器
 * @modifyRecord:
 */


public class ScenicBodyAdapter extends BaseQuickAdapter<NearScenicEntity.BodyBean,BaseViewHolder> {
    private Context mC;

    public ScenicBodyAdapter(@LayoutRes int layoutResId, @Nullable List<NearScenicEntity.BodyBean> data,Context context) {
        super(layoutResId, data);
        this.mC = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NearScenicEntity.BodyBean item) {
        helper.setText(R.id.item_scenic_name,item.getGoods_name())
                .setText(R.id.item_scenic_address,item.getGoods_address())
                .setText(R.id.item_scenic_mile,item.getDistance() + "km");
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getDefault_image(), (ImageView) helper.getView(R.id.item_scenic_image));
    }
}
