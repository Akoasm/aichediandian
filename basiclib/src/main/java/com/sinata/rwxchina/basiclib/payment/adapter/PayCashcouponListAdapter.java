package com.sinata.rwxchina.basiclib.payment.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.payment.entity.CouponEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/1/8
 * @describe
 * @modifyRecord
 */

public class PayCashcouponListAdapter extends BaseQuickAdapter<CouponEntity,BaseViewHolder> {
    public PayCashcouponListAdapter(@LayoutRes int layoutResId, @Nullable List<CouponEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponEntity item) {
        helper.setText(R.id.item_couponName_tv,item.getCoupon_name())
                .setText(R.id.item_couponMoney_tv,item.getMoney())
                .setText(R.id.item_validity_tv,item.getEnd_time())
                .setText(R.id.item_couponRule_tv,item.getCoupon_describe());
    }
}
