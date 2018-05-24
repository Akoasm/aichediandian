package com.sinata.rwxchina.component_basic.basic.basiclist.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sinata.rwxchina.component_basic.car.activity.CleanCarActivity;
import com.sinata.rwxchina.component_basic.finefood.activity.FoodActivity;
import com.sinata.rwxchina.component_basic.hotel.activity.HotelActivity;
import com.sinata.rwxchina.component_basic.ktv.activity.KTVActivity;
import com.sinata.rwxchina.component_basic.regimen.activity.HealthActivity;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 娱乐各小模块跳转工具类
 * @modifyRecord
 */

public class ListJumpUtils {

    /**
     * 汽车娱乐商铺列表跳转到对应的模块
     * @param context
     * @param shop_type 区分跳转到哪个模块
     */
    public static void toJump(Context context,String shop_type,String shopId,String shopName){
        Intent intent=null;
        Bundle bundle=new Bundle();
        bundle.putString("shopid",shopId);
        bundle.putString("shop_name",shopName);
        switch (shop_type){
            case "jiudian"://酒店
                intent=new Intent(context, HotelActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "meishi"://美食
                intent=new Intent(context, FoodActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "ktv"://KTV
                intent=new Intent(context, KTVActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "yangsheng"://养生
                intent=new Intent(context, HealthActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case "qiche"://汽车服务
                intent=new Intent(context, CleanCarActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
