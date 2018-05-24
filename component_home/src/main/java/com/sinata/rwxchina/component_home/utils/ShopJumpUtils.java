package com.sinata.rwxchina.component_home.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity;
import com.sinata.rwxchina.component_basic.finefood.activity.FoodActivity;
import com.sinata.rwxchina.component_basic.hotel.activity.HotelActivity;
import com.sinata.rwxchina.component_basic.ktv.activity.KTVActivity;
import com.sinata.rwxchina.component_basic.regimen.activity.HealthActivity;

/**
 * @author:wj
 * @datetime：2018/1/3
 * @describe：
 * @modifyRecord:
 */


public class ShopJumpUtils {

    /**
     * 汽车娱乐商铺列表跳转到对应的模块
     * @param context
     * @param shop_type 区分跳转到哪个模块
     */
    public static void toJump(Context context, String shop_type, String shopId,String shop_name,String type_labels){
        Intent intent=null;
        Bundle bundle=new Bundle();
        bundle.putString("shopid",shopId);
        bundle.putString("shop_name",shop_name);
        switch (shop_type){
            case "2"://酒店
                intent=new Intent(context, HotelActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "3"://美食
                intent=new Intent(context, FoodActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "4"://KTV
                intent=new Intent(context, KTVActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "6"://养生
                intent=new Intent(context, HealthActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "1"://汽车
                bundle.putString("m_shop_type_labels",type_labels);
                intent=new Intent(context, CleanCarActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
