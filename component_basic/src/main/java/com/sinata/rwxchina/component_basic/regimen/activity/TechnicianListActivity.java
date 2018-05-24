package com.sinata.rwxchina.component_basic.regimen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerGridItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.regimen.adapter.TechnicianListAdapter;
import com.sinata.rwxchina.component_basic.regimen.entity.TechnicianEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 技师列表页
 * @modifyRecord
 */

public class TechnicianListActivity extends BaseActivity {
    private ImageView back;
    private TextView title;
    private View statusBar;
    private RecyclerView recyclerView;
    private TechnicianListAdapter adapter;
    private List<TechnicianEntity> technicians;
    //    private String shopId;
    private BaseShopInfo shopInfo;
    private String shop;

    @Override
    public void initParms(Bundle parms) {
        shop = parms.getString("shop_info");
        if (!TextUtils.isEmpty(shop)) {
            shopInfo = new Gson().fromJson(shop, BaseShopInfo.class);
            LogUtils.e("shopinfo_msg" + shopInfo);
        }
//        shopId = parms.getString("shopId");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_health_technician_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        back = findViewById(R.id.food_comment_back);
        title = findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("技师");
        recyclerView = findViewById(R.id.health_technician_list);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id = v.getId();
        if (id == R.id.food_comment_back) {
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getTechnicianList();
        setTitleBarView();
    }

    private void getTechnicianList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("shopid", shopInfo.getShopid());
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                technicians = new Gson().fromJson(result, new TypeToken<List<TechnicianEntity>>() {
                }.getType());
                setAdapter();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSHOPMEMBER, params);
    }

    private void setAdapter() {
        adapter = new TechnicianListAdapter(TechnicianListActivity.this, R.layout.item_health_technician_list, technicians);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent technician = new Intent(TechnicianListActivity.this, TechnicianActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shop", shop);
                bundle.putString("teamsonid", technicians.get(position).getTeamsonid());
                technician.putExtras(bundle);
                startActivity(technician);
            }
        });
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView() {
        ImmersionUtils.setListImmersion(getWindow(), this, statusBar);
    }

    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTranslucentForImageViewInFragment(TechnicianListActivity.this, null);
    }
}
