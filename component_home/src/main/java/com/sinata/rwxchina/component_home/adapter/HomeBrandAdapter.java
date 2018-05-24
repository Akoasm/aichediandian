package com.sinata.rwxchina.component_home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_home.entity.BrandEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2018/1/4
 * @describe：
 * @modifyRecord:
 */


public class HomeBrandAdapter extends BaseQuickAdapter<BrandEntity,BaseViewHolder> {

    public HomeBrandAdapter(@LayoutRes int layoutResId, @Nullable List<BrandEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandEntity item) {

    }
}
