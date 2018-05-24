package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_home.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/11
 * @describe 猜你喜欢中商铺展示图片的适配器
 * @modifyRecord
 */

public class OtherLovelyImageAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private Context mC;
    public OtherLovelyImageAdapter(@LayoutRes int layoutResId, @Nullable List<String> data,Context context) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item, (ImageView) helper.getView(R.id.guesslike_recycler_item_img));
    }
}
