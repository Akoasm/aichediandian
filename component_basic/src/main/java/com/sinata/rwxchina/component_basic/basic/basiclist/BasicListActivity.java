package com.sinata.rwxchina.component_basic.basic.basiclist;

import android.content.Context;
import android.os.Bundle;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopListMapActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basiclist.adapter.BasicListAdapter;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.ClassfiyEntity;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.EventEnity;
import com.sinata.rwxchina.component_basic.basic.basiclist.entity.SortEntity;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.ClassifyUtils;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.ListJumpUtils;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.SearchUtils;
import com.sinata.rwxchina.component_basic.basic.basiclist.utils.SortUtils;
import com.sinata.rwxchina.component_basic.regimen.activity.TechnicianActivity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/20
 * @describe 列表页
 * @modifyRecord
 */

public class BasicListActivity extends BaseActivity {
    /**下拉刷新上拉加载*/
    private SmartRefreshLayout refreshLayout;
    private RecyclerView listRecycler;
    private View topline;
    /**商铺列表适配器*/
    private BasicListAdapter adapter;
    /**筛选栏*/
    private LinearLayout screenOne,screenTwo,screenThree;
    private TextView screenOneTv,screenTwoTv,screenThreeTv;
    /**数据集合*/
    private List<BaseShopInfo> shopInfos;
    private String shopInfo;
    private String shop_type;
    private String shop_type_labels;
    private String icon_label;
    private String title;
    /**title*/
    private ImageView back,map;
    private TextView titleTv;
    private View statusBar;
    /**当前页数*/
    private int page=1;
    /**智能排序字段*/
    private String sort="near";//comp -综合排序、near -离我最近 、order -订单最多、evaluate -评价最好
    private int isMore;//是否还有更多0:没有下一页，1：有下一页
    private boolean isRefresh=true;//是否是下拉刷新
    private String keyword="";//搜索内容，默认为空
    /**分类集合*/
    private List<ClassfiyEntity> classfiyEntities;
    @Override
    public void initParms(Bundle parms) {
        shop_type=parms.getString("shopType");
        shop_type_labels=parms.getString("shopTypeLabels");
        icon_label=parms.getString("iconLabel");
        title=parms.getString("title");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_general_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        refreshLayout=findViewById(R.id.activity_list_refresh);
        screenOne=findViewById(R.id.list_screening_synthetical_linear);
        screenTwo=findViewById(R.id.list_screening_fillter_linear);
        screenThree=findViewById(R.id.list_screening_search_linear);
        screenOneTv=findViewById(R.id.list_screening_synthetical_tv);
        screenTwoTv=findViewById(R.id.list_screening_fillter_tv);
        screenThreeTv=findViewById(R.id.list_screening_search_tv);
        topline=findViewById(R.id.top_line);
        statusBar = findViewById(R.id.normal_fakeview);
        listRecycler=findViewById(R.id.brand_area_list_recycler);
        listRecycler.setLayoutManager(new LinearLayoutManager(this));
        shopInfos=new ArrayList<BaseShopInfo>();
        adapter=new BasicListAdapter(this,R.layout.item_entertainment_list,shopInfos);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到相应模块
                BaseShopInfo shopInfo= (BaseShopInfo) adapter.getData().get(position);
                ListJumpUtils.toJump(BasicListActivity.this,icon_label,shopInfo.getShopid(),shopInfo.getShop_name());
            }
        });
        listRecycler.setAdapter(adapter);
        listRecycler.addItemDecoration(new DividerRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL,10,getResources().getColor(R.color.background)));
        back=findViewById(R.id.basic_list_title_back);
        titleTv=findViewById(R.id.basic_list_title_tv);
        titleTv.setText(title);
        classfiyEntities=new ArrayList<>();
        map = findViewById(R.id.basic_list_title_location);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        screenOne.setOnClickListener(this);
        screenTwo.setOnClickListener(this);
        screenThree.setOnClickListener(this);
        map.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.basic_list_title_back){
            finish();
        }else if (id==R.id.list_screening_synthetical_linear){
            screenOneTv.setTextColor(getResources().getColor(R.color.colorOrange));
            screenTwoTv.setTextColor(getResources().getColor(R.color.text_hint));
            screenThreeTv.setTextColor(getResources().getColor(R.color.text_hint));
            SortUtils.setSort(BasicListActivity.this,topline,sort);
        } else if (id==R.id.list_screening_fillter_linear){
            screenOneTv.setTextColor(getResources().getColor(R.color.text_hint));
            screenTwoTv.setTextColor(getResources().getColor(R.color.colorOrange));
            screenThreeTv.setTextColor(getResources().getColor(R.color.text_hint));
            ClassifyUtils.setClassify(BasicListActivity.this,topline,classfiyEntities,shop_type_labels);
        }else if (id==R.id.list_screening_search_linear){
            screenOneTv.setTextColor(getResources().getColor(R.color.text_hint));
            screenTwoTv.setTextColor(getResources().getColor(R.color.text_hint));
            screenThreeTv.setTextColor(getResources().getColor(R.color.colorOrange));
            SearchUtils.setSearch(BasicListActivity.this,topline);
        }else if (id == R.id.basic_list_title_location){
            Bundle bundle = new Bundle();
            bundle.putString("shopType",shop_type);
            startActivity(BasicShopListMapActivity.class,bundle);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setScreen(icon_label);
        setRefreshLayout();
        getList();
        setTitleBarView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventEnity enity){
        switch (enity.getType()){
            case "sort":
                setSort(enity);
                break;
            case "classfiy":
                setClassfiy(enity);
                break;
            case "search":
                setSearch(enity);
                break;
            default:
                break;
        }
    }

    /**
     * 设置选择智能排序
     * @param enity
     */
    private void setSort(EventEnity enity){
        isRefresh=true;
        SortEntity sortentity=new Gson().fromJson(enity.getContent(),SortEntity.class);
        sort=sortentity.getId();
        page=1;
        getList();
    }

    /**
     * 设置选择分类筛选
     * @param enity
     */
    private void setClassfiy(EventEnity enity){
        isRefresh=true;
        ClassfiyEntity classfiyEntity=new Gson().fromJson(enity.getContent(),ClassfiyEntity.class);
        shop_type_labels=classfiyEntity.getLabel_id();
        page=1;
        getList();
    }

    /**
     * 设置搜索筛选
     * @param enity
     */
    private void setSearch(EventEnity enity){
        isRefresh=true;
        keyword=enity.getContent();
        page=1;
        getList();
    }

    /**
     * 获取店铺列表，默认离我最近
     */
    private void getList(){
        Map<String,String> params=new HashMap<>();
        params.put("shop_type",shop_type);
        params.put("shop_type_labels",shop_type_labels);
        params.put("lng", LocationUtils.getLng(this));
        params.put("lat",LocationUtils.getLat(this));
        params.put("sort",sort);
        params.put("page",page+"");
        params.put("keyword",keyword);
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                shopInfo = result;
                isMore=pageInfo.getIsMore();
                shopInfos=new Gson().fromJson(result,new TypeToken<List<BaseShopInfo>>(){}.getType());
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
        apiManager.post(HttpPath.GETSHOPLIST,params);
    }

    /**
     * 获取店铺分类
     */
    private void getClassfiy(){
        Map<String,String> params=new HashMap<>();
        params.put("shop_type",shop_type);
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                classfiyEntities=new Gson().fromJson(result,new TypeToken<List<ClassfiyEntity>>(){}.getType());
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETSHOPLABELLIST,params);
    }

    /**
     * 设置选择栏
     * @param shop_type
     */
    private void setScreen(String shop_type){
        switch (shop_type){
            case "jiudian"://酒店
                screenOneTv.setText("智能排序");
                screenTwoTv.setText("分类筛选");
                screenThreeTv.setText("智能搜索");
                getClassfiy();
                break;
            case "meishi"://美食
                screenOneTv.setText("智能排序");
                screenTwoTv.setText("分类筛选");
                screenThreeTv.setText("智能搜索");
                getClassfiy();
                break;
            case "ktv"://KTV
                screenOneTv.setText("智能排序");
                screenThreeTv.setText("智能搜索");
                screenTwo.setVisibility(View.GONE);
                break;
            case "yangsheng"://养生
                screenOneTv.setText("智能排序");
                screenTwoTv.setText("分类筛选");
                screenThreeTv.setText("智能搜索");
                getClassfiy();
                break;
            default:
                break;
        }
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
     * 设置店铺列表
     */
    private void setScenicList(){
        adapter.setNewData(shopInfos);
        refreshLayout.finishRefresh();//结束刷新
        if (isMore==1){
            refreshLayout.setLoadmoreFinished(false);
        }else {
            refreshLayout.setLoadmoreFinished(true);
        }
        adapter.notifyDataSetChanged();
//        adapter.disableLoadMoreIfNotFullPage();
    }

    /**
     * 加载更多
     */
    private void loadMoreData(){
        adapter.addData(shopInfos);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();//结束加载
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
        getList();
    }

    /**
     * 刷新数据
     */
    private void refresh(){
        page=1;
        isRefresh=true;
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
        StatusBarUtil.setTransparentForImageViewInFragment(BasicListActivity.this, null);
    }
}