package com.sinata.rwxchina.component_home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityDetailActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.SaleAdapter;
import com.sinata.rwxchina.component_home.entity.LovelyEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/8
 * @describe 特卖专区fragment
 * @modifyRecord
 */

public class SaleFragment extends BaseFragment {
    private List<CommodityBean> lovelyEntities;
    private SaleAdapter saleAdapter;
    private RecyclerView recyclerView;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_sale;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        recyclerView=view.findViewById(R.id.sale_recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        Bundle bundle1=getArguments();
        String data=bundle1.getSerializable("data").toString();
        LogUtils.e("zxc",data);
        lovelyEntities=new Gson().fromJson(data,new TypeToken<List<CommodityBean>>(){}.getType());
        saleAdapter=new SaleAdapter(R.layout.home_guesslike_item,lovelyEntities,getContext());
        recyclerView.setAdapter(saleAdapter);
        recyclerView.setNestedScrollingEnabled(false);// 解决滑动冲突
        recyclerView.setHasFixedSize(false);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(getActivity(),LinearLayoutManager.HORIZONTAL,10,getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));
        saleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("commodity",lovelyEntities.get(position));
                startActivity(CommodityDetailActivity.class,bundle);
            }
        });
    }
}
