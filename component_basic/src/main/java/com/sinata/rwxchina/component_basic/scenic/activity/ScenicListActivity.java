package com.sinata.rwxchina.component_basic.scenic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopListMapActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.scenic.adapter.ScenicAdapter;
import com.sinata.rwxchina.basiclib.commonclass.entity.ScenicEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/11/30
 * @describe 景区列表页面
 * @modifyRecord
 */

public class ScenicListActivity extends BaseActivity {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView scenic_recycler;
    private ScenicAdapter scenicAdapter;
    private List<ScenicEntity> scenic;
    private int isMore;//是否还有更多0:没有下一页，1：有下一页
    private int page=1;//当前页数
    private ApiManager apiManager;
    private boolean isRefresh=true;//是否是下拉刷新
    private EditText search_et;
    private String keyWord;
    private ImageView scenic_list_back,location;
    private RelativeLayout search_relative;
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
        return R.layout.activity_scenic_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refreshLayout=findViewById(R.id.scenic_smartRefresh);
        scenic_recycler=findViewById(R.id.scenic_recycler);
        search_et = view.findViewById(R.id.scenic_search_edit);
        location = view.findViewById(R.id.scenic_list_location);
        search_relative = view.findViewById(R.id.scenic_search_relative);
        scenic_list_back = view.findViewById(R.id.scenic_list_back);
        statusBar = view.findViewById(R.id.scenic_fakeview);
        scenic_recycler.setLayoutManager(new LinearLayoutManager(this));
        scenic=new ArrayList<ScenicEntity>();
        setAdapter();
        setRefreshLayout();

    }

    @Override
    public void setListener() {
        scenic_list_back.setOnClickListener(this);
        location.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle = new Bundle();
        if(v.getId() == R.id.scenic_list_back){
            finish();
        }else if(v.getId() == R.id.scenic_list_location){
            bundle.putBoolean("isScenic",true);
            startActivity(BasicShopListMapActivity.class,bundle);
        }

    }

    @Override
    public void doBusiness(Context mContext) {
        getScenicList();
        setTitleBarView();
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    keyWord = search_et.getText().toString();
                    searchScnicList();
                return false;
            }
        });
    }

    /**
     * 搜索景区列表
     */
    private void searchScnicList(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("page",page+"");
        params.put("keyword",keyWord);
        apiManager  = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                isMore=pageInfo.getIsMore();
                showLog("isMore="+isMore);
                scenic= new Gson().fromJson(result,new TypeToken<List<ScenicEntity>>(){}.getType());
                if (isRefresh){
                    setScenicList();
                }else {
                    loadMoreData();
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSCENIC,params);
    }

    /**
     * 获取景区列表
     */
    private void getScenicList(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("page",page+"");
        apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                isMore=pageInfo.getIsMore();
                showLog("isMore="+isMore);
                scenic= new Gson().fromJson(result,new TypeToken<List<ScenicEntity>>(){}.getType());
                if (isRefresh){
                    setScenicList();
                }else {
                    loadMoreData();
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSCENIC,params);
    }

    /**
     * 设置景区列表
     */
    private void setScenicList(){
        scenicAdapter.setNewData(scenic);
        refreshLayout.finishRefresh();//结束刷新
        if (isMore==1){
            refreshLayout.setLoadmoreFinished(false);
        }else {
            refreshLayout.setLoadmoreFinished(true);
        }
        scenicAdapter.notifyDataSetChanged();
        scenicAdapter.disableLoadMoreIfNotFullPage();
    }

    /**
     * 加载更多
     */
    private void loadMoreData(){
        scenicAdapter.addData(scenic);
        scenicAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();//结束加载
    }

    /**
     * 设置adapter
     */
    private void setAdapter(){
        scenicAdapter=new ScenicAdapter(R.layout.scenic_list_item,scenic,ScenicListActivity.this);
        //当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        scenicAdapter.setPreLoadNumber(1);
        //设置点击监听
        scenicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(ScenicListActivity.this,ScenicActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("scenic", (Serializable) adapter.getData().get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        scenic_recycler.setAdapter(scenicAdapter);
        scenic_recycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,10,getResources().getColor(R.color.background)));

    }

    /**
     * 设置refreshLayout
     */
    private void setRefreshLayout(){
        //是否启用上拉加载功能
        refreshLayout.setEnableLoadmore(true);
        //是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setEnableAutoLoadmore(true);
        //设置加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMore();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
            }
        });
    }

    /**
     * 加载更多的数据设置
     */
    private void loadMore(){
        if (isMore==0){
            //结束加载
            refreshLayout.finishLoadmore();
            //全部加载完成
            refreshLayout.setLoadmoreFinished(true);
            return;
        }
        page++;
        isRefresh=false;
        getScenicList();
    }

    /**
     * 刷新数据
     */
    private void refresh(){
        page=1;
        isRefresh=true;
        scenic=new ArrayList<ScenicEntity>();
        getScenicList();

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
        StatusBarUtil.setTransparentForImageViewInFragment(ScenicListActivity.this, null);
    }
}
