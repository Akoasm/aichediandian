package com.sinata.rwxchina.component_basic.basic.basicgroupdetail;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sinata.rwxchina.basiclib.ConditionAdapter;
import com.sinata.rwxchina.component_basic.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/22
 * @describe 设置团购条件工具类
 * @modifyRecord
 */

public class ConditionUtils {

    /**
     * 设置团购中条件适配器
     * @param view
     * @param types
     */
    public static void setCondition(Context context,View view, List<String> types){
        if (types==null||types.size()==0){
            view.setVisibility(View.GONE);
            return;
        }
        RecyclerView recyclerView=view.findViewById(R.id.purchase_condition_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        ConditionAdapter adapter=new ConditionAdapter(R.layout.item_basic_purchase_condition,types);
        recyclerView.setAdapter(adapter);
    }
}
