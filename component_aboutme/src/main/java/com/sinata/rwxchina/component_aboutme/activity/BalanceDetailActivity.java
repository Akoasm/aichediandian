package com.sinata.rwxchina.component_aboutme.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.adapter.BalanceDetailAdapter;
import com.sinata.rwxchina.component_aboutme.bean.BalanceDetailBean;
import com.sinata.rwxchina.component_aboutme.contract.BalanceDetailContract;
import com.sinata.rwxchina.component_aboutme.presenter.BalanceDetailPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：收支明细
 * @modifyRecord:修改记录
 */

public class BalanceDetailActivity extends BaseActivity implements BalanceDetailContract.View {
    private ListView balanceDetail_lv;
    private BalanceDetailPresenter balanceDetailPresenter;
    private BalanceDetailAdapter balanceDetailAdapter;
    private ImageView back;
    private TextView title, rightIcon;
    private SmartRefreshLayout refreshLayout;
    private PageInfo pageInfo;
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
        return R.layout.activity_balancedetail;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        balanceDetail_lv = view.findViewById(R.id.balanceDetail_lv);
        View titleView = view.findViewById(R.id.balanceDetail_title);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        refreshLayout = view.findViewById(R.id.balanceDetail_srl);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Map<String, String> params = new HashMap<String, String>();

                if (pageInfo!=null&&pageInfo.getIsMore() == 1){
                    int page = pageInfo.getPage() + 1;
                    params.put("page", page + "");
                    balanceDetailPresenter.loadMore(params);
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                balanceDetailPresenter.getData(true);
            }
        });
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
            finish();
        }

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        balanceDetailPresenter = new BalanceDetailPresenter(this);
        balanceDetailPresenter.attachView(this);
        balanceDetailPresenter.getData(false);
        setTitleBarView();
    }


    @Override
    public void showView(List<BalanceDetailBean> balanceDetailBeen, boolean isRefresh) {
        setListData(balanceDetailBeen,isRefresh);
    }

    private void setListData(List<BalanceDetailBean> balanceDetailBeen, boolean isRefresh) {
        if (balanceDetailBeen != null && balanceDetailAdapter != null) {
            updateData(balanceDetailBeen);
            isStopRefresh(isRefresh);
        } else if (balanceDetailBeen == null && balanceDetailAdapter != null) {
            balanceDetailBeen = new ArrayList<>();
            updateData(balanceDetailBeen);
            isStopRefresh(isRefresh);
            ToastUtils.showShort("暂无数据");
        } else if (balanceDetailBeen != null && balanceDetailAdapter == null) {
            setAdapter(balanceDetailBeen);
        } else {
            balanceDetailBeen = new ArrayList<>();
            setAdapter(balanceDetailBeen);
            ToastUtils.showShort("暂无数据");
        }
    }

    private void updateData(List<BalanceDetailBean> balanceDetailBeen) {
        balanceDetailAdapter.setList(balanceDetailBeen);
        balanceDetailAdapter.notifyDataSetChanged();
    }

    private void setAdapter(List<BalanceDetailBean> balanceDetailBeen) {
        balanceDetailAdapter = new BalanceDetailAdapter(this, balanceDetailBeen);
        balanceDetail_lv.setAdapter(balanceDetailAdapter);
    }

    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(List<BalanceDetailBean> balanceDetailBeen) {
        List<BalanceDetailBean> list = balanceDetailAdapter.getList();
        list.addAll(balanceDetailBeen);
        balanceDetailAdapter.setList(list);
        balanceDetailAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    private void setTitle() {
        rightIcon.setVisibility(View.GONE);
        title.setText("收支明细");
    }

    @Override
    protected void onDestroy() {
        balanceDetailPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(BalanceDetailActivity.this, null);
    }
}
