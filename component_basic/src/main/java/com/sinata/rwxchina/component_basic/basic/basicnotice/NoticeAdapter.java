package com.sinata.rwxchina.component_basic.basic.basicnotice;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/25
 * @describe 用户须知适配器
 * @modifyRecord
 */

public class NoticeAdapter  extends BaseQuickAdapter<NoticeInfo,BaseViewHolder>{
    public NoticeAdapter(@LayoutRes int layoutResId, @Nullable List<NoticeInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeInfo item) {
        helper.setText(R.id.item_group_purchaseneeds_title,item.getTitle())
                .setText(R.id.item_group_purchaseneeds_details,item.getContent());
    }
}
