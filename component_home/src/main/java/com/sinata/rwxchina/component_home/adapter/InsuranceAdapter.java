package com.sinata.rwxchina.component_home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.InsuranceDetailsEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：保险适配器
 * @modifyRecord:
 */


public class InsuranceAdapter extends BaseQuickAdapter<InsuranceDetailsEntity.ServiceListBean,BaseViewHolder> {

    public InsuranceAdapter(@LayoutRes int layoutResId, @Nullable List<InsuranceDetailsEntity.ServiceListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuranceDetailsEntity.ServiceListBean item) {
        helper.setText(R.id.item_insurance_details_name,item.getItem())
                .setText(R.id.item_insurance_details_content,item.getContent());

    }
}
