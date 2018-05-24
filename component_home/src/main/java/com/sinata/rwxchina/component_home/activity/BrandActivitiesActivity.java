package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_home.Contract.BrandActivitiesContract;
import com.sinata.rwxchina.component_home.Presenter.BrandActivitiesPresenter;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.BrandActivitiesAdapter;
import com.sinata.rwxchina.component_home.entity.HotEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/20
 * @describe：活动区更多页面
 * @modifyRecord:添加点击事件
 */

public class BrandActivitiesActivity extends BaseActivity implements BrandActivitiesContract.View {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private TextView title;
    private ImageView back;
    private BrandActivitiesAdapter adapter;
    private BrandActivitiesPresenter brandActivitiesPresenter;
    private PageInfo pageInfo;
    private View statusBar;
    private Map<String,String> params;

    @Override
    public void initParms(Bundle parms) {
        setSetStatusBar(true);
        params = new HashMap<>();
        params.put("cityid","1");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_brandactivities;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.brandActivities_rv);
        refreshLayout = view.findViewById(R.id.brandActivities_srl);
        View titleView = view.findViewById(R.id.brandActivities_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    params.put("page", pageInfo.getPage() + 1 + "");
                    brandActivitiesPresenter.loadMore(params);
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                brandActivitiesPresenter.getData(params,true);
            }
        });

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        brandActivitiesPresenter = new BrandActivitiesPresenter(this);
        brandActivitiesPresenter.attachView(this);
        brandActivitiesPresenter.getData(params,false);
        setTitleBarView();
    }


    @Override
    public void showView(List<HotEntity> list, boolean isRefresh) {
       if (list!=null){
           adapter = new BrandActivitiesAdapter(list,this);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           recyclerView.setAdapter(adapter);

       }else {
           ToastUtils.showShort("暂无活动");
       }
       isStopRefresh(isRefresh);
    }

    @Override
    public void getPage(PageInfo pageInfo) {
       this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(List<HotEntity> list) {
        List<HotEntity> hotEntities = adapter.getList();
        hotEntities.addAll(list);
        adapter.setList(hotEntities);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }
    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }
    private void setTitle() {
        title.setText("活动专场");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(BrandActivitiesActivity.this, null);
    }
}
