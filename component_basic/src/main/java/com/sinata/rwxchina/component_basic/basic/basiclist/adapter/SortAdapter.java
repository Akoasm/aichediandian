package com.sinata.rwxchina.component_basic.basic.basiclist.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.SortEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 智能排序适配器
 * @modifyRecord
 */

public class SortAdapter extends BaseQuickAdapter<SortEntity,BaseViewHolder> {
    private String sort;
    private Context mC;
    public SortAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<SortEntity> data, String sort) {
        super(layoutResId, data);
        this.mC=context;
        this.sort=sort;
    }

    @Override
    protected void convert(BaseViewHolder helper, SortEntity item) {
        TextView textView=helper.getView(R.id.item_pop_synthetical);
        textView.setText(item.getName());
        LogUtils.e("sortadapter",sort);
        if (item.getId().equals(sort)){
            textView.setTextColor(mC.getResources().getColor(R.color.colorOrange));
            helper.getView(R.id.item_pop_sort).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.item_pop_sort).setVisibility(View.GONE);
            textView.setTextColor(mC.getResources().getColor(R.color.text_hint));
        }
    }
}
