package com.sinata.rwxchina.component_basic.basic.basiccashcoupon;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类代金券列表适配器
 * @modifyRecord
 */

public class CashCouponAdapter extends BaseQuickAdapter<BaseGoodsInfo,BaseViewHolder> {
    public CashCouponAdapter(@LayoutRes int layoutResId, @Nullable List<BaseGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseGoodsInfo item) {
        helper.setText(R.id.item_voucher_condition,item.getVolume_description())
                .setText(R.id.item_voucher_money,"￥"+item.getGoods_price())
                .setText(R.id.item_voucher_realmoney,"代"+item.getGoods_market_price()+"元")
                .setText(R.id.item_voucher_copies,"已售"+item.getGoods_sale()+"份");
    }
}
