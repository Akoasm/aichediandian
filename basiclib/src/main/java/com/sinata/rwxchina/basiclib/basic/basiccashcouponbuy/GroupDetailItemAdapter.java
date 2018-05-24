package com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.basic.basicGroup.CashCouponGroupEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe  套餐详情的adapter
 * @modifyRecord
 */

public class GroupDetailItemAdapter extends BaseQuickAdapter<CashCouponGroupEntity.MeunListBean,BaseViewHolder> {
    public GroupDetailItemAdapter(@LayoutRes int layoutResId, @Nullable List<CashCouponGroupEntity.MeunListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashCouponGroupEntity.MeunListBean item) {
        helper.setText(R.id.item_package_name,item.getM_goods_name())
                .setText(R.id.item_package_num,item.getM_goods_number())
                .setText(R.id.item_package_money,item.getM_goods_money());
    }
}
