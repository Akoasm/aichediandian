package com.sinata.rwxchina.basiclib.commonclass.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.commonclass.Bean.Couponbean;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.MyCouponPresenter;
import com.sinata.rwxchina.basiclib.commonclass.adpter.CouponListAdapter;
import com.sinata.rwxchina.basiclib.commonclass.contract.MyCouponContract;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：优惠券fragment
 * @modifyRecord:修改记录
 */

public class MyCouponFragment extends BaseFragment implements MyCouponContract.View {
    private ListView listView;
    private PageInfo pageInfo;
    private SmartRefreshLayout refreshLayout;
    private Map<String, String> params;
    private CouponListAdapter adapter;
    private MyCouponPresenter myCouponPresenter;

    public static final MyCouponFragment getMyCouponFragment(String params) {
        MyCouponFragment myCouponFragment = new MyCouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("coupon_state", params);
        myCouponFragment.setArguments(bundle);
        return myCouponFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        params = new HashMap<>();
        params.put("coupon_state", bundle.getString("coupon_state"));
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mycoupon;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.myCoupon_lv);
        refreshLayout = view.findViewById(R.id.myCoupon_srl);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("page", pageInfo.getPage() + 1 + "");
                    myCouponPresenter.loadMore(params);
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                myCouponPresenter.getData(params, true);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        myCouponPresenter = new MyCouponPresenter((BaseActivity) getActivity());
        myCouponPresenter.attachView(this);
        myCouponPresenter.getData(params,false);
    }

    @Override
    public void showView(List<Couponbean> list, boolean isRefresh) {
          if (list!=null){
              if (adapter!=null){
                  adapter.setList(list);
                  adapter.notifyDataSetChanged();
              }{
                  adapter = new CouponListAdapter((BaseActivity) getActivity(), list);
                  listView.setAdapter(adapter);
                  adapter.notifyDataSetChanged();
              }
          }
          isStopRefresh(isRefresh);
    }
    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(List<Couponbean> list) {
        List<Couponbean> couponbeen = adapter.getList();
        couponbeen.addAll(list);
        adapter.setList(couponbeen);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onDestroy() {
        myCouponPresenter.detachView();
        super.onDestroy();
    }
}
