package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.adapter.RefundFragmentAdapter;
import com.sinata.rwxchina.component_aboutme.fragment.RefundOrderFragment;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/19
 * @describe：退款列表
 * @modifyRecord:修改记录
 */

public class RefundActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RefundFragmentAdapter adapter;
    private TextView title, rightIcon, mall, merchants;
    private ImageView back;
    private View statusBar;
    private List<String> list;
    private List<RefundOrderFragment> fragmentList;

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
        return R.layout.activity_refund;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.refund_tab);
        viewPager = view.findViewById(R.id.refund_vp);
        mall = view.findViewById(R.id.refund_mall_tv);
        merchants = view.findViewById(R.id.refund_merchants_tv);
        View titleView = view.findViewById(R.id.refunds_titleView);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        titleView.setBackgroundResource(R.color.white);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        mall.setOnClickListener(this);
        merchants.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.refund_mall_tv) {
            setViewPagerTitle(true);
            viewPager.setCurrentItem(0);
            setButtonBackground(true);
        } else if (i == R.id.refund_merchants_tv) {
            setViewPagerTitle(false);
            viewPager.setCurrentItem(0);
            setButtonBackground(false);
        }else if (i == R.id.back){
            finish();
        }
    }
    private void setButtonBackground(boolean isMall){
        if (isMall){
            mall.setBackgroundResource(R.mipmap.button_selected_bg);
            mall.setTextColor(Color.rgb(255,255,255));
            merchants.setBackgroundResource(R.color.white);
            merchants.setTextColor(Color.rgb(51,51,51));
        }else {
            merchants.setBackgroundResource(R.mipmap.button_selected_bg);
            merchants.setTextColor(Color.rgb(255,255,255));
            mall.setBackgroundResource(R.color.white);
            mall.setTextColor(Color.rgb(51,51,51));
        }
    }
    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        setViewPagerTitle(true);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTitleBarView();
    }

    private void setViewPagerTitle(boolean isMall) {
        list = new ArrayList<>();
        list.add("全部");
        list.add("退款中");
        if (isMall)
            list.add("已驳回");
        list.add("完成");
        if (isMall)
            setFragmentList("1");
        else
            setFragmentList("0");
    }

    private void setFragmentList(String isMall) {
        String params = null;
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)) {
                case "全部":
                    break;
                case "退款中":
                    params = "50";
                    break;
                case "已驳回":
                    params = "60";
                    break;
                case "完成":
                    params = "91";
                    break;
            }
            fragmentList.add(RefundOrderFragment.getRefundOrderFragment(isMall, params));
        }
        if (adapter != null) {
            adapter.setTitle(list);
            adapter.setFragmentList(fragmentList);
        } else {
            adapter = new RefundFragmentAdapter(getSupportFragmentManager(), list);
            adapter.setTitle(list);
            adapter.setFragmentList(fragmentList);
        }
    }

    private void setTitle() {
        title.setText("退款");
        title.setTextColor(Color.rgb(51, 51, 51));
        back.setImageResource(R.mipmap.back_black);
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
        StatusBarUtil.setTranslucentForImageViewInFragment(RefundActivity.this, null);
    }
}
