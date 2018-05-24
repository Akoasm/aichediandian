package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.utils.controlutils.EditTextUtils;
import com.sinata.rwxchina.component_aboutme.bean.BalanceBean;
import com.sinata.rwxchina.component_aboutme.contract.BalanceContract;
import com.sinata.rwxchina.component_aboutme.presenter.BalanceActivityPresenter;
import com.sinata.rwxchina.component_aboutme.R;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：余额
 * @modifyRecord:修改记录
 */

public class BalanceActivity extends BaseActivity implements BalanceContract.View {
    private TextView balance, todayIncome, monthsIncome, titleText, rightIcon, balanceDetail;
    private ImageView back;
    private RelativeLayout title;
    private View statusBar;
    private Button withDraw;
    private BalanceActivityPresenter balanceActivityPresenter;

    @Override
    public void initParms(Bundle parms) {
        balanceActivityPresenter = new BalanceActivityPresenter(this);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_balance;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        balance = view.findViewById(R.id.activity_balance_amount);
        todayIncome = view.findViewById(R.id.todayIncome_tv);
        monthsIncome = view.findViewById(R.id.monthsIncome_tv);
        withDraw = view.findViewById(R.id.withdrawsCash_btn);
        balanceDetail = view.findViewById(R.id.balanceDetail_tv);
        title = findViewById(R.id.balance_titleView);
        titleText = title.findViewById(R.id.titleLayout_title_tv);
        rightIcon = title.findViewById(R.id.rightIcon_tv);
        back = title.findViewById(R.id.back);
        statusBar = view.findViewById(R.id.withdraw_fakeview);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        withDraw.setOnClickListener(this);
        balanceDetail.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.withdrawsCash_btn) {
            startActivity(WithDrawActivity.class);
        } else if (i == R.id.balanceDetail_tv) {
            startActivity(BalanceDetailActivity.class);
        } else if (i == R.id.back) {
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        balanceActivityPresenter.attachView(this);
        getStatusBar(statusBar, ScreenUtils.getStatusHeight(this));
    }

    @Override
    protected void onStart() {
        balanceActivityPresenter.getData();
        super.onStart();
    }

    private void setTitle() {
        title.setBackgroundColor(Color.argb(0, 255, 255, 255));
        titleText.setText("我的钱包");
    }

    @Override
    public void showView(BalanceBean balanceBean) {
        balance.setText(EditTextUtils.roud2(Double.parseDouble(balanceBean.getBalance()),2)+"");
        todayIncome.setText(EditTextUtils.roud2(Double.parseDouble(balanceBean.getToday_money()),2)+"");
        monthsIncome.setText(EditTextUtils.roud2(Double.parseDouble(balanceBean.getMonth()),2)+"");
    }

    @Override
    protected void onDestroy() {
        balanceActivityPresenter.detachView();
        super.onDestroy();
    }
    /**
     * 设置假状态栏高度
     */
    private void getStatusBar(View view, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(BalanceActivity.this, null);
    }
}
