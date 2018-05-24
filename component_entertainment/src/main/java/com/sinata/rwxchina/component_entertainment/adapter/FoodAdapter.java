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
import com.sinata.rwxchina.component_entertainment.entity.NearFoodEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/15
 * @describe：美食适配器
 * @modifyRecord:
 */


public class FoodAdapter extends BaseQuickAdapter<NearFoodEntity.OtherBean,BaseViewHolder> {

    private Context mC;

    public FoodAdapter(@LayoutRes int layoutResId, @Nullable List<NearFoodEntity.OtherBean> data,Context context) {
        super(layoutResId, data);
        this.mC = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, NearFoodEntity.OtherBean item) {
        helper.setText(R.id.item_food_name, item.getShop_name())
                .setRating(R.id.item_food_rating, Float.parseFloat(item.getShop_starlevel()))
                .setText(R.id.item_food_score, item.getShop_starlevel()+"分")
                .setText(R.id.item_food_type, item.getShop_type_labels_zh())
                .setText(R.id.item_food_address, item.getShop_address())
                .setText(R.id.item_food_miles, item.getDistance()+"km")
                .setText(R.id.item_food_price, item.getShop_people_avgmoney());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getShop_logo(), (ImageView) helper.getView(R.id.item_food_image));
    }
}
