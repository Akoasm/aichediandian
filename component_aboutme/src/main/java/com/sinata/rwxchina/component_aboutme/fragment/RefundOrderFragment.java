package com.sinata.rwxchina.component_aboutme.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.adapter.RefundListAdapter;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;
import com.sinata.rwxchina.component_aboutme.contract.RefundContract;
import com.sinata.rwxchina.component_aboutme.presenter.RefundPresenter;
import com.sinata.rwxchina.component_aboutme.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/19
 * @describe：退款列表fragment
 * @modifyRecord:修改记录
 */

public class RefundOrderFragment extends BaseFragment implements RefundContract.View {
    private ListView listView;
    private PageInfo pageInfo;
    private SmartRefreshLayout refreshLayout;
    private RefundListAdapter adapter;
    private Map<String, String> params;
    private RefundPresenter refundPresenter;
    private EditText search;

    public static final RefundOrderFragment getRefundOrderFragment(String ismall,@Nullable String indent_state) {
        RefundOrderFragment orderFragment = new RefundOrderFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("is_mall",ismall);
        bundle.putString("indent_state", indent_state);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        getParams(bundle);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_refund;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.refund_lv);
        refreshLayout = view.findViewById(R.id.refund_srl);
        search = view.findViewById(R.id.refund_search_et);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("page", pageInfo.getPage() + 1 + "");
                    refundPresenter.loadMore(params);
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refundPresenter.getData(params, true);
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH||actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())){
                    String keyWord = search.getText().toString().trim();
                    if (!TextUtils.isEmpty(keyWord)) {
                        params.put("key", keyWord);
                        refundPresenter.getData(params, false);
                    }else {
                        params.remove("key");
                        refundPresenter.getData(params, false);
                    }
                }
                return false;
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString().trim())){
                    params.remove("key");
                    refundPresenter.getData(params, false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        refundPresenter = new RefundPresenter((BaseActivity) getActivity());
        refundPresenter.attachView(this);
        refundPresenter.getData(params, false);

    }

    @Override
    public void ShowView(List<RefundOrderBean> list, boolean isRefresh) {
        if (list!=null) {
            adapter = new RefundListAdapter((BaseActivity) getActivity(), list);
            listView.setAdapter(adapter);
        }else {
            ToastUtils.showShort("暂无数据");
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
    public void showLoadMore(List<RefundOrderBean> list) {
        List<RefundOrderBean> orderBeen = adapter.getList();
        orderBeen.addAll(list);
        adapter.setList(orderBeen);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }
    private void getParams(Bundle bundle){
        params = new HashMap<>();
        params.put("is_mall",bundle.getString("is_mall"));
        if (bundle.getString("indent_state") != null) {
            params.put("indent_state", bundle.getString("indent_state"));
        }
    }


    @Override
    public void onDestroy() {
        refundPresenter.detachView();
        super.onDestroy();
    }
}
