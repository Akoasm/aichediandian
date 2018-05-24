package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;
import com.sinata.rwxchina.component_aboutme.bean.RefundInfoBean;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;
import com.sinata.rwxchina.component_aboutme.contract.RefundInfoContract;
import com.sinata.rwxchina.component_aboutme.presenter.RefundInfoPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：退款失败
 * @modifyRecord:修改记录
 */

public class RefundFailedActivity extends BaseActivity implements RefundInfoContract.View {
    private TextView refundFailedReason, title;
    private ImageView back;
    private Button reApply;
    private Map<String, String> params;
    private RefundInfoPresenter refundInfoPresenter;
    private String type;
    private View statusBar;
    private RefundOrderBean refundOrderBean;
    private OrderBean orderBean;
    private boolean isOrder;

    @Override
    public void initParms(Bundle parms) {
        params = new HashMap<>();
        params.put("orderson", parms.getString("orderson"));
        type = parms.getString("type");
        isOrder = parms.getBoolean("isOrder",false);
        if (isOrder)
            orderBean = (OrderBean) parms.getSerializable("orderInfo");
        else
        refundOrderBean = (RefundOrderBean) parms.getSerializable("refundOrder");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_refundfailed;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refundFailedReason = view.findViewById(R.id.refundFailed_reason_tv);
        reApply = view.findViewById(R.id.refundFailed_reApply_btn);
        View titleView = view.findViewById(R.id.refundFailed_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        reApply.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle = new Bundle();
        int i = v.getId();
        if (i == R.id.refundFailed_reApply_btn) {

            switch (type){
                case "0":
                    bundle.putString("orderson",refundOrderBean.getOrderson());
                    bundle.putString("goodsName",refundOrderBean.getGoods_name());
                    bundle.putString("payMoney",refundOrderBean.getPay_money());
                    startActivity(ApplyRefund_MerhcantActivity.class,bundle);
                    finish();
                    break;
                case "1":
                    if (isOrder){
                        bundle.putSerializable("orderInfo", orderBean);
                    }else {
                        bundle.putBoolean("isFail", true);
                        bundle.putSerializable("orderInfo", refundOrderBean);
                    }
                    startActivity(ApplyRefundActivity.class, bundle);
                    finish();
                    break;
            }

        }
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
        refundFailedReason.setText(refundInfoBean.getRemarks());
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
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(RefundFailedActivity.this, null);
    }
}
