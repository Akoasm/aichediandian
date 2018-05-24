package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentListActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.QRBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.OrderDetailContract;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.OrderDetailPresnter;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/22
 * @describe：商城订单详情
 * @modifyRecord:修改记录
 */

public class CommodityOrderCancelOrCompleteActivity extends BaseActivity implements OrderDetailContract.View {
    private TextView payStatus_tv, userName, userPhone, receiveAddress, commodityName, commodityCount,
            commodityPrice, commodityCountPrice, offIntegral, offBalance, deliveryCost, totalCost, orderNumber,
            orderDate, payWay, deliveryWay, title, finish, tel;
    private ImageView payStatus_iv, commodityImage, back;
    private Button buyAgain;
    private Map<String,String> params;
    private View recevierInfo;
    private OrderDetailPresnter orderDetailPresnter;
    private View statusBar;

    @Override
    public void initParms(Bundle parms) {
        String orderson = parms.getString("orderson");
        params = new HashMap<>();
        params.put("orderson",orderson);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_ordercomplete_commodity;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        View payStatusView = view.findViewById(R.id.orderComplete_payStatus_rl);
        payStatus_tv = payStatusView.findViewById(R.id.payStatus_tv);
        payStatus_iv = payStatusView.findViewById(R.id.payStatus_iv);
        recevierInfo = view.findViewById(R.id.orderCancel_receiverInfo_rl);
        userName = recevierInfo.findViewById(R.id.receiverInfo_receiver_tv);
        userPhone = recevierInfo.findViewById(R.id.receiverInfo_receiverPhone_tv);
        receiveAddress = recevierInfo.findViewById(R.id.receiverInfo_receiverAddress_tv);
        View commodityInfo = view.findViewById(R.id.orderComplete_commodityInfo_rl);
        commodityName = commodityInfo.findViewById(R.id.commodityName_tv);
        commodityImage = commodityInfo.findViewById(R.id.commodityImage_iv);
        commodityCount = commodityInfo.findViewById(R.id.commodityCount_tv);
        commodityPrice = commodityInfo.findViewById(R.id.commodityPrice_tv);
        View priceInfo = view.findViewById(R.id.orderComplete_commodityPriceInfo_rl);
        commodityCountPrice = priceInfo.findViewById(R.id.commodityPriceInfo_commodityCountPrice_tv);
        offIntegral = priceInfo.findViewById(R.id.commodityPriceInfo_integralUsed_tv);
        offBalance = priceInfo.findViewById(R.id.commodityPriceInfo_balanceUsed_tv);
        deliveryCost = priceInfo.findViewById(R.id.commodityPriceInfo_fare_tv);
        totalCost = priceInfo.findViewById(R.id.commodityPriceInfo_totalMoney_tv);
        View orderInfo = view.findViewById(R.id.orderComplete_commodityOrderInfo_rl);
        orderNumber = orderInfo.findViewById(R.id.orderInfo_orderNumber_tv);
        orderDate = orderInfo.findViewById(R.id.orderInfo_orderTime_tv);
        payWay = orderInfo.findViewById(R.id.orderInfo_payBy_tv);
        deliveryWay = orderInfo.findViewById(R.id.orderInfo_deliveryWay_tv);
        buyAgain = view.findViewById(R.id.orderComplete_buyAgain_btn);
        tel = view.findViewById(R.id.tel_tv);
        View titleView = view.findViewById(R.id.orderComplete_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        finish = titleView.findViewById(R.id.rightIcon_tv);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        buyAgain.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.orderComplete_buyAgain_btn) {

        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        orderDetailPresnter = new OrderDetailPresnter(this);
        orderDetailPresnter.attachView(this);
        orderDetailPresnter.getData(params);
        setTitleBarView();
    }

    private void setTitle() {
        title.setText("支付结果");
        back.setVisibility(View.GONE);
        finish.setText("完成");
        finish.setTextColor(ContextCompat.getColor(this, R.color.text_hint));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showView(List<OrderDetailBean> orderDetailBean) {
        if ("20".equals(orderDetailBean.get(0).getIndent_state())) {
            payStatus_tv.setText("已取消");
            payStatus_iv.setBackgroundResource(R.mipmap.icon_cancel);
            tel.setVisibility(View.GONE);
            userName.setText(orderDetailBean.get(0).getReceipt_name());
            userPhone.setText(orderDetailBean.get(0).getReceipt_phone());
            receiveAddress.setText(orderDetailBean.get(0).getReceipt_address());
        } else if ("90".equals(orderDetailBean.get(0).getIndent_state())) {
            recevierInfo.setVisibility(View.GONE);
        }else if ("4103".equals(orderDetailBean.get(0).getIndent_state())||"4102".equals(orderDetailBean.get(0).getIndent_state())){
            userName.setText(orderDetailBean.get(0).getReceipt_name());
            userPhone.setText(orderDetailBean.get(0).getReceipt_phone());
            receiveAddress.setText(orderDetailBean.get(0).getReceipt_address());
            payStatus_tv.setText("购买成功");
            buyAgain.setVisibility(View.GONE);
        }
        commodityName.setText(orderDetailBean.get(0).getGoods_name());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + orderDetailBean.get(0).getGoods_img(), commodityImage);
        commodityCount.setText("x" + orderDetailBean.get(0).getGoods_number());
        commodityPrice.setText(orderDetailBean.get(0).getGoods_price());
        commodityCountPrice.setText(orderDetailBean.get(0).getAfter_money());
        offIntegral.setText(orderDetailBean.get(0).getIntegral_money());
        offBalance.setText(orderDetailBean.get(0).getMoney());
        deliveryCost.setText(orderDetailBean.get(0).getFreight());
        totalCost.setText(orderDetailBean.get(0).getPay_money());
        orderNumber.setText(orderDetailBean.get(0).getOrderson());
        orderDate.setText(orderDetailBean.get(0).getCreatedate());
        switch (orderDetailBean.get(0).getPaytype()) {
            case "0":
                payWay.setText("未付款");
                break;
            case "1":
                payWay.setText("现金支付");
                break;
            case "2":
                payWay.setText("余额支付");
                break;
            case "11":
                payWay.setText("银联");
                break;
            case "12":
                payWay.setText("支付宝");
                break;
            case "13":
                payWay.setText("微信");
                break;
        }
        deliveryWay.setText(orderDetailBean.get(0).getSend_mode());
    }


    @Override
    public void showQRView(QRBean qrBean) {

    }

    @Override
    protected void onDestroy() {
        orderDetailPresnter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(CommodityOrderCancelOrCompleteActivity.this, null);
    }
}
