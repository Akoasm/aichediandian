package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.adpter.MycouponFragmentAdapter;
import com.sinata.rwxchina.basiclib.commonclass.fragment.MyCouponFragment;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：优惠券
 * @modifyRecord:修改记录
 */

public class MyCouponActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView title,rightIcon;
    private ImageView back;
    private List<String> list;
    private List<MyCouponFragment> fragmentList;
    private MycouponFragmentAdapter adapter;
    private View statusBar;

    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mycoupon;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.activity_myCoupon_tab);
        viewPager = view.findViewById(R.id.activity_coupon_vp);
        View titleView = view.findViewById(R.id.coupon_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        setTitle();
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTabTitle();
        setFragmentList();
        adapter = new MycouponFragmentAdapter(getSupportFragmentManager(),list);
        adapter.setFragmentList(fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTitleBarView();
    }

    private void setTabTitle() {
        list = new ArrayList<>();
        list.add("可使用");
        list.add("已使用");
        list.add("已失效");
    }

    private void setFragmentList() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            fragmentList.add(MyCouponFragment.getMyCouponFragment(i + ""));
        }
    }

    private void setTitle() {
        title.setText("代金券");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        rightIcon.setText("使用规则");
        rightIcon.setTextColor(ContextCompat.getColor(this,R.color.text_hint));
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CouponRuleActivity.class);
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
        StatusBarUtil.setTranslucentForImageViewInFragment(MyCouponActivity.this, null);
    }
}
