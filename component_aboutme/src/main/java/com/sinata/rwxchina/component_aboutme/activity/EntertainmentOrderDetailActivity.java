package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopMapActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.QRBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.OrderDetailContract;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.OrderDetailPresnter;
import com.sinata.rwxchina.basiclib.payment.ordinarypayment.PayMentActivity;
import com.sinata.rwxchina.basiclib.payment.utils.PayTypeUtils;
import com.sinata.rwxchina.basiclib.utils.appUtils.CallPhoneUtils;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.adapter.GoodsAttrLablesRVAdapter;
import com.sinata.rwxchina.component_aboutme.adapter.PackageRVadapter;
import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：娱乐订单详情
 * @modifyRecord:修改记录
 */

public class EntertainmentOrderDetailActivity extends BaseActivity implements OrderDetailContract.View {
    private TextView goodsName, goodsName1, originalPrice, discountPrice, shopName, shopScore, shopAddress,
            orderNumber, userPhone, payTime, count, realPay, title,refundComplete;
    private RecyclerView goodsAttrLables_rv, group_rv;
    private ImageView phone, goodsImage, back;
    private Button btn;
    private RatingBar shopLevel;
    private String orderSon, type;
    private LinearLayout shopAddress_ll;
    private OrderDetailPresnter entertainmentOrderDetailPresnter;
    private OrderDetailBean orderDetailBean;
    private Map<String,String> paramsMap;
    private GoodsAttrLablesRVAdapter goodsAttrLablesRVAdapter;
    private PackageRVadapter packageRVadapter;
    private View statusBar;


