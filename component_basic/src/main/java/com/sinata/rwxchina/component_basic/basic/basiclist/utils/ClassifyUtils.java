package com.sinata.rwxchina.component_basic.basic.basiclist.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.adapter.ClassfiyAdapter;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.ClassfiyEntity;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.EventEnity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 分类工具类
 * @modifyRecord
 */

public class ClassifyUtils {

    /**
     * 设置分类
     * @param context
     * @param mTopLine
     */
    public static void setClassify(Context context, View mTopLine, final List<ClassfiyEntity> classfiyEntities, String classfiy){
        View view= LayoutInflater.from(context).inflate(R.layout.popup_brand_list_price,null);
        //设置recyclerview
        RecyclerView recyclerView=view.findViewById(R.id.popup_brand_list_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        //设置recyclerview的适配器
        ClassfiyAdapter adapter=new ClassfiyAdapter(context,R.layout.item_label,classfiyEntities,classfiy);
        recyclerView.setAdapter(adapter);
        //设置popupwindow
        final PopupWindow classfiyPop=new PopupWindow(context);
        classfiyPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        classfiyPop.setWidth(ScreenUtils.getScreenWidth(context));
        classfiyPop.setContentView(view);
        //将这两个属性设置为false，使点击popupwindow外面其他地方会消失
        classfiyPop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        classfiyPop.setOutsideTouchable(true);
        classfiyPop.setFocusable(true);
        classfiyPop.showAsDropDown(mTopLine, 0, 0);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String classfiy=new Gson().toJson(classfiyEntities.get(position));
                EventEnity enity=new EventEnity();
                enity.setType("classfiy");
                enity.setContent(classfiy);
                EventBus.getDefault().post(enity);
                if (classfiyPop.isShowing()){
                    classfiyPop.dismiss();
                }
            }
        });
    }
}
