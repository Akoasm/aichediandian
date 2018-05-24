package com.sinata.rwxchina.basiclib.payment.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinata.rwxchina.basiclib.ConditionAdapter;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.view.HorizontalListView;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 支付页面商品信息工具类
 * @modifyRecord
 */

public class InformationUtils {

    /**
     * 设置商品基本信息
     * @param context
     * @param view
     * @param goodsInfo
     */
    public static void setInformation(Context context, View view, BaseGoodsInfo goodsInfo, BaseShopInfo shopInfo){
        //头像
        ImageView logo=view.findViewById(R.id.food_order_details_goods_img);
        //商品名称
        TextView name=view.findViewById(R.id.food_order_details_head_name);
        //代金券价格（代金券商品才会有）
        TextView vocherPrice=view.findViewById(R.id.payment_voucher_price);
        //商品副标题（代金券商品才会有）
        TextView title=view.findViewById(R.id.payment_goods_title);
        RelativeLayout money=view.findViewById(R.id.payment_goods_money);
        //商品现价（套餐商品才会有）
        TextView price=view.findViewById(R.id.food_order_details_head_price);
        //商品原价（套餐商品才会有）
        TextView market=view.findViewById(R.id.food_order_details_head_market_price);
        //商品标签recyclerview
        RecyclerView attrs=view.findViewById(R.id.goodsLabelsAttr_rv);
        //设置recyclerview的显示格式
        attrs.setLayoutManager(new GridLayoutManager(context,3));
        ConditionAdapter adapter=new ConditionAdapter(R.layout.item_basic_purchase_condition,goodsInfo.getGoods_labels_attr());
        attrs.setAdapter(adapter);

        //是否是代金券商品（0：不是，1：是）
        if ("1".equals(goodsInfo.getIs_volume())){
            money.setVisibility(View.GONE);
            title.setText(goodsInfo.getVolume_description());
        }else {
            title.setText(goodsInfo.getGoods_subtitle());
            attrs.setVisibility(View.GONE);
        }
        //设置商品信息
        if ("1".equals(goodsInfo.getGoods_is_group())){//套餐商品
            name.setText(shopInfo.getShop_name());
            vocherPrice.setVisibility(View.GONE);
            //美食团购商品
            if ("3".equals(shopInfo.getShop_type())){
                title.setVisibility(View.GONE);
                attrs.setVisibility(View.VISIBLE);
            }
        }else {
            name.setText(goodsInfo.getGoods_name());
            vocherPrice.setText("￥"+goodsInfo.getGoods_price());
        }
        ImageUtils.showImage(context, HttpPath.IMAGEURL+goodsInfo.getDefault_image(),logo);
        //酒店商品不显示单位
        if ("2".equals(shopInfo.getShop_type())){
            String goods_nummber=SinglePayment.getSinglePayment().getGoods_number();
            BigDecimal goods_price=new BigDecimal(goodsInfo.getGoods_price());
            goods_price=goods_price.multiply(new BigDecimal(goods_nummber));
            BigDecimal goods_market_price=new BigDecimal(goodsInfo.getGoods_market_price());
            goods_market_price=goods_market_price.multiply(new BigDecimal(goods_nummber));
            price.setText("￥"+goods_price.toString());
            market.setText("￥"+goods_market_price.toString());
        }else {
            price.setText("￥"+goodsInfo.getGoods_price()+"/"+goodsInfo.getGoods_unit());
            market.setText("￥"+goodsInfo.getGoods_market_price()+"/"+goodsInfo.getGoods_unit());
        }
        TextviewUtils.setStrikethrough(market);
    }
}
