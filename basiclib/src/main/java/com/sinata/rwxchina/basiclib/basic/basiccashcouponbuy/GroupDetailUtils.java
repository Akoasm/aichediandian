package com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.basiclib.basic.basicGroup.CashCouponGroupEntity;

import java.util.List;


/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 代金券和团购中套餐工具类
 * @modifyRecord
 */

public class GroupDetailUtils {

    /**
     * 设置代金券中套餐
     * @param view
     * @param type
     * @param entitys
     */
    public static void setCashCouponGroup(View view, Context context,String type, List<CashCouponGroupEntity> entitys){
        //隐藏代金券中的套餐信息（凯哥提的要求）
        if (view!=null){
            view.setVisibility(View.GONE);
            return;
        }
        TextView name=view.findViewById(R.id.basic_package_title);
        switch (type){
            case "6":
                name.setText("服务内容");
                break;
            default:
                name.setText("套餐");
                break;
        }
        RecyclerView recyclerView=view.findViewById(R.id.food_group_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        GroupDetailAdapter adapter=new GroupDetailAdapter(context,R.layout.item_basic_package_title,entitys);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(context,LinearLayoutManager.HORIZONTAL,2,context.getResources().getColor(R.color.background)));

    }

    /**
     * 设置团购中套餐
     * @param view
     * @param context
     * @param type
     * @param entitys
     */
    public static void setGroupDetailGroup(View view, Context context,String type, List<CashCouponGroupEntity> entitys){
        if (entitys==null||entitys.size()==0){
            view.setVisibility(View.GONE);
            return;
        }
        RecyclerView recyclerView=view.findViewById(R.id.food_group_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        GroupDetailAdapter adapter=new GroupDetailAdapter(context,R.layout.item_basic_package_title,entitys);
        recyclerView.setAdapter(adapter);

    }
}
