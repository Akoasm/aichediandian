package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.payment.entity.DeductibleEntity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2018/1/5
 * @describe 支付时积分兑换工具类
 * @modifyRecord
 */

public class PayIntegralUtils {
    private static TextView integralTv;
    private static CheckBox isIntegral;

    public static void initIntegral(Context context,View view, DeductibleEntity deductible){
        final CallIntegral call= (CallIntegral) context;
        integralTv=view.findViewById(R.id.payment_discount_integal);
        isIntegral=view.findViewById(R.id.payment_discount_integal_opt);
        integralTv.setText("可抵扣："+deductible.getIntegral_deduction()+"元");
        isIntegral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                call.callIsIntegral(isChecked);
            }
        });
    }

    public static BigDecimal setIntegral(DeductibleEntity deductible,BigDecimal money){
        //使用积分后订单还剩余的金额
        BigDecimal discountMoney = null;
        //账户有的积分
        BigDecimal integral=new BigDecimal(deductible.getIntegral_deduction());
        //判断money是否小于0，如果小于0，则显示为0
        if (money.compareTo(new BigDecimal("0"))<0){
            money=new BigDecimal("0");
        }
        //账户积分大于或等于订单金额
        if (integral.compareTo(money)>=0){
            //设置支付订单的积分抵扣金额
            SinglePayment.getSinglePayment().setIntegral_money(money.toString());
            integralTv.setText("已抵扣："+money.toString()+"元");
            discountMoney=new BigDecimal("0");
        }else {//账户积分小于订单金额
            //设置支付订单的积分抵扣金额
            SinglePayment.getSinglePayment().setIntegral_money(integral.toString());
            integralTv.setText("已抵扣："+integral.toString()+"元");
            discountMoney=money.subtract(integral);
        }
        return discountMoney;
    }

    public static void setIntegral(DeductibleEntity deductible){
        if (deductible!=null){
            integralTv.setText("可抵扣："+deductible.getIntegral_deduction()+"元");
        }
    }

    public interface CallIntegral{
        void callIsIntegral(boolean isIntegral);
    }
}
