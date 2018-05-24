package com.sinata.rwxchina.component_basic.finefood.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.finefood.entity.CharacteristicEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 特色菜列表适配器
 * @modifyRecord
 */

public class SpecialitiesAdapter extends BaseQuickAdapter<CharacteristicEntity,BaseViewHolder> {
    private Context mC;
    public SpecialitiesAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<CharacteristicEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CharacteristicEntity item) {
        TextView top=helper.getView(R.id.specialities_item_top);
        ImageView logo=helper.getView(R.id.specialities_item_img);
        int postion=helper.getPosition();
        if (postion<3){
            top.setVisibility(View.VISIBLE);
            top.setText("TOP"+(postion+1));
        }else {
            top.setVisibility(View.GONE);
        }
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getDefault_image(),logo);
        helper.setText(R.id.specialities_item_name,item.getGoods_name())
                .setText(R.id.specialities_item_price,"￥"+item.getGoods_price())
        .addOnClickListener(R.id.specialities_item_img);
    }
}
