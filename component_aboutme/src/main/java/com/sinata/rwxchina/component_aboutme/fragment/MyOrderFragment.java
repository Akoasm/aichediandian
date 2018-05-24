package com.sinata.rwxchina.component_aboutme.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.adapter.MyOrderListAdapter;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;
import com.sinata.rwxchina.component_aboutme.contract.MyOrderContract;
import com.sinata.rwxchina.component_aboutme.presenter.MyOrderPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/15
 * @describe：订单列表fragment
 * @modifyRecord:修改记录
 */

public class MyOrderFragment extends BaseFragment implements MyOrderContract.View {
    private ListView listView;
    private MyOrderListAdapter adapter;
    private Map<String, String> params;
    private MyOrderPresenter myOrderPresenter;
    private PageInfo pageInfo;
    private SmartRefreshLayout refreshLayout;

    public static final MyOrderFragment getMyOrderFragment(@Nullable String params) {
        MyOrderFragment orderFragment = new MyOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("indent_state", params);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Bundle bundle =  this.getArguments();
            if (bundle.getString("indent_state") != null) {
                params = new HashMap<>();
                params.put("indent_state", bundle.getString("indent_state"));
            } else
                params = null;


    }


    @Override
    public void onStart() {
        super.onStart();
        myOrderPresenter.getData(params, false);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.myOrder_lv);
        refreshLayout = view.findViewById(R.id.myOrder_srl);
    }

    @Override
    public void setListener() {
      refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
          @Override
          public void onLoadmore(RefreshLayout refreshlayout) {
              if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                  Map<String, String> params = new HashMap<String, String>();
                  params.put("page", pageInfo.getPage() + 1 + "");
                  myOrderPresenter.loadMore(params);
              } else {
                  refreshlayout.finishLoadmoreWithNoMoreData();
              }
          }

          @Override
          public void onRefresh(RefreshLayout refreshlayout) {
               myOrderPresenter.getData(params,true);
          }
      });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        myOrderPresenter = new MyOrderPresenter((BaseActivity) getActivity());
        myOrderPresenter.attachView(this);
    }

    @Override
    public void showView(List<OrderBean> list,boolean isRefresh) {
        if (list!=null) {
            adapter = new MyOrderListAdapter((BaseActivity) getActivity(), list);
            listView.setAdapter(adapter);
        }
        isStopRefresh(isRefresh);
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }
    @Override
    public void showLoadMore(List<OrderBean> list) {
        List<OrderBean> orderBeen = adapter.getList();
        orderBeen.addAll(list);
        adapter.setList(orderBeen);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onDestroy() {
        myOrderPresenter.detachView();
        super.onDestroy();
    }
}
