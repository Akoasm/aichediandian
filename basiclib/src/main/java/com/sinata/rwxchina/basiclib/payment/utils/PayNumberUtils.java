package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 购买数量及合计工具类
 * @modifyRecord
 */

public class PayNumberUtils {
    //购买数量,默认为1
    private static int buyNumber=1;
    //合计价格
    private static TextView allMoney;

    /**
     * 设置购买数量及合计
     * @param context
     * @param view
     * @param goodsInfo
     */
    public static void setPayNumber(final Context context, View view, final BaseGoodsInfo goodsInfo){
        //初始化购买数量
        String num=SinglePayment.getSinglePayment().getGoods_number();
        if (TextUtils.isEmpty(num)){
            buyNumber=1;
        }else {
            buyNumber= Integer.parseInt(num);
        }
        //购买数量减1
        RelativeLayout reduce=view.findViewById(R.id.pop_brand_confim_order_amount_reduce);
        //购买数量加1
        RelativeLayout add=view.findViewById(R.id.pop_brand_confim_order_amount_add);
        //购买数量
        final EditText buyNum=view.findViewById(R.id.activity_confim_order_sureorder_edit);
        //合计价格
        allMoney=view.findViewById(R.id.payment_discount_price);
        computePrice(context,goodsInfo);
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyNumber>1){
                    buyNumber--;
                    buyNum.setText(buyNumber+"");
                }
                SinglePayment.getSinglePayment().setGoods_number(buyNumber+"");
                computePrice(context,goodsInfo);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyNumber<999){
                    buyNumber++;
                    buyNum.setText(buyNumber+"");
                }
                SinglePayment.getSinglePayment().setGoods_number(buyNumber+"");
                computePrice(context,goodsInfo);
            }
        });

        buyNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)){
                    buyNumber=Integer.valueOf(s.toString());
                }
                if (buyNumber<1){
                    buyNumber=1;
                }
                SinglePayment.getSinglePayment().setGoods_number(buyNumber+"");
                computePrice(context,goodsInfo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 计算合计价格
     */
    private static void computePrice(Context context, BaseGoodsInfo goodsInfo){
        CallNumberPrice call= (CallNumberPrice) context;
        BigDecimal price=new BigDecimal(goodsInfo.getGoods_price());
        price=price.multiply(new BigDecimal(buyNumber));
        allMoney.setText("￥"+price.toString());
        call.callNumberPrice(buyNumber,price);
    }

    /**
     * 接口回调选择商品数量之后的价格
     */
    public interface CallNumberPrice{
        void callNumberPrice(int buyNum,BigDecimal price);
    }
}
