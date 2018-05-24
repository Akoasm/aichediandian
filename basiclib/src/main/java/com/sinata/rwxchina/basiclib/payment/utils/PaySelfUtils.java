package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 自助买单工具类
 * @modifyRecord
 */

public class PaySelfUtils {
    private static EditText orderPriceET;
    private static EditText noDiscountPriceET;
    /**
     * 设置自助买单
     * @param view
     */
    public static void setPaySelf(final Context context, View view){
        //订单金额
        orderPriceET= view.findViewById(R.id.self_payment_price_ori);
        //不参与优惠的金额
        noDiscountPriceET=view.findViewById(R.id.self_payment_price_nodiscount);
        EditTextUtils.setHint(orderPriceET,15);
        EditTextUtils.setHint(noDiscountPriceET,13);
        //订单金额发生改变
        orderPriceET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                priceChange(context,orderPriceET,noDiscountPriceET);

            }
        });
        //不参与优惠的金额发生改变
        noDiscountPriceET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                priceChange(context,orderPriceET,noDiscountPriceET);
            }
        });
    }

    /**
     * 自助买单金额发生改变或者不参与优惠的金额发生改变
     * @param orderPriceEt
     * @param noDiscountPriceEt
     */
    private static void priceChange(Context context,EditText orderPriceEt,EditText noDiscountPriceEt){
        CallDiscount callDiscount= (CallDiscount) context;
        String orderPrice=orderPriceEt.getText().toString();
        String noDiscountPrice=noDiscountPriceEt.getText().toString();
        if (TextUtils.isEmpty(orderPrice)){
            orderPrice="0";
        }
        if (TextUtils.isEmpty(noDiscountPrice)){
            noDiscountPrice="0";
        }

        BigDecimal order=new BigDecimal(orderPrice);
        BigDecimal noDiscount=new BigDecimal(noDiscountPrice);
        //不参与优惠金额大于消费总额
        if (noDiscount.compareTo(order)==1){
            ToastUtils.showShort("不参与优惠金额应小于消费总额");
            noDiscountPriceET.setText("0");
        }else {
            BigDecimal discount=order.subtract(noDiscount);
            callDiscount.callPrice(order,discount);
        }
    }

    /**接口回调参与优惠的金额*/
    public interface CallDiscount{
        /**orderprice订单价格，discount参与折扣的价格*/
        void callPrice(BigDecimal orderPrice,BigDecimal discount);
    }
}
