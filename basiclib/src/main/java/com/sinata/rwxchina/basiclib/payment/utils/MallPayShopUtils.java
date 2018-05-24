package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2018/1/9
 * @describe 商城支付商品信息工具类
 * @modifyRecord
 */

public class MallPayShopUtils {

    /**
     * 设置商品信息
     * @param context
     * @param view
     * @param shopName
     * @param goodsinfo
     */
    public static void setPayShop(Context context, View view,String shopName,CommodityBean goodsinfo){
        //店铺名称
        TextView shopname=view.findViewById(R.id.payment_mall_shopinfor_title);
        //商品logo
        ImageView logo=view.findViewById(R.id.payment_mall_shopinfor_image);
        //商品名称
        TextView goodsName=view.findViewById(R.id.payment_mall_shopinfor_name);
        //购买数量
        TextView num=view.findViewById(R.id.payment_mall_shopinfor_num);
        //商品价格
        TextView price=view.findViewById(R.id.payment_mall_shopinfor_price);

        shopname.setText(shopName);
        ImageUtils.showImage(context, HttpPath.IMAGEURL+goodsinfo.getDefault_image(),logo);
        goodsName.setText(goodsinfo.getGoods_name());
        String buyNum=SinglePayment.getSinglePayment().getGoods_number();
        num.setText("×"+buyNum);
        BigDecimal money=new BigDecimal(goodsinfo.getGoods_price());
        money=money.multiply(new BigDecimal(buyNum));
        price.setText("实付：￥"+money.toString());
    }
}
