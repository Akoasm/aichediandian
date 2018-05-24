package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.adapter.RefundReasonRVAdapter;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;
import com.sinata.rwxchina.component_aboutme.contract.ApplyRefund_MerhcantContract;
import com.sinata.rwxchina.component_aboutme.presenter.ApplyRefund_MerhcantPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：娱乐订单申请退款
 * @modifyRecord:修改记录
 */

public class ApplyRefund_MerhcantActivity extends BaseActivity implements ApplyRefund_MerhcantContract.View {
    private TextView refundMoney, title;
    private ImageView back;
    private EditText reason_et;
    private RecyclerView reason_rv;
    private Button submit;
    private List<String> reasonList;
    private RefundReasonRVAdapter refundReasonRVAdapter;
    private ApplyRefund_MerhcantPresenter applyRefundPresenter;
    private Map<String, String> paramsMap;
    private View statusBar;

    @Override
    public void initParms(Bundle params) {
        reasonList = new ArrayList<>();
        paramsMap  = new HashMap<>();
        reasonList.add("预约不上");
        reasonList.add("商家营业但不接待");
        reasonList.add("店家停业转让");
        reasonList.add("买多了/买错了");
        reasonList.add("计划有变，没时间");
        reasonList.add("联系不上商家");
        paramsMap.put("rtype", "51");
        if (params!=null) {
            paramsMap.put("orderson", params.getString("orderson"));
            paramsMap.put("goods_name", params.getString("goodsName"));
            paramsMap.put("total_money", params.getString("payMoney"));
        }
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return com.sinata.rwxchina.basiclib.R.layout.activity_apply_refund;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refundMoney = view.findViewById(R.id.apply_refund_money);
        reason_et = view.findViewById(R.id.apply_refund_inputReason_et);
        reason_rv = view.findViewById(R.id.apply_refund_reason_recycler);
        submit = view.findViewById(R.id.apply_refund_submit_btn);
        View titleView = view.findViewById(R.id.apply_refund_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        setTitle();
        refundMoney.setText(paramsMap.get("total_money") + "元");
        submit.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.apply_refund_submit_btn) {
            List<String> list = refundReasonRVAdapter.getParams();
            if (!TextUtils.isEmpty(reason_et.getText().toString().trim()))
                list.add(reason_et.getText().toString().trim());
            String s = "";
            for (int x = 0;x<list.size();x++){
                s = s+list.get(x)+",";
            }
            paramsMap.put("reason", s);
            applyRefundPresenter.postData(paramsMap);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        applyRefundPresenter = new ApplyRefund_MerhcantPresenter(this);
        applyRefundPresenter.attachView(this);
        refundReasonRVAdapter = new RefundReasonRVAdapter(this, reasonList);
        reason_rv.setLayoutManager(new LinearLayoutManager(this));
        reason_rv.setAdapter(refundReasonRVAdapter);
        setTitleBarView();
    }

    @Override
    public void showView(String s) {
        ToastUtils.showShort(s);
        Bundle bundle = new Bundle();
        bundle.putString("orderson", paramsMap.get("orderson"));
        bundle.putString("goodsName", paramsMap.get("goods_name"));
        bundle.putString("payMoney", paramsMap.get("total_money"));
        this.startActivity(RefundDetailActivity.class,bundle);
        finish();
    }

    private void setTitle() {
        title.setText("申请退款");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        applyRefundPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(ApplyRefund_MerhcantActivity.this, null);
    }
}
