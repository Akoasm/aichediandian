package com.sinata.rwxchina.component_basic.hotel.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.hotel.entity.HotelReserveEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 酒店预约适配器
 * @modifyRecord
 */

public class HotelReserveAdapter extends BaseQuickAdapter<BaseGoodsInfo,BaseViewHolder> {
    private Context mC;
    public HotelReserveAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<BaseGoodsInfo> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseGoodsInfo item) {
        String acreage=item.getGoods_acreage()+"㎡";
        String lables=item.getGoods_type_labels_str();
        String isWindow=item.getGoods_is_window();
        if ("0".equals(isWindow)){
            isWindow="无窗";
        }else {
            isWindow="有窗";
        }
        String isBreakfast=item.getGoods_is_breakfast();
        if ("0".equals(isBreakfast)){
            isBreakfast="不含早";
        }else {
            isBreakfast="含早";
        }
        String type=acreage+" | "+lables+" | "+isWindow+" | "+isBreakfast;
        TextviewUtils.setStrikethrough((TextView) helper.getView(R.id.item_hotel_reserve_origin_price));
        helper.setText(R.id.item_hotel_reserve_name,item.getGoods_name())
                .setText(R.id.item_hotel_reserve_type,type)
                .setText(R.id.item_hotel_reserve_description,item.getGoods_subtitle())
                .setText(R.id.item_hotel_reserve_price,"￥"+item.getGoods_price())
                .setText(R.id.item_hotel_reserve_origin_price,"￥"+ item.getGoods_market_price())
        .addOnClickListener(R.id.item_hotel_reserve_btn);
    }
}
