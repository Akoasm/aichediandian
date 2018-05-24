package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.QRBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.OrderDetailContract;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.OrderDetailPresnter;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;

import java.util.List;

/**
 * @author:zy
 * @detetime:2018/1/2
 * @describe：娱乐订单购买成功
 * @modifyRecord:修改记录
 */

public class EntertainmentOrderBuySuccessActivity extends BaseActivity implements OrderDetailContract.View {
    private ImageView mBack;
    private TextView mTitleLayoutTitleTv;
    private TextView mRightIconTv;
    ;
    private TextView mPaymentResultName;
    private TextView mPaymentResultNumber;
    private TextView mOrderInfoOrderNumberTv;
    private TextView mOrderInfoUserPhoneNumberTv;
    private TextView mOrderInfoPayTimeTv;
    private TextView mOrderInfoCountTv;
    private TextView mOrderInfoRealPayTv;
    private String orderson;
    private ImageView QRcode;
    private OrderDetailPresnter orderDetailPresnter;
    private View statusBar;


    @Override
    public void initParms(Bundle params) {
        orderson = params.getString("orderson");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_payment_result;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        View titleView = view.findViewById(R.id.buySuccessMerchant_title);
        mBack = titleView.findViewById(R.id.back);
        mTitleLayoutTitleTv = titleView.findViewById(R.id.titleLayout_title_tv);
        mRightIconTv = titleView.findViewById(R.id.rightIcon_tv);
        mPaymentResultName = findViewById(R.id.payment_result_name);
        mPaymentResultNumber = findViewById(R.id.payment_result_number);
//        View orderInfo = view.findViewById(R.id.orderInfo_rl);
        mOrderInfoOrderNumberTv = findViewById(R.id.orderInfo_orderNumber_tv);
        mOrderInfoUserPhoneNumberTv = findViewById(R.id.orderInfo_userPhoneNumber_tv);
        mOrderInfoPayTimeTv = findViewById(R.id.orderInfo_payTime_tv);
        mOrderInfoCountTv = findViewById(R.id.orderInfo_count_tv);
        mOrderInfoRealPayTv = findViewById(R.id.orderInfo_realPay_tv);
        QRcode = findViewById(R.id.payment_result_QRcode);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        orderDetailPresnter = new OrderDetailPresnter(this);
        orderDetailPresnter.attachView(this);
        orderDetailPresnter.getQRCode(orderson);
        setTitleBarView();
    }


    @Override
    public void showView(List<OrderDetailBean> entertainmentOrderDetailBean) {

    }

    @Override
    public void showQRView(QRBean qrBean) {
        mPaymentResultName.setText(qrBean.getGoods_name());
        String qrcode = qrBean.getCodestr();
        String res = qrcode.substring(0,4)+" "+qrcode.substring(4,8)+" "+qrcode.substring(8,12);
        mPaymentResultNumber.setText(res);
        ImageUtils.showImage(this, HttpPath.IMAGEURL + qrBean.getCodeimg(), QRcode);
        mOrderInfoOrderNumberTv.setText(qrBean.getOrderson());
        mOrderInfoUserPhoneNumberTv.setText(qrBean.getUser_tel());
        mOrderInfoPayTimeTv.setText(qrBean.getPaydate());
        mOrderInfoCountTv.setText(qrBean.getGoods_number());
        mOrderInfoRealPayTv.setText(qrBean.getPay_money());
    }

    private void setTitle() {
        mTitleLayoutTitleTv.setText("支付结果");
        mBack.setVisibility(View.GONE);
        mRightIconTv.setVisibility(View.VISIBLE);
        mRightIconTv.setText("完成");
        mRightIconTv.setTextColor(ContextCompat.getColor(this, R.color.text_hint));
        mRightIconTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(EntertainmentOrderBuySuccessActivity.this, null);
    }
}
