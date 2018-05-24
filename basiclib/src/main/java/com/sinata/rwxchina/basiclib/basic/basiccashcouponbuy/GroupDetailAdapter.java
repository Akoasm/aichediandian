package com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.basic.basicGroup.CashCouponGroupEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 套餐详情适配器
 * @modifyRecord
 */

public class GroupDetailAdapter extends BaseQuickAdapter<CashCouponGroupEntity,BaseViewHolder> {
    private Context mC;
    public GroupDetailAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<CashCouponGroupEntity> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CashCouponGroupEntity item) {
        helper.setText(R.id.item_package_title,item.getM_meun_name());
        RecyclerView recyclerView=helper.getView(R.id.item_package_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(mC));
        GroupDetailItemAdapter adapter=new GroupDetailItemAdapter(R.layout.item_basic_package_content,item.getMeun_list());
        recyclerView.setAdapter(adapter);
    }
}
