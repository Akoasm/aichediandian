package com.sinata.rwxchina.component_basic.basic.basiccheckself;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.ordinarypayment.PayMentActivity;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.component_basic.R;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 自助买单工具类
 * @modifyRecord
 */

public class CheckSelfUtils {

    /**
     * 设置优惠买单
     * @param view
     * @param shopInfo
     */
    public static void setCheckSelf(final Context context, View view, final BaseShopInfo shopInfo){
        Button buySelf=view.findViewById(R.id.basic_buySelf);
        TextView discountTv=view.findViewById(R.id.health_discount);
        //店铺是否有折扣0：没有；1：有
        String isDiscount=shopInfo.getShop_is_discount();
        view.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
        String discount=shopInfo.getShop_discount();
        if (!TextUtils.isEmpty(discount)){
            if ("10".equals(discount)||"10.0".equals(discount)||"10.00".equals(discount)){
                discountTv.setText("本店暂无折扣");
            }else {
                discountTv.setText(shopInfo.getShop_discount()+"折");
            }
        }
        buySelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //标注为自助买单
                SinglePayment.getSinglePayment().setIs_youhui("1");
                //跳转到支付页面
                StartPayMentUtils.startPayment(context,shopInfo);
            }
        });
    }
}
