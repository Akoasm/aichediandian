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
import com.sinata.rwxchina.component_entertainment.entity.NearHotelEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/18
 * @describe：酒店适配器
 * @modifyRecord:
 */


public class HotelAdapter extends BaseQuickAdapter<NearHotelEntity.BodyBean,BaseViewHolder> {
    private Context mC;

    public HotelAdapter(@LayoutRes int layoutResId, @Nullable List<NearHotelEntity.BodyBean> data, Context context) {
        super(layoutResId, data);
        this.mC = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NearHotelEntity.BodyBean item) {
        helper.setText(R.id.item_food_name, item.getShop_name())
                .setRating(R.id.item_food_rating, Float.parseFloat(item.getShop_starlevel()))
                .setText(R.id.item_food_score, item.getShop_starlevel())
                .setText(R.id.item_food_type, item.getShop_type_labels_zh())
                .setText(R.id.item_food_address, item.getShop_address())
                .setText(R.id.item_food_miles, item.getDistance()+ "km")
                .setText(R.id.item_food_price, item.getShop_people_avgmoney());
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getShop_logo(), (ImageView) helper.getView(R.id.item_food_image));
    }
}
