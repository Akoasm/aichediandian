package com.sinata.rwxchina.component_basic.basic.basicgroup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupListActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicgroupdetail.GroupDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 所有分类店铺套餐工具类
 * @modifyRecord
 */

public class GroupUtils {
    /**
     * 设置店铺内套餐
     * @param view 代金券布局view
     * @param context 上下文环境
     * @param groupEntityList 代金券集合
     */
    public static void setGroup(View view, final Context context, final List<BaseGoodsInfo> groupEntityList, final BaseShopInfo shopInfo){
        List<BaseGoodsInfo> groupEntities=new ArrayList<BaseGoodsInfo>();
        TextView more=view.findViewById(R.id.group_more);
        LinearLayout diver=view.findViewById(R.id.basic_group_diver);
        LinearLayout moreLL=view.findViewById(R.id.basic_group_more);
        RecyclerView recyclerView=view.findViewById(R.id.health_group);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //如果套餐商品为空或者长度为0，则隐藏
        if (groupEntityList==null||groupEntityList.size()==0){
            view.setVisibility(View.GONE);
            return;
        }
        if (groupEntityList.size()<=3){
            diver.setVisibility(View.GONE);
            moreLL.setVisibility(View.GONE);
        }
        //在店铺页面最多只展示三个套餐
        if (groupEntityList.size()>3){
            for (int i=0;i<3;i++){
                groupEntities.add(groupEntityList.get(i));
            }
        }else {
            groupEntities.addAll(groupEntityList);
        }
        GroupAdapter adapter=new GroupAdapter(context,R.layout.item_basic_group,groupEntities);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(context,LinearLayoutManager.HORIZONTAL,2,context.getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String group=new Gson().toJson(groupEntityList.get(position));
                String shop=new Gson().toJson(shopInfo);
                Intent intent=new Intent(context, GroupDetailActivity.class);
                intent.putExtra("group",group);
                intent.putExtra("shop",shop);
                context.startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shop=new Gson().toJson(shopInfo);
                Intent intent=new Intent(context, GroupListActivity.class);
                intent.putExtra("shopId",shopInfo.getShopid());
                intent.putExtra("shop",shop);
                context.startActivity(intent);
            }
        });
    }
}
