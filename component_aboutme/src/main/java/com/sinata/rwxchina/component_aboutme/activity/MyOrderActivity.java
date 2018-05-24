package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_aboutme.adapter.MyOrderFragmentAdapter;
import com.sinata.rwxchina.component_aboutme.fragment.MyOrderFragment;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/15
 * @describe：全部订单
 * @modifyRecord:修改记录
 */

public class MyOrderActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView title;
    private ImageView back;
    private View statusBar;
    private MyOrderFragmentAdapter myOrderFragmentAdapter;
    private List<String> list;
    private List<MyOrderFragment> fragmentList;
    private int i;

    @Override
    public void initParms(Bundle parms) {
        setSetStatusBar(true);
        i = parms.getInt("page");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_myorder;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.myOrder_tab);
        viewPager = view.findViewById(R.id.myOrder_vp);
        View titleView = view.findViewById(R.id.myOrder_titleView);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        titleView.setBackgroundResource(R.color.white);
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
            try {
                Class cla=Class.forName("com.sinata.rwxchina.aichediandian.MainActivity");
                Intent intent=new Intent(this,cla);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        setViewPagerTitle();
        setFragmentList();
        myOrderFragmentAdapter = new MyOrderFragmentAdapter(getSupportFragmentManager(), list);
        myOrderFragmentAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(myOrderFragmentAdapter);
        viewPager.setCurrentItem(i);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        setTitleBarView();
    }

    private void setViewPagerTitle() {
        list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待验证");
        list.add("待收货");
        list.add("已完成");
        list.add("已取消");
    }

    private void setFragmentList() {
        String params = null;
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    params = "10";
                    break;
                case 2:
                    params = "4101";
                    break;
                case 3:
                    params = "4103";
                    break;
                case 4:
                    params = "90";
                    break;
                case 5:
                    params = "20";
                    break;
            }
            fragmentList.add(MyOrderFragment.getMyOrderFragment(params));
        }
    }

    private void setTitle() {
        title.setText("我的订单");
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
        StatusBarUtil.setTranslucentForImageViewInFragment(MyOrderActivity.this, null);
    }
}
