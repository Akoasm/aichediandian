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
import com.sinata.rwxchina.component_aboutme.R;
import com.sinata.rwxchina.component_aboutme.adapter.IntegralListAdapter;
import com.sinata.rwxchina.component_aboutme.bean.IntegralBean;
import com.sinata.rwxchina.component_aboutme.contract.IntegralContract;
import com.sinata.rwxchina.component_aboutme.presenter.IntegralPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：积分
 * @modifyRecord:修改记录
 */

public class IntegralActivity extends BaseActivity implements IntegralContract.View {
    private TextView integral, title;
    private ImageView back;
    private ListView integralList;
    private SmartRefreshLayout refreshLayout;
    private PageInfo pageInfo;
    private IntegralListAdapter adapter;
    private IntegralPresenter integralPresenter;
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
        return R.layout.activity_integral;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        integral = view.findViewById(R.id.integral_integralNow_tv);
        integralList = view.findViewById(R.id.integral_list_lv);
        refreshLayout = view.findViewById(R.id.integral_srl);
        View titleView = view.findViewById(R.id.integral_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore()==1) {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("page", pageInfo.getPage() + 1 + "");
                    integralPresenter.loadMore(params);
                }else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                integralPresenter.getData(true);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        integralPresenter = new IntegralPresenter(this);
        integralPresenter.attachView(this);
        integralPresenter.getData(false);
        setTitleBarView();
    }

    @Override
    public void showView(IntegralBean integralBean, boolean isRefresh) {
        integral.setText(integralBean.getIntegral());
        if (isRefresh){
            adapter.setList(integralBean.getIntegral_list());
            adapter.notifyDataSetChanged();
            refreshLayout.finishRefresh();
        }else {
            adapter = new IntegralListAdapter(this, integralBean.getIntegral_list());
            integralList.setAdapter(adapter);
        }
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(IntegralBean integralBean) {
        List<IntegralBean.IntegralListBean> listBeen = integralBean.getIntegral_list();
        listBeen.addAll(integralBean.getIntegral_list());
        adapter.setList(listBeen);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    private void setTitle() {
        title.setText("积分");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        integralPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(IntegralActivity.this, null);
    }

}
