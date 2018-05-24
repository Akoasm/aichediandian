package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.bean.ConfirmInfoBean;
import com.sinata.rwxchina.component_aboutme.contract.ConfirmReceiveContract;
import com.sinata.rwxchina.component_aboutme.presenter.ConfirmReceivePrensenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：确认收货
 * @modifyRecord:修改记录
 */

public class ConfirmReceiveActivity extends BaseActivity implements ConfirmReceiveContract.View{
    private TextView payStatus_tv, userName, userPhone, receiveAddress, commodityName, commodityCount,
            commodityPrice, title, finish;
    private ImageView  commodityImage, back;
    private ConfirmReceivePrensenter confirmReceivePrensenter;
    private Map<String,String> params;
    private View statusBar;

    @Override
    public void initParms(Bundle prams) {
         params = new HashMap<>();
        params.put("orderson",prams.getString("orderson"));
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_confirmreceive;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        View payStatusView = view.findViewById(R.id.confirmReceive_payStatus);
        payStatus_tv = payStatusView.findViewById(R.id.payStatus_tv);
        View receiverInfo = view.findViewById(R.id.confirmReceive_receiverInfo);
        userName = receiverInfo.findViewById(R.id.receiverInfo_receiver_tv);
        userPhone = receiverInfo.findViewById(R.id.receiverInfo_receiverPhone_tv);
        receiveAddress = receiverInfo.findViewById(R.id.receiverInfo_receiverAddress_tv);
        View commodityInfo = view.findViewById(R.id.confirmReceive_commodityInfo);
        commodityName = commodityInfo.findViewById(R.id.commodityName_tv);
        commodityImage = commodityInfo.findViewById(R.id.commodityImage_iv);
        commodityCount = commodityInfo.findViewById(R.id.commodityCount_tv);
        commodityPrice = commodityInfo.findViewById(R.id.commodityPrice_tv);
        View titleView = view.findViewById(R.id.confirmReceive_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        finish = titleView.findViewById(R.id.rightIcon_tv);
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
        confirmReceivePrensenter = new ConfirmReceivePrensenter(this);
        confirmReceivePrensenter.attachView(this);
        confirmReceivePrensenter.getData(params);
        setTitleBarView();
    }
    private void setTitle() {
        title.setText("确认收货");
        back.setVisibility(View.GONE);
        finish.setText("完成");
        finish.setTextColor(ContextCompat.getColor(this, com.sinata.rwxchina.basiclib.R.color.text_hint));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showView(ConfirmInfoBean confirmInfoBean) {
        payStatus_tv.setText("确认收货");
        userName.setText(confirmInfoBean.getReceipt_name());
        userPhone.setText(confirmInfoBean.getReceipt_phone());
        receiveAddress.setText(confirmInfoBean.getReceipt_address());
        commodityName.setText(confirmInfoBean.getGoods_name());
        ImageUtils.showImage(this, HttpPath.IMAGEURL + confirmInfoBean.getGoods_img(), commodityImage);
//        commodityCount.setText("x" + confirmInfoBean.getGoods_number());
        commodityPrice.setText(confirmInfoBean.getGoods_price());
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
        StatusBarUtil.setTranslucentForImageViewInFragment(ConfirmReceiveActivity.this, null);
    }
}
