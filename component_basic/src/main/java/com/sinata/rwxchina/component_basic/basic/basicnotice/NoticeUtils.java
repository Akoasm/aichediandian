package com.sinata.rwxchina.component_basic.basic.basicnotice;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.component_basic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/25
 * @describe 用户须知工具类
 * @modifyRecord
 */

public class NoticeUtils {

    /**
     * 设置用户须知
     * @param view
     * @param context
     */
    public static void setNotice(View view, Context context, BaseGoodsInfo goodsInfo){
        List<NoticeInfo> infos=new ArrayList<NoticeInfo>();
        //使用规则
        String rule=goodsInfo.getGoods_rule();
        //有效期
        String period=goodsInfo.getGoods_period();
        //商家服务
        String services=goodsInfo.getGoods_services();
        //温馨提示
        String prompt=goodsInfo.getGoods_prompt();
        //食堂外带
        String pack=goodsInfo.getGoods_pack();
        //适用范围
        String range=goodsInfo.getGoods_scope_range();
        //预约提醒
        String reminder=goodsInfo.getGoods_reminder();
        //包间
        String room=goodsInfo.getGoods_room();
        //适用人数
        String people=goodsInfo.getGoods_scope_people();
        if (!TextUtils.isEmpty(rule)){
            infos.add(new NoticeInfo("使用规则",rule));
        }
        if (!TextUtils.isEmpty(period)){
            infos.add(new NoticeInfo("有效期",period));
        }
        if (!TextUtils.isEmpty(services)){
            infos.add(new NoticeInfo("商家服务",services));
        }
        if (!TextUtils.isEmpty(prompt)){
            infos.add(new NoticeInfo("温馨提示",prompt));
        }
        if (!TextUtils.isEmpty(pack)){
            infos.add(new NoticeInfo("食堂外带",pack));
        }
        if (!TextUtils.isEmpty(range)){
            infos.add(new NoticeInfo("适用范围",range));
        }
        if (!TextUtils.isEmpty(reminder)){
            infos.add(new NoticeInfo("预约提醒",reminder));
        }
        if (!TextUtils.isEmpty(room)){
            infos.add(new NoticeInfo("包间",room));
        }
        if (!TextUtils.isEmpty(people)){
            infos.add(new NoticeInfo("适用人数",people));
        }
        if (infos==null||infos.size()==0){
            view.setVisibility(View.GONE);
            return;
        }
        RecyclerView noticeView=view.findViewById(R.id.group_purchase_needs);
        noticeView.setLayoutManager(new LinearLayoutManager(context));
        NoticeAdapter adapter=new NoticeAdapter(R.layout.item_group_purchaseneeds,infos);
        noticeView.setAdapter(adapter);
    }
}