    @Override
    public void initParms(Bundle params) {
        paramsMap = new HashMap<>();
        orderSon = params.getString("orderson");
        paramsMap.put("orderson",orderSon);
        type = params.getString("type");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return com.sinata.rwxchina.basiclib.R.layout.activity_food_order_details;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        goodsName = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_head_name);
        goodsName1 = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_refund_name);
        goodsImage = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_goods_img);
        discountPrice = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_head_price);
        originalPrice = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_head_market_price);
        View shopInfo_ll = view.findViewById(com.sinata.rwxchina.basiclib.R.id.shopInfo_ll);
        View shopScore_ll = view.findViewById(com.sinata.rwxchina.basiclib.R.id.shopScore_ll);
        shopName = shopScore_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.merchants_name);
        shopScore = shopScore_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.merchants_score);
        shopLevel = shopScore_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.merchants_rating);
        shopAddress_ll = shopInfo_ll.findViewById(R.id.shopInfo_address_ll);
        shopAddress = shopInfo_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.health_address);
        phone = shopInfo_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.health_phone);
        goodsAttrLables_rv = findViewById(com.sinata.rwxchina.basiclib.R.id.goodsLabelsAttr_rv);
        View group_ll = view.findViewById(com.sinata.rwxchina.basiclib.R.id.group_ll);
        group_rv = group_ll.findViewById(com.sinata.rwxchina.basiclib.R.id.food_group_recycle);
        View orderInfo = findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_rl);
        orderNumber = orderInfo.findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_orderNumber_tv);
        userPhone = orderInfo.findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_userPhoneNumber_tv);
        payTime = orderInfo.findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_payTime_tv);
        count = orderInfo.findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_count_tv);
        realPay = orderInfo.findViewById(com.sinata.rwxchina.basiclib.R.id.orderInfo_realPay_tv);
        refundComplete = view.findViewById(com.sinata.rwxchina.basiclib.R.id.order_details_refund_complete);
        View titleView = view.findViewById(com.sinata.rwxchina.basiclib.R.id.orderDetail_title);
        title = titleView.findViewById(com.sinata.rwxchina.basiclib.R.id.titleLayout_title_tv);
        back = titleView.findViewById(com.sinata.rwxchina.basiclib.R.id.back);
        refundComplete = view.findViewById(com.sinata.rwxchina.basiclib.R.id.order_details_refund_complete);
        btn = view.findViewById(com.sinata.rwxchina.basiclib.R.id.food_order_details_refund);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        setTitle();
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        originalPrice.getPaint().setAntiAlias(true);// 抗锯齿
        btn.setOnClickListener(this);
        phone.setOnClickListener(this);
        shopAddress_ll.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
              int i = v.getId();
        if (i == R.id.food_order_details_refund){
            Bundle bundle =new Bundle();
            switch (type){
                case "4101":
                    bundle.putString("goodsName",orderDetailBean.getGoods_name());
                    bundle.putString("orderson",orderDetailBean.getOrderson());
                    bundle.putString("payMoney",orderDetailBean.getPay_money());
                    startActivity(ApplyRefund_MerhcantActivity.class,bundle);
                    break;
                case "10":
                    PayTypeUtils payTypeUtils=new PayTypeUtils(this);
                    payTypeUtils.againPay(orderDetailBean.getPay_money(),btn,orderDetailBean.getOrderson());
//                    bundle.putString("shopInfo",new Gson().toJson(orderDetailBean.getShop_info()));
//                    startActivity(PayMentActivity.class,bundle);
                    break;
                case "90":
                case "20":

                    break;
                default:
                    break;
            }
        }else if (i == R.id.shopInfo_address_ll){
               String shopInfo  = new Gson().toJson(orderDetailBean.getShop_info());
            Bundle bundle = new Bundle();
            bundle.putString("shopInfo",shopInfo);
            startActivity(BasicShopMapActivity.class,bundle);
        }else if (i == R.id.health_phone){
            CallPhoneUtils.call(this,orderDetailBean.getShop_info().getShop_telephone());
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        entertainmentOrderDetailPresnter = new OrderDetailPresnter(this);
        entertainmentOrderDetailPresnter.attachView(this);
        entertainmentOrderDetailPresnter.getData(paramsMap);
        setTitleBarView();
    }

    @Override
    public void showView(List<OrderDetailBean> orderDetailBean) {
        this.orderDetailBean = orderDetailBean.get(0);
        goodsName.setText(orderDetailBean.get(0).getGoods_name());
        goodsName1.setText(orderDetailBean.get(0).getGoods_name());
        ImageUtils.showImage(this, HttpPath.IMAGEURL+orderDetailBean.get(0).getDefault_image(),goodsImage);
        if (!orderDetailBean.get(0).getGoods_name().equals("自助买单")) {
            originalPrice.setText("￥" + orderDetailBean.get(0).getGoods_market_price());
            discountPrice.setText("￥" + orderDetailBean.get(0).getGoods_price());
        }else {
            discountPrice.setText("￥" + orderDetailBean.get(0).getPay_money());
        }
        shopName.setText(orderDetailBean.get(0).getShop_info().getShop_name());
        shopScore.setText("("+orderDetailBean.get(0).getShop_info().getShop_starlevel()+"分)");
        shopLevel.setRating(Float.parseFloat(orderDetailBean.get(0).getShop_info().getShop_starlevel()));
        shopAddress.setText(orderDetailBean.get(0).getShop_info().getShop_address());
        orderNumber.setText(orderDetailBean.get(0).getOrderson());
        userPhone.setText(orderDetailBean.get(0).getUser_tel());
        payTime.setText(orderDetailBean.get(0).getPaydate());
        count.setText(orderDetailBean.get(0).getGoods_number());
        realPay.setText(orderDetailBean.get(0).getPay_money()+"元");
        switch (type){
            case "10":
                btn.setText("付款");
                break;
            case "91":
                btn.setVisibility(View.GONE);
                refundComplete.setVisibility(View.VISIBLE);
                break;
            case "90":
            case "20":
                btn.setText("再次购买");
                break;
        }

        goodsAttrLablesRVAdapter = new GoodsAttrLablesRVAdapter(this,orderDetailBean.get(0).getGoods_labels_attr());
        goodsAttrLables_rv.setLayoutManager(new GridLayoutManager(this,3));
        goodsAttrLables_rv.setAdapter(goodsAttrLablesRVAdapter);
        packageRVadapter = new PackageRVadapter(this,orderDetailBean.get(0).getGoods_group_data());
        group_rv.setLayoutManager(new LinearLayoutManager(this));
        group_rv.setAdapter(packageRVadapter);
    }

    @Override
    public void showQRView(QRBean qrBean) {

    }

    private void setTitle() {
        title.setText("订单详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        entertainmentOrderDetailPresnter.detachView();
        super.onDestroy();
    }
    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setListImmersion(getWindow(),this,statusBar);
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(EntertainmentOrderDetailActivity.this, null);
    }
}
