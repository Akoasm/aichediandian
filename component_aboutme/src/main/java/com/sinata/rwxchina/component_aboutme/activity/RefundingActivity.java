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

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：退款中
 * @modifyRecord:修改记录
 */

public class RefundingActivity extends BaseActivity {
    private ImageView back;
    private TextView title;
    private View statusBar;

    @Override
    public void initParms(Bundle parms) {
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_refunding;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        View titleView = view.findViewById(R.id.refunding_title);
        back  = titleView.findViewById(R.id.back);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
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
    }

    public void setTitle() {
        title.setText("退款详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitleBarView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(RefundingActivity.this, null);
    }
}
