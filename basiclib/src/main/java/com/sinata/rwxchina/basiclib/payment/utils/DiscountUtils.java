package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 支付页面折扣工具类
 * @modifyRecord
 */

public class DiscountUtils {

    /**
     * 设置折扣和
     * @param view
     * @param shopInfo
     * @param order 订单价格
     * @param discount
     */
    public static void setDiscount(Context context,View view, BaseShopInfo shopInfo,BigDecimal order, BigDecimal discount){
        CallDiscountMoney call= (CallDiscountMoney) context;
        TextView shopDiscountTv=view.findViewById(R.id.payment_discount_discount);
        TextView discountPriceTv=view.findViewById(R.id.payment_discount_price);
        String shopDiscount=shopInfo.getShop_discount();
        shopDiscountTv.setText(shopDiscount+"折");
        //店铺折扣
        BigDecimal discountBd=new BigDecimal(shopDiscount);
        //计算出折扣优惠的价格
        BigDecimal discountprice=discount.multiply(discountBd);
        discountprice=discountprice.divide(new BigDecimal("10"));
        discountprice=discount.subtract(discountprice);
        discountprice=order.subtract(discountprice);
        discountPriceTv.setText("￥"+discountprice.toString());
        //回调折扣优惠价格
        call.callDiscount(discountprice);
    }

    /**
     * 设置折扣(商城部分)
     * @param context
     * @param view
     * @param shopInfo
     * @param order 订单价格
     * @param discount 参与折扣的价格
     */
    public static void setDiscount(Context context, View view, ShopInfoBean.ShopinfoBean shopInfo, BigDecimal order, BigDecimal discount){
        CallDiscountMoney call= (CallDiscountMoney) context;
        TextView shopDiscountTv=view.findViewById(R.id.payment_discount_discount);
        TextView discountPriceTv=view.findViewById(R.id.payment_discount_price);
        String shopDiscount=shopInfo.getShop_discount();
        shopDiscountTv.setText(shopDiscount+"折");
        //店铺折扣
        BigDecimal discountBd=new BigDecimal(shopDiscount);
        //计算出折扣优惠的价格
        BigDecimal discountprice=discount.multiply(discountBd);
        discountprice=discountprice.divide(new BigDecimal("10"));
        discountprice=discount.subtract(discountprice);
        discountprice=order.subtract(discountprice);
        discountPriceTv.setText("￥"+discountprice.toString());
        //回调折扣优惠价格
        call.callDiscount(discountprice);
    }

    /**接口回调折扣优惠的金额*/
    public interface CallDiscountMoney{
        void callDiscount(BigDecimal discount);
    }
}
