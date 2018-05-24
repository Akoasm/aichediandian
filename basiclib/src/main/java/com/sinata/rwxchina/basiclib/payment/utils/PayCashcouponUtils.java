package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.payment.activity.PayCashcouponListActivity;
import com.sinata.rwxchina.basiclib.payment.entity.CouponEntity;
import com.sinata.rwxchina.basiclib.payment.entity.DeductibleEntity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2018/1/5
 * @describe 支付时选择代金券工具类
 * @modifyRecord
 */

public class PayCashcouponUtils {
    private static TextView cashucoupon;
    public static CallCoupon callCoupon;
    public static String price;
    public static String shopType;
    public static String shopId;
    public static String isMall;
    /**
     * 初始化代金券
     * @param context
     * @param view
     * @param deductible
     */
    public static void initCoupon(final Context context, View view, DeductibleEntity deductible){
        callCoupon= (CallCoupon) context;
        cashucoupon=view.findViewById(R.id.payment_discount_voucher);
        LinearLayout toCashcoupon=view.findViewById(R.id.payment_discount_voucher_linear);
        toCashcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PayCashcouponListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("cityid","1");
                bundle.putString("price",price);
                bundle.putString("shop_type",shopType);
                bundle.putString("shopid",shopId);
                bundle.putString("is_mall",isMall);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        int count=deductible.getCoupon().getCount();
        if (count<=0){
            cashucoupon.setText("暂无代金券可用");
        }else {
            cashucoupon.setText(count+"张可用");
        }
    }

    public static void setCoupon(CouponEntity coupon){
        //是否使用优惠券，coupon为空则不使用优惠券
        if (coupon!=null){
            callCoupon.callIsCoupon(coupon,true);
            cashucoupon.setText(coupon.getMoney());
            SinglePayment.getSinglePayment().setCouponid(coupon.getCouponid());
        }else {
            callCoupon.callIsCoupon(coupon,false);
        }
    }

    public static BigDecimal getCoupon(CouponEntity coupon,BigDecimal money){
        //使用代金券后订单还剩余的金额
        BigDecimal discountMoney = null;
        if (coupon!=null){
            discountMoney=money.subtract(new BigDecimal(coupon.getMoney()));
        }
        return discountMoney;
    }


    public interface CallCoupon{
        void callIsCoupon(CouponEntity coupon,boolean isCoupon);
    }
}
