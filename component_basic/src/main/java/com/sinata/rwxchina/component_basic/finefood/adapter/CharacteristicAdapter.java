package com.sinata.rwxchina.component_basic.finefood.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.finefood.entity.CharacteristicEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe
 * @modifyRecord
 */

public class CharacteristicAdapter extends BaseQuickAdapter<CharacteristicEntity,BaseViewHolder> {
    private Context mC;
    public CharacteristicAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<CharacteristicEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CharacteristicEntity item) {
        helper.setText(R.id.charactieristic_item_name,item.getGoods_name());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getDefault_image(), (ImageView) helper.getView(R.id.charactieristic_item_img));
    }
}
