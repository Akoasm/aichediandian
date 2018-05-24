package com.sinata.rwxchina.component_home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.car.activity.CarListActivity;
import com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.OtherLovelyAdapter;
import com.sinata.rwxchina.component_home.entity.OtherLovelyEntity;
import com.sinata.rwxchina.component_home.utils.ShopJumpUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/8
 * @describe 猜你喜欢其他中除去特卖区其他内容fragment
 * @modifyRecord
 */

public class OtherLovelyFragment extends BaseFragment {
    /**
     * 店铺类别和店铺标签（由主页请求返回的直接传入）
     */
    private String shopType, shopTypeLabels;
    /**
     * 当前位置的纬度和经度
     */
    private String lat, lng;
    /**
     * 数据集合
     */
    private List<BaseShopInfo> others;
    /**
     * 适配器
     */
    private OtherLovelyAdapter adapter;
    /**
     * 数据列表
     */
    private RecyclerView recyclerView;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_ohterlovely;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        Bundle bundle1 = getArguments();
        shopType = bundle1.getString("shop_type");
        shopTypeLabels = bundle1.getString("shop_type_labels");
        others = new ArrayList<BaseShopInfo>();
        recyclerView = view.findViewById(R.id.otherlovely_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LogUtils.e("shop_type=" + bundle1.getString("shop_type") + "   shop_type_labels=" + bundle1.getString("shop_type_labels"));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getOtherLovely();
    }

    private void getOtherLovely() {
        lat = LocationUtils.getLat(getContext());
        lng = LocationUtils.getLng(getContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("shop_type", shopType);
        params.put("shop_type_labels", shopTypeLabels);
        params.put("lng", lng);
        params.put("lat", lat);
        params.put("pageSize", "10");
        ApiManager apiManager = new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("OtherLovely", result);
                others = new Gson().fromJson(result, new TypeToken<List<BaseShopInfo>>() {
                }.getType());
                LogUtils.e("OtherLovely", others.toString());
                setData();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSHOPLIST, params);
    }

    private void setData() {

        adapter = new OtherLovelyAdapter(R.layout.home_guesslike_item_behind, others, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));
        recyclerView.setNestedScrollingEnabled(false);// 解决滑动冲突
        recyclerView.setHasFixedSize(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到相应模块
                ShopJumpUtils.toJump(getContext(), others.get(position).getShop_type(), others.get(position).getShopid(),others.get(position).getShop_name(),others.get(position).getM_shop_type_labels());
            }
        });

    }
}
