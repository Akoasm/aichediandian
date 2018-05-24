package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.basic.basiclist.BasicListActivity;
import com.sinata.rwxchina.component_basic.basic.basiclist.adapter.BasicListAdapter;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.ListJumpUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.utils.ShopJumpUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/27
 * @describe：搜索结果
 * @modifyRecord:
 */


public class SearchResultActivity extends BaseActivity {
    private ImageView back;
    private View statusBar;
    private EditText search_et;
    private ImageView search_img;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private String keyword;
    private boolean isRefresh = true;//是否是下拉刷新
    private String icon_label;
    /**
     * 数据集合
     */
    private List<BaseShopInfo> shopInfos;
    /**
     * 当前页数
     */
    private int page = 1;
    private int isMore;//是否还有更多0:没有下一页，1：有下一页

    /**
     * 商铺列表适配器
     */
    private BasicListAdapter adapter;


    @Override
    public void initParms(Bundle params) {
        keyword = params.getString("keyword");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        back = view.findViewById(R.id.home_search_result_back);
        search_et = view.findViewById(R.id.home_search_result_edit);
        search_img = view.findViewById(R.id.home_search_result_image);
        recyclerView = view.findViewById(R.id.home_search_result_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        refreshLayout = view.findViewById(R.id.home_search_result_refresh);
        statusBar = findViewById(R.id.search_result_fakeview);
        search_et.setText(keyword);
        search_et.setSelection(search_et.getText().length());
        adapter=new BasicListAdapter(this, com.sinata.rwxchina.component_basic.R.layout.item_entertainment_list,shopInfos);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,10,getResources().getColor(com.sinata.rwxchina.component_basic.R.color.background)));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到相应模块
                ShopJumpUtils.toJump(SearchResultActivity.this, shopInfos.get(position).getShop_type(), shopInfos.get(position).getShopid(),shopInfos.get(position).getShop_name(),shopInfos.get(position).getM_shop_type_labels());

            }
        });
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        search_img.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if(v.getId() == R.id.home_search_result_back){
            finish();
        }else if (v.getId() == R.id.home_search_result_image){
            keyword = search_et.getText().toString();
            if (TextUtils.isEmpty(keyword)) {
                ToastUtils.showShort("请输入搜索内容");
            } else {
                getList();
            }
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getList();
        setRefreshLayout();
        isSearch();
        setTitleBarView();
    }

    private void getList() {
        Map<String, String> params = new HashMap<>();
        params.put("lng", LocationUtils.getLng(this));
        params.put("lat", LocationUtils.getLat(this));
        params.put("page", page + "");
        params.put("keyword", keyword);
        params.put("pageSize", "10");
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                isMore = pageInfo.getIsMore();
                shopInfos = new Gson().fromJson(result, new TypeToken<List<BaseShopInfo>>() {
                }.getType());

                if (isRefresh) {
                    setScenicList();
                } else {
                    loadMoreData();
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSHOPLIST, params);
    }

    private void isSearch(){
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keyword = search_et.getText().toString();
                    if (keyword == null) {
                        ToastUtils.showShort("请输入搜索内容");
                    } else {
                        getList();
                    }
                    return true;
                }
                return false;
            }
        });
    }
    /**
     * 加载更多
     */
    private void loadMoreData() {
        adapter.addData(shopInfos);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();//结束加载
    }

    /**
     * 设置refreshLayout
     */
    private void setRefreshLayout() {
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
     * 设置店铺列表
     */
    private void setScenicList() {
        adapter.setNewData(shopInfos);
        refreshLayout.finishRefresh();//结束刷新
        if (isMore == 1) {
            refreshLayout.setLoadmoreFinished(false);
        } else {
            refreshLayout.setLoadmoreFinished(true);
        }
        adapter.notifyDataSetChanged();
//        adapter.disableLoadMoreIfNotFullPage();
    }

    /**
     * 加载更多的数据设置
     */
    private void loadMore() {
        if (isMore == 0) {
            //结束加载
            refreshLayout.finishLoadmore();
            //全部加载完成
            refreshLayout.setLoadmoreFinished(true);
            return;
        }
        page++;
        isRefresh = false;
        getList();
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        page = 1;
        isRefresh = true;
        getList();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(SearchResultActivity.this, null);
    }
}
