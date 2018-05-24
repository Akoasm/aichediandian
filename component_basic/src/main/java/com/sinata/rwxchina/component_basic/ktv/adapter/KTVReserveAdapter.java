package com.sinata.rwxchina.component_basic.ktv.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe
 * @modifyRecord
 */

public class KTVReserveAdapter extends BaseQuickAdapter<BaseGoodsInfo,BaseViewHolder> {
    public KTVReserveAdapter(@LayoutRes int layoutResId, @Nullable List<BaseGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseGoodsInfo item) {
        helper.setText(R.id.item_ktv_reserve_time,item.getGoods_name())
                .setText(R.id.item_ktv_reserve_description,item.getGoods_subtitle())
                .setText(R.id.item_ktv_reserve_price,"￥"+item.getGoods_price())
        .addOnClickListener(R.id.item_ktv_reserve_btn);
    }
}
