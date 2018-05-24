package com.sinata.rwxchina.component_find.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseFragment;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.component_find.Activity.InformationDetailsActivity;
import com.sinata.rwxchina.component_find.Adapter.InformationsAdapter;
import com.sinata.rwxchina.component_find.Entity.InformationBean;
import com.sinata.rwxchina.component_find.R;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/11/14
 * @describe：
 * @modifyRecord:
 */


public class FindInformationFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private int page = 1,totalpage = 1;

    private InformationBean informationBean;
    private InformationsAdapter informationAdapter;//适配器
    private List<InformationBean> dataBeanList;//列表集合
    private RecyclerView.LayoutManager layoutManager;
    private RefreshLayout refreshLayout;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_information;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        recyclerView = view.findViewById(R.id.information_listview);
        refreshLayout = view.findViewById(R.id.information_smartRefresh);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        informationAdapter = new InformationsAdapter(getContext());
        recyclerView.setAdapter(informationAdapter);

    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
            }
        });


    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getdata();
        informationAdapter.setmOnItemActionListener(new InformationsAdapter.OnItemActionListener() {
            @Override
            public void onItemClickListener(View v, int pos) {
                Intent intent = new Intent(getActivity(), InformationDetailsActivity.class);
                intent.putExtra("news_id",dataBeanList.get(pos).getNews_id());
                intent.putExtra("title",dataBeanList.get(pos).getTitle());
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page + "");
        ApiManager apiManager = new ApiManager((BaseActivity) this.getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String resulte, String method, int code,String msg, PageInfo pageInfo) throws JSONException {
                initdata(resulte);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.INFORMATION, params);
    }

    private void initdata(String result) {
        dataBeanList=  new Gson().fromJson(result,new TypeToken<List<InformationBean>>(){}.getType());
        informationAdapter.setList(dataBeanList);
    }
}
