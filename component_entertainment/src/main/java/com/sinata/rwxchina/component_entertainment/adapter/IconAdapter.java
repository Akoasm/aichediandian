package com.sinata.rwxchina.component_entertainment.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.entity.BaseIconEntity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_entertainment.R;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/15
 * @describe：娱乐Icon适配器
 * @modifyRecord:
 */


public class IconAdapter extends BaseQuickAdapter<BaseIconEntity,BaseViewHolder> {

    private Context mC;

    public IconAdapter(@LayoutRes int layoutResId, @Nullable List<BaseIconEntity> data, Context context) {
        super(layoutResId, data);
        this.mC=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, BaseIconEntity item) {
        helper.setText(R.id.icon_item_name,item.getTitle());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getImg(), (ImageView) helper.getView(R.id.icon_item_img));
    }
}
