package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：退款详情
 * @modifyRecord:修改记录
 */

public class RefundDetailActivity extends BaseActivity {
    private TextView shopName, refundAmount, status, orderNumber, title;
    private ImageView back, statusimage;
    private String orderson,goodsName,payMoney;
    private View statusBar;


    @Override
    public void initParms(Bundle params) {
        orderson =  params.getString("orderson");
        goodsName = params.getString("goodsName");
        payMoney = params.getString("payMoney");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return com.sinata.rwxchina.basiclib.R.layout.activity_refund_details;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refundAmount = view.findViewById(com.sinata.rwxchina.basiclib.R.id.refund_amount);
        shopName = view.findViewById(com.sinata.rwxchina.basiclib.R.id.refund_shopName);
        orderNumber = view.findViewById(com.sinata.rwxchina.basiclib.R.id.refund_orderNumber_tv);
        View titleView = view.findViewById(com.sinata.rwxchina.basiclib.R.id.RefundDetail_title);
        title = titleView.findViewById(com.sinata.rwxchina.basiclib.R.id.food_comment_title_tv);
        back = titleView.findViewById(com.sinata.rwxchina.basiclib.R.id.food_comment_back);
        View statusView = view.findViewById(com.sinata.rwxchina.basiclib.R.id.RefundDetail_status);
        status = statusView.findViewById(com.sinata.rwxchina.basiclib.R.id.payStatus_tv);
        statusimage = statusView.findViewById(com.sinata.rwxchina.basiclib.R.id.payStatus_iv);
        statusBar = findViewById(R.id.normal_fakeview);
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
         status.setText("退款中");
        statusimage.setImageResource(com.sinata.rwxchina.basiclib.R.mipmap.icon_cancel);
        refundAmount.setText(payMoney);
        orderNumber.setText(orderson);
        shopName.setText(goodsName);
        setTitleBarView();
    }

    private void setTitle() {
        title.setText("退款详情");
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
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
        StatusBarUtil.setTranslucentForImageViewInFragment(RefundDetailActivity.this, null);
    }
}
