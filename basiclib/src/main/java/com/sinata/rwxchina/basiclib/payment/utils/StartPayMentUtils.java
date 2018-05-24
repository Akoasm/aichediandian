package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.ordinarypayment.PayMentActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.utils.userutils.UserUtils;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 跳转到支付页面
 * @modifyRecord
 */

public class StartPayMentUtils {

    /**
     * 跳转到支付页面，需携带店铺信息及商品信息
     * @param context
     * @param goodsInfo
     * @param shopInfo
     */
    public static void startPayment(Context context,BaseGoodsInfo goodsInfo,BaseShopInfo shopInfo){
        if (!UserUtils.isLogin(context)){
            return;
        }
        //设置不是自主买单
        SinglePayment.getSinglePayment().setIs_youhui("0");
        String shopinfo=new Gson().toJson(shopInfo);
        String goodsinfo=new Gson().toJson(goodsInfo);
        Intent toPayment=new Intent(context, PayMentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("shopInfo",shopinfo);
        bundle.putString("goodsInfo",goodsinfo);
        toPayment.putExtras(bundle);
        context.startActivity(toPayment);
    }

    /**
     * 跳转到支付页面，需携带店铺信息及商品信息
     * @param context
     * @param goodsInfo
     */
    public static void startPayment(Context context,BaseGoodsInfo goodsInfo){
        //设置不是自主买单
        SinglePayment.getSinglePayment().setPaytype("0");
        String goodsinfo=new Gson().toJson(goodsInfo);
        Intent toPayment=new Intent(context, PayMentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("goodsInfo",goodsinfo);
        toPayment.putExtras(bundle);
        context.startActivity(toPayment);
    }

    /**
     * 跳转到支付页面，需携带店铺信息
     * 适用于自助买单
     * @param context
     * @param shopInfo
     */
    public static void startPayment(Context context, BaseShopInfo shopInfo){
        if (TextUtils.isEmpty(CommonParametersUtils.getUid(context))){
            ToastUtils.showShort("请登录后继续操作");
            Class cla= null;
            try {
                cla = Class.forName("com.sinata.rwxchina.component_login.activity.LoginPhoneActivity");
                Intent login=new Intent(context,cla);
                context.startActivity(login);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return;
        }
        //设置是自主买单
        SinglePayment.getSinglePayment().setPaytype("1");
        BaseGoodsInfo goodsInfo=new BaseGoodsInfo();
        //自助买单goods_id为0，商品数量为1
        goodsInfo.setGoods_id("0");
        goodsInfo.setGoods_number("1");
        goodsInfo.setShopid(shopInfo.getShopid());
        String shopinfo=new Gson().toJson(shopInfo);
        String goodsinfo=new Gson().toJson(goodsInfo);
        Intent toPayment=new Intent(context,PayMentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("shopInfo",shopinfo);
        bundle.putString("goodsInfo",goodsinfo);
        toPayment.putExtras(bundle);
        context.startActivity(toPayment);
    }
}
