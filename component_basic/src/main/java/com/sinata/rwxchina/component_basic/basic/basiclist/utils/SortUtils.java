package com.sinata.rwxchina.component_basic.basic.basiclist.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.adapter.SortAdapter;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.EventEnity;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.SortEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 排序工具类
 * @modifyRecord
 */

public class SortUtils {

    /**
     * 设置智能排序
     * @param context
     * @param mTopLine
     */
    public static void setSort(Context context,View mTopLine,String sort){
        //初始化排序选项
        final List<SortEntity> sortList=new ArrayList<SortEntity>();
        sortList.add(new SortEntity("comp","综合排序"));
        sortList.add(new SortEntity("near","离我最近"));
        sortList.add(new SortEntity("order","订单最多"));
        sortList.add(new SortEntity("evaluate","评价最好"));
        View view=LayoutInflater.from(context).inflate(R.layout.popup_brand_list_price,null);
        //设置recyclerview
        RecyclerView recyclerView=view.findViewById(R.id.popup_brand_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //设置recyclerview的适配器
        SortAdapter adapter=new SortAdapter(context,R.layout.item_pop_synthetical,sortList,sort);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //设置popupwindow
        final PopupWindow sortPop=new PopupWindow(context);
        sortPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        sortPop.setWidth(ScreenUtils.getScreenWidth(context));
        sortPop.setContentView(view);
        //将这两个属性设置为false，使点击popupwindow外面其他地方会消失
        sortPop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        sortPop.setOutsideTouchable(true);
        sortPop.setFocusable(true);
        sortPop.showAsDropDown(mTopLine, 0, 0);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String sortcontent=new Gson().toJson(sortList.get(position));
                EventEnity enity=new EventEnity();
                enity.setType("sort");
                enity.setContent(sortcontent);
                EventBus.getDefault().post(enity);
                if (sortPop.isShowing()){
                    sortPop.dismiss();
                }
            }
        });
    }
}
