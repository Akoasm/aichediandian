package com.sinata.rwxchina.component_basic.basic.basicname;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.component_basic.R;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe 商铺名称工具类
 * @modifyRecord
 */

public class NameUtils {
    /**
     * 设置店铺名称、评分等基本信息
     * @param view
     * @param shopInfo
     */
    public static void setName(View view, BaseShopInfo shopInfo){
        TextView name=view.findViewById(R.id.merchants_name);
        TextView score=view.findViewById(R.id.merchants_score);
        RatingBar rating=view.findViewById(R.id.merchants_rating);
        //人均消费
        LinearLayout averagePriceLL=view.findViewById(R.id.basic_merchants_averagePrice_ll);
        TextView averagePrice=view.findViewById(R.id.basic_merchants_averagePrice);
        //设置店铺基本信息
        name.setText(shopInfo.getShop_name());
        String star=shopInfo.getShop_starlevel();
        score.setText("（"+star+"分）");
        rating.setRating(Float.parseFloat(star));
        //美食模块需要显示人均消费
        if ("3".equals(shopInfo.getShop_type())){
            averagePriceLL.setVisibility(View.VISIBLE);
            averagePrice.setText(shopInfo.getShop_people_avgmoney()+"元");
        }
    }
}
