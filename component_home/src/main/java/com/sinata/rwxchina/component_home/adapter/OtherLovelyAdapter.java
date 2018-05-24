package com.sinata.rwxchina.component_home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.entity.OtherLovelyEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/11
 * @describe
 * @modifyRecord
 */

public class OtherLovelyAdapter extends BaseQuickAdapter<BaseShopInfo,BaseViewHolder> {
    private Context mC;
    private RecyclerView recyclerView;
    private OtherLovelyImageAdapter adapter;
    public OtherLovelyAdapter(@LayoutRes int layoutResId, @Nullable List<BaseShopInfo> data, Context context) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseShopInfo item) {
        helper.setText(R.id.guesslike_item_behind_name,item.getShop_name());
        helper.setText(R.id.guesslike_item_behind_address,item.getShop_address());
        helper.setText(R.id.guesslike_item_behind_kilometre,item.getDistance()+"km");
        helper.setText(R.id.guesslike_item_behind_price,"￥"+item.getShop_goodsmoney_min());
        helper.setText(R.id.guesslike_item_behind_describe,item.getShop_point());
        recyclerView=helper.getView(R.id.guesslike_item_behind_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(mC);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        adapter=new OtherLovelyImageAdapter(R.layout.guesslike_recycler_item,item.getShop_show(),mC);
        recyclerView.setAdapter(adapter);

    }
}
