package com.sinata.rwxchina.component_basic.car.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 汽车商铺列表适配器
 * @modifyRecord
 */

public class CarListAdapter extends BaseQuickAdapter<BaseShopInfo,BaseViewHolder> {
    private Context mC;
    public CarListAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<BaseShopInfo> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseShopInfo item) {
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getShop_logo(), (ImageView) helper.getView(R.id.item_carlist_logo));
        RatingBar bar=helper.getView(R.id.item_carlist_rating);
        bar.setRating(Float.parseFloat(item.getShop_starlevel()));
        helper.setText(R.id.item_carlist_name,item.getShop_name())
                .setText(R.id.item_carlist_price,item.getShop_goodsmoney_min())
                .setText(R.id.item_carlist_address,item.getShop_address())
                .setText(R.id.item_carlist_ratingtv,"（"+item.getShop_starlevel()+"分）")
                .setText(R.id.item_carlist_location,item.getDistance()+"km");
    }
}
