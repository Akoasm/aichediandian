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
 * @describe 支付时余额抵扣工具类
 * @modifyRecord
 */

public class PayBalanceUtils {
    /**余额抵扣信息*/
    private static TextView balanceTv;
    /**是否选择使用余额抵扣控件*/
    private static CheckBox isBalance;

    public static void initBalance(Context context,View view, DeductibleEntity deductible){
        final CallBalance balance= (CallBalance) context;
        balanceTv=view.findViewById(R.id.payment_discount_balance);
        isBalance=view.findViewById(R.id.payment_discount_balance_opt);
        String money=deductible.getMoney();
        if (("0".equals(money))||(TextUtils.isEmpty(money))){
            balanceTv.setText("没有余额了呀~");
        }else {
            balanceTv.setText("当前钱包：￥"+money);
        }
        isBalance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                balance.callIsBalance(isChecked);
            }
        });
    }

    public static BigDecimal setBalance(DeductibleEntity deductible,BigDecimal money){
        //使用余额抵扣后订单还剩余的金额
        BigDecimal balanceMoney = null;
        //账户有的余额
        BigDecimal integral=new BigDecimal(deductible.getMoney());
        //账户余额大于或等于订单金额
        if (integral.compareTo(money)>=0){
            //设置支付订单的余额折扣金额
            SinglePayment.getSinglePayment().setMoney(money.toString());
            balanceTv.setText("已抵扣 ￥"+money.toString());
            balanceMoney=new BigDecimal("0");
        }else {//账户余额小于订单金额
            LogUtils.e("PayBalance","账户余额小于订单金额");
            //设置支付订单的余额折扣金额
            SinglePayment.getSinglePayment().setMoney(integral.toString());
            balanceTv.setText("已抵扣 ￥"+integral.toString());
            balanceMoney=money.subtract(integral);
        }
        return balanceMoney;
    }

    public static void setBalance(DeductibleEntity deductible){
        if (deductible!=null){
            String money=deductible.getMoney();
            if (("0".equals(money))||(TextUtils.isEmpty(money))){
                balanceTv.setText("没有余额了呀~");
            }else {
                balanceTv.setText("当前钱包：￥"+money);
            }
        }
    }

    public interface CallBalance{
        void callIsBalance(boolean isBalance);
    }
}
