package com.sinata.rwxchina.component_basic.basic.basicnearby;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
 * @datetime 2017/12/18
 * @describe 附近商铺适配器
 * @modifyRecord
 */

public class NearByAdapter extends BaseQuickAdapter<BaseShopInfo,BaseViewHolder> {
    private Context mC;
    public NearByAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<BaseShopInfo> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseShopInfo item) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(item.getDistance()).append("km ").append(item.getShop_address());
        SpannableString address_infor = new SpannableString(stringBuffer);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff720a"));
        address_infor.setSpan(colorSpan,0,item.getDistance().length()+2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.nearby_item_name,item.getShop_name())
                .setText(R.id.nearby_item_ratingnum,"（"+item.getShop_starlevel()+"分）")
                .setText(R.id.nearby_item_address,address_infor);
        //设置图片
        ImageUtils.showImage(mC, HttpPath.IMAGEURL+item.getShop_logo(), (ImageView) helper.getView(R.id.nearby_item_img));
        RatingBar ratingBar=helper.getView(R.id.nearby_item_rating);
        ratingBar.setRating(Float.parseFloat(item.getShop_starlevel()));
    }
}
