package com.sinata.rwxchina.basiclib.payment.utils;

import android.view.View;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;

/**
 * @author HRR
 * @datetime 2018/1/5
 * @describe 支付时套餐的工具类
 * @modifyRecord
 */

public class PayGroupUtils {

    /**
     * 设置套餐
     * @param view
     * @param goodsInfo
     */
    public static void setGroup(View view, BaseGoodsInfo goodsInfo){
        TextView name=view.findViewById(R.id.payment_middle_group_name);
        TextView describe=view.findViewById(R.id.payment_middle_group_describe);
        name.setText(goodsInfo.getGoods_name());
        describe.setText(goodsInfo.getGoods_subtitle());
    }
}
