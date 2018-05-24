package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.bean.RefundInfoBean;
import com.sinata.rwxchina.component_aboutme.contract.RefundInfoContract;
import com.sinata.rwxchina.component_aboutme.presenter.RefundInfoPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：退款成功
 * @modifyRecord:修改记录
 */

public class RefundSuccessActivity extends BaseActivity implements RefundInfoContract.View {
    private TextView refundReason, shopName, refundMoney1, refundMoney2, refundTime, applyTime, title;
    private ImageView back;
    private Map<String, String> params;
    private RefundInfoPresenter refundInfoPresenter;
    private View statusBar;

    @Override
    public void initParms(Bundle parms) {
        params = new HashMap<>();
        params.put("orderson", parms.getString("orderson"));
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_refundsuccess;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refundReason = view.findViewById(R.id.refundSuccess_refundReason_tv);
        shopName = view.findViewById(R.id.refundSuccess_shopName_tv);
        refundMoney1 = view.findViewById(R.id.refundSuccess_refundMoney_tv1);
        refundMoney2 = view.findViewById(R.id.refundSuccess_refundMoney_tv);
        refundTime = view.findViewById(R.id.refundSuccess_refundTime_tv);
        applyTime = view.findViewById(R.id.refundSuccess_applyRefund_tv);
        View titleView = view.findViewById(R.id.refundSuccess_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
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
        refundInfoPresenter = new RefundInfoPresenter(this);
        refundInfoPresenter.attachView(this);
        refundInfoPresenter.getData(params);
        setTitleBarView();
    }

    @Override
    public void showView(RefundInfoBean refundInfoBean) {
        refundReason.setText(refundInfoBean.getReason());
        shopName.setText(refundInfoBean.getShop_name());
        refundMoney1.setText(refundInfoBean.getTotal_money());
        refundMoney2.setText(refundInfoBean.getTotal_money());
        refundTime.setText(refundInfoBean.getActiondate());
        applyTime.setText(refundInfoBean.getCreatedate());
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

    @Override
    protected void onDestroy() {
        refundInfoPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(RefundSuccessActivity.this, null);
    }
}
