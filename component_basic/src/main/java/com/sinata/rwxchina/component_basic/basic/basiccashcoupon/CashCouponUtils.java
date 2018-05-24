package com.sinata.rwxchina.component_basic.basic.basiccashcoupon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类代金券工具类
 * @modifyRecord
 */

public class CashCouponUtils {
    /**
     * 设置店铺内代金券
     * @param view 代金券布局view
     * @param context 上下文环境
     * @param cashCouponList 代金券集合
     */
    public static void setCashCoupon(View view, final Context context, final List<BaseGoodsInfo> cashCouponList, final BaseShopInfo shopInfo){
        LogUtils.e("CashCouponUtils","list="+cashCouponList.toString());
        RecyclerView recyclerView=view.findViewById(R.id.voucher);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        CashCouponAdapter adapter=new CashCouponAdapter(R.layout.item_basic_cash_voucher,cashCouponList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(context,LinearLayoutManager.HORIZONTAL,2,context.getResources().getColor(R.color.background)));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String shop=new Gson().toJson(shopInfo);
                String cashCoupon=new Gson().toJson(cashCouponList.get(position));
                Intent intent=new Intent(context,CashCouponBuyActivity.class);
                intent.putExtra("shop",shop);
                intent.putExtra("cashCoupon",cashCoupon);
                context.startActivity(intent);
            }
        });
    }
}
