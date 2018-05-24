package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;

import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.IconMoreAdapter;
import com.sinata.rwxchina.component_home.adapter.IconMoreDetailsAdapter;
import com.sinata.rwxchina.component_home.entity.HotSearchEntity;
import com.sinata.rwxchina.component_home.entity.IconMoreEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：首页更多
 * @modifyRecord:
 */


public class IconMoreAcitivity extends BaseActivity {
    private IconMoreEntity iconMoreEntity;
    private ImageView back;
    private TextView title;
    private View statusBar;
    private RecyclerView recycler;
    private IconMoreAdapter adapter;
    private List<IconMoreEntity> list;

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
        return R.layout.activity_icon_more;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        back = view.findViewById(R.id.food_comment_back);
        title = view.findViewById(R.id.food_comment_title_tv);
        recycler = view.findViewById(R.id.icon_more_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("全部服务");
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if (v.getId() == R.id.food_comment_back) {
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getData();
        setTitleBarView();
    }

    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("cityid", "1");
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                list = gson.fromJson(result, new TypeToken<List<IconMoreEntity>>() {}.getType());
                LogUtils.e("list============"+list);
                setAdapter();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.MOREICON, params);
    }

    private void setAdapter() {
        adapter = new IconMoreAdapter(this);
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,10,getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));
        adapter.setList(list);
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
        StatusBarUtil.setTranslucentForImageViewInFragment(IconMoreAcitivity.this, null);
    }
}
