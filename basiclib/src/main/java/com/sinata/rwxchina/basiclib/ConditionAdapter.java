package com.sinata.rwxchina.basiclib;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/22
 * @describe 团购中条件适配器
 * @modifyRecord
 */

public class ConditionAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ConditionAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_purchase_condition_tv,item);
    }
}
