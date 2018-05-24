package com.sinata.rwxchina.component_home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.HotSearchEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/27
 * @describe：
 * @modifyRecord:
 */


public class HotSearchAdapter extends BaseQuickAdapter<HotSearchEntity,BaseViewHolder> {
    public HotSearchAdapter(@LayoutRes int layoutResId, @Nullable List<HotSearchEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSearchEntity item) {
        helper.setText(R.id.item_home_search_recycler_text,item.getKeyword());
    }
}
