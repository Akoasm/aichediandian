package com.sinata.rwxchina.component_basic.basic.basicnearby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity;
import com.sinata.rwxchina.component_basic.finefood.activity.FoodActivity;
import com.sinata.rwxchina.component_basic.hotel.activity.HotelActivity;
import com.sinata.rwxchina.component_basic.ktv.activity.KTVActivity;
import com.sinata.rwxchina.component_basic.regimen.activity.HealthActivity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类附近店铺工具类
 * @modifyRecord
 */

public class NearByUtils {
    /**
     * 获取附近店铺并设置
     * @param view
     * @param activity
     */
    public static void getNearBy(final View view, final BaseActivity activity,BaseShopInfo shopInfo){
        Map<String,String> params=new HashMap<String,String>();
//        params.put("lng", "104.094136");
//        params.put("lat", "30.678093");
        params.put("lng", LocationUtils.getLng(activity));
        params.put("lat", LocationUtils.getLat(activity));
        params.put("shop_type", shopInfo.getShop_type());
        params.put("pageSize", "10");
        ApiManager apiManager=new ApiManager(activity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                final List<BaseShopInfo> nearByEntities=new Gson().fromJson(result,new TypeToken<List<BaseShopInfo>>(){}.getType());
                RecyclerView recyclerView=view.findViewById(R.id.food_near_rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                recyclerView.setNestedScrollingEnabled(false);
                NearByAdapter adapter=new NearByAdapter(activity,R.layout.item_basic_nearby,nearByEntities);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(activity,LinearLayoutManager.HORIZONTAL,2,activity.getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        BaseShopInfo shopInfo=nearByEntities.get(position);
                        Intent intent=null;
                        Bundle bundle=new Bundle();
                        switch (shopInfo.getShop_type()){
                            case "1"://汽车服务
                                intent=new Intent(activity, CleanCarActivity.class);
                                break;
                            case "2":
                                intent=new Intent(activity, HotelActivity.class);
                                break;
                            case "3":
                                intent=new Intent(activity, FoodActivity.class);
                                break;
                            case "4":
                                intent=new Intent(activity, KTVActivity.class);
                                break;
                            case "6":
                                intent=new Intent(activity, HealthActivity.class);
                                break;
                            default:
                                break;
                        }

                        bundle.putString("shopid",shopInfo.getShopid());
                        bundle.putString("shop_name",shopInfo.getShop_name());
                        intent.putExtras(bundle);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                });
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSHOPLIST,params);

    }
}
