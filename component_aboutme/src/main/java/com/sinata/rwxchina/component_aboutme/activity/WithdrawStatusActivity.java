package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.bean.WithdrawStatusBean;
import com.sinata.rwxchina.component_aboutme.contract.WithdrawStatusContract;
import com.sinata.rwxchina.component_aboutme.presenter.WithdrawStatusPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：提现状态
 * @modifyRecord:修改记录
 */

public class WithdrawStatusActivity extends BaseActivity implements WithdrawStatusContract.View {
    private TextView withdrawMoney_tv, bankCardInfo_tv, bankInfo_tv,dealing,title,rightIcon;
    private ImageView start, finished,back;
    private WithdrawStatusPresenter withdrawStatusPresenter;
    private String ID;
    private Map<String, String> param;
    private View statusBar;


    @Override
    public void initParms(Bundle params) {
        ID = params.getString("id");
        param = new HashMap<>();
        param.put("id", ID);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_withdrawstatus;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        withdrawMoney_tv = view.findViewById(R.id.withdrawStatus_withdrawMoney_tv);
        bankCardInfo_tv = view.findViewById(R.id.withdrawStatus_bankCardInfo_tv);
        bankInfo_tv = view.findViewById(R.id.withdrawStatus_bankInfo_tv);
        start = view.findViewById(R.id.status_start_iv);
        dealing = view.findViewById(R.id.dealing_tv);
        finished = view.findViewById(R.id.status_finish_iv);
        View titleView = view.findViewById(R.id.withdrawStatus_title);
        back  = titleView.findViewById(R.id.back);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
          back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
           finish() ;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        withdrawStatusPresenter = new WithdrawStatusPresenter(this);
        withdrawStatusPresenter.attachView(this);
        withdrawStatusPresenter.getData(param);
        setTitleBarView();
    }

    @Override
    public void showView(WithdrawStatusBean withdrawStatusBean) {
        withdrawMoney_tv.setText(withdrawStatusBean.getMoney());
        bankInfo_tv.setText(withdrawStatusBean.getBank_name());
        bankCardInfo_tv.setText(withdrawStatusBean.getCard_number());
        switch (withdrawStatusBean.getStatus()){
            case "1":
                finished.setImageResource(R.mipmap.status_gray);
                break;
            case "2":
                dealing.setText("已驳回");
                break;
            case "3":
                finished.setImageResource(R.mipmap.status_orange);
                break;
        }
    }
    private void setTitle(){
        title.setText("提现详情");
        rightIcon.setVisibility(View.GONE);
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
        StatusBarUtil.setTranslucentForImageViewInFragment(WithdrawStatusActivity.this, null);
    }
}
