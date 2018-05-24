package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2018/1/9
 * @describe 总额工具类
 * @modifyRecord
 */

public class TotalMoneyUtils {

    /**
     * 设置商城支付总额
     * @param view
     * @param money 使用代金券，积分，余额之后的价格
     * @param goodsInfo
     */
    public static void setTotalMoney(Context context,View view, BigDecimal money, CommodityBean goodsInfo){
        CallTotalMoney call= (CallTotalMoney) context;
        //商品总额
        TextView totalMoney=view.findViewById(R.id.payment_mall_total_money);
        //运费
        TextView freight=view.findViewById(R.id.payment_discount_price);
        //运费抵扣
        TextView discountFreight=view.findViewById(R.id.mallPayment_discount_freight);
        //需付款
        TextView needpay=view.findViewById(R.id.mallpayment_needpay);
        //运费价格
        BigDecimal freightB=new BigDecimal(goodsInfo.getGoods_freight());
        //优惠券、余额、积分可以抵扣完商品价格
        //如果money大于运费，则说明运费没有抵扣完；如果money小于运费，则说明已经全部抵扣或部分抵扣运费
        if (money.compareTo(freightB)>=0){
            totalMoney.setText("￥"+money.subtract(freightB).toString());
            discountFreight.setText("运费已抵扣0元");
        }else {//优惠券、余额、积分可以抵扣完商品价格
            totalMoney.setText("￥"+money);
            discountFreight.setText("运费已抵扣：￥"+freightB.subtract(money).toString());
        }
        freight.setText("￥"+freightB.toString());
        BigDecimal needpayMoney=money;
        call.callTotalMoney(needpayMoney);
        needpay.setText("需付款：￥"+needpayMoney.toString());
    }

    public interface CallTotalMoney{
        //回调最后需要支付的价格
        void callTotalMoney(BigDecimal paymoney);
    }
}
