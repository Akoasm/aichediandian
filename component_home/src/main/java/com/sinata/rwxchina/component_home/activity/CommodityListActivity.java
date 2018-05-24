package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_home.Bean.BrandBean;
import com.sinata.rwxchina.component_home.Contract.CommodityListContract;
import com.sinata.rwxchina.component_home.Presenter.CommodityListPresenter;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.CommodityListRVAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：品牌商品列表
 * @modifyRecord:修改记录
 */

public class CommodityListActivity extends BaseActivity implements CommodityListContract.View {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private TextView title;
    private ImageView back;
    private LinearLayout mListScreeningSyntheticalLinear;
    private TextView mListScreeningSyntheticalTv;
    private LinearLayout mListScreeningFillterLinear;
    private TextView mListScreeningFillterTv;
    private LinearLayout mListScreeningSearchLinear;
    private TextView mListScreeningSearchTv;
    private CommodityListPresenter commodityListPresenter;
    private CommodityListRVAdapter adapter;
    private Map<String, String> params;
    private BrandBean brand;
    private PageInfo pageInfo;
    private EditText mPopSearchEdit;
    private ImageView mPopSearchImg;
    private RelativeLayout mItemPopSyntheticalRl;
    private TextView mItemPopSyntheticalTv;
    private ImageView mItemPopSyntheticalIv;
    private RelativeLayout mItemPopSalesRl;
    private TextView mItemPopSalesTv;
    private ImageView mItemPopSalesIv;
    private RelativeLayout mItemPopPriceHighToLowRl;
    private TextView mItemPopPriceHighToLowTv;
    private ImageView mItemPopPriceHighToLowIv;
    private RelativeLayout mItemPopPriceLowToHighRl;
    private TextView mItemPopPriceLowToHighTv;
    private ImageView mItemPopPriceLowToHighIv;
    private EditText mPopLowPriceEdit;
    private EditText mPopHighPriceEdit;
    private View fview, titleViwe, compositeItem, search, priceFiliter;
    private PopupWindow pop;
    private boolean isHome;
    private String title_txt;
    private View statusBar,background;


    @Override
    public void initParms(Bundle prams) {
        params = new HashMap<>();
        params.put("is_mallgoods", "1");
        isHome = prams.getBoolean("isHome", false);
        if (isHome) {
            params.put("goods_type", prams.getString("goods_type"));
            title_txt = prams.getString("title");
        } else {
            brand = (BrandBean) prams.getSerializable("brand");
            params.put("brand_id", brand.getBrand_id());
        }
        setSetStatusBar(true);

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_commoditylist;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.brand_area_list_recycler);
        refreshLayout = view.findViewById(R.id.activity_list_refresh);
        titleViwe = view.findViewById(R.id.commodityList_title);
        title = titleViwe.findViewById(R.id.food_comment_title_tv);
        back = titleViwe.findViewById(R.id.food_comment_back);
        View filter = view.findViewById(R.id.commodity_filter);
        fview = filter;
        mListScreeningSyntheticalLinear = filter.findViewById(R.id.list_screening_synthetical_linear);
        mListScreeningSyntheticalTv = filter.findViewById(R.id.list_screening_synthetical_tv);
        mListScreeningFillterLinear = filter.findViewById(R.id.list_screening_fillter_linear);
        mListScreeningFillterTv = filter.findViewById(R.id.list_screening_fillter_tv);
        mListScreeningSearchLinear = filter.findViewById(R.id.list_screening_search_linear);
        mListScreeningSearchTv = filter.findViewById(R.id.list_screening_search_tv);
        compositeItem = LayoutInflater.from(this).inflate(R.layout.pop_complex, null);
        search = LayoutInflater.from(this).inflate(R.layout.pop_search, null);
        priceFiliter = LayoutInflater.from(this).inflate(R.layout.pop_price, null);
        mPopSearchEdit = search.findViewById(R.id.pop_search_edit);
        mPopSearchImg = search.findViewById(R.id.pop_search_image);
        mItemPopSyntheticalRl = compositeItem.findViewById(R.id.item_pop_synthetical_rl);
        mItemPopSyntheticalTv = compositeItem.findViewById(R.id.item_pop_synthetical_tv);
        mItemPopSyntheticalIv = compositeItem.findViewById(R.id.item_pop_synthetical_iv);
        mItemPopSalesRl = compositeItem.findViewById(R.id.item_pop_Sales_rl);
        mItemPopSalesTv = compositeItem.findViewById(R.id.item_pop_Sales_tv);
        mItemPopSalesIv = compositeItem.findViewById(R.id.item_pop_Sales_iv);
        mItemPopPriceHighToLowRl = compositeItem.findViewById(R.id.item_pop_priceHighToLow_rl);
        mItemPopPriceHighToLowTv = compositeItem.findViewById(R.id.item_pop_priceHighToLow_tv);
        mItemPopPriceHighToLowIv = compositeItem.findViewById(R.id.item_pop_priceHighToLow_iv);
        mItemPopPriceLowToHighRl = compositeItem.findViewById(R.id.item_pop_priceLowToHigh_rl);
        mItemPopPriceLowToHighTv = compositeItem.findViewById(R.id.item_pop_priceLowToHigh_tv);
        mItemPopPriceLowToHighIv = compositeItem.findViewById(R.id.item_pop_priceLowToHigh_iv);
        mPopLowPriceEdit = priceFiliter.findViewById(R.id.pop_lowPrice_edit);
        mPopHighPriceEdit = priceFiliter.findViewById(R.id.pop_highPrice_edit);
        statusBar = findViewById(R.id.normal_fakeview);
        background = findViewById(R.id.nearbyService_background);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    params.put("page", pageInfo.getPage()+1 +"");
                    commodityListPresenter.loadMore(params);
                } else {
                    refreshLayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                commodityListPresenter.getData(params, true);
            }
        });
        mListScreeningSyntheticalLinear.setOnClickListener(this);
        mListScreeningFillterLinear.setOnClickListener(this);
        mListScreeningSearchLinear.setOnClickListener(this);
        mItemPopSyntheticalRl.setOnClickListener(this);
        mItemPopSalesRl.setOnClickListener(this);
        mItemPopPriceHighToLowRl.setOnClickListener(this);
        mItemPopPriceLowToHighRl.setOnClickListener(this);
        mPopSearchImg.setOnClickListener(this);
        mPopSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    String keyWord = mPopSearchEdit.getText().toString().trim();
                    if (!TextUtils.isEmpty(keyWord)) {
                        params.put("keyword", keyWord);
                        checkSearchFilter();
                        commodityListPresenter.getData(params, false);
                        pop.dismiss();
                    } else {
                        params.remove("keyword");
                        checkSearchFilter();
                        commodityListPresenter.getData(params, false);
                        pop.dismiss();
                    }

                }
                return false;
            }
        });
        mPopHighPriceEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    String highPrice = mPopHighPriceEdit.getText().toString().trim();
                    String lowPrice = mPopLowPriceEdit.getText().toString().trim();
                    if (!TextUtils.isEmpty(lowPrice) && TextUtils.isEmpty(highPrice)) {
                        ToastUtils.showShort("请输入最高价");
                    } else {
                        params.put("money_min", lowPrice);
                        params.put("money_max", highPrice);
                        if (TextUtils.isEmpty(lowPrice)) {
                            params.remove("money_min");
                        } else if (TextUtils.isEmpty(highPrice)) {
                            params.remove("money_max");
                        }
                        checkPriceFilter();
                        commodityListPresenter.getData(params, false);
                        pop.dismiss();
                    }
                }
                return false;
            }
        });

    }

    private void checkSearchFilter() {
        if (params.get("msort") != null)
            params.remove("msort");
        else if (params.get("money_min") != null)
            params.remove("money_min");
        else if (params.get("money_max") != null)
            params.remove("money_max");
    }

    private void checkPriceFilter() {
        if (params.get("msort") != null)
            params.remove("msort");
        else if (params.get("keyword") != null)
            params.remove("keyword");
    }

    private void checkSortFilter() {
        if (params.get("keyword") != null)
            params.remove("keyword");
        else if (params.get("money_min") != null)
            params.remove("money_min");
        else if (params.get("money_max") != null)
            params.remove("money_max");
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.list_screening_synthetical_linear) {
            initPop(compositeItem, mListScreeningSyntheticalTv);
            changeFileterNormal(mListScreeningFillterTv, mListScreeningSearchTv);
        } else if (i == R.id.list_screening_fillter_linear) {
            initPop(priceFiliter, mListScreeningFillterTv);
            changeFileterNormal(mListScreeningSyntheticalTv, mListScreeningSearchTv);
        } else if (i == R.id.list_screening_search_linear) {
            initPop(search, mListScreeningSearchTv);
            changeFileterNormal(mListScreeningSyntheticalTv, mListScreeningFillterTv);
        } else if (i == R.id.item_pop_synthetical_rl) {
            filterData("msort", "0");
        } else if (i == R.id.item_pop_Sales_rl) {
            filterData("msort", "1");
        } else if (i == R.id.item_pop_priceHighToLow_rl) {
            filterData("msort", "2");
        } else if (i == R.id.item_pop_priceLowToHigh_rl) {
            filterData("msort", "3");
        }else if(i == R.id.pop_search_image){
            String keyWord = mPopSearchEdit.getText().toString().trim();
            if (!TextUtils.isEmpty(keyWord)) {
                params.put("keyword", keyWord);
                checkSearchFilter();
                commodityListPresenter.getData(params, false);
                pop.dismiss();
            } else {
                params.remove("keyword");
                checkSearchFilter();
                commodityListPresenter.getData(params, false);
                pop.dismiss();
            }
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        commodityListPresenter = new CommodityListPresenter(this);
        commodityListPresenter.attachView(this);
        commodityListPresenter.getData(params, false);
        setTitleBarView();
    }


    private void setTitle() {
        if (isHome)
            title.setText(title_txt);
        else
            title.setText(brand.getBrand_name());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showView(List<CommodityBean> commodityListBean) {
        if (commodityListBean!=null) {
            adapter = new CommodityListRVAdapter(commodityListBean, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void stopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(List<CommodityBean> commodityListBean) {
        List<CommodityBean> list = adapter.getListBeen();
        list.addAll(commodityListBean);
        adapter.setListBeen(list);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    private void initPop(View view, TextView textView) {
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                background.setVisibility(View.GONE);
            }
        });
        changeFilter(textView);
        showPop();
    }

    private void changeFilter(TextView textView) {
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorOrange1));
    }

    private void changeFileterNormal(TextView textView, TextView textView1) {
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorGray));
        textView1.setTextColor(ContextCompat.getColor(this, R.color.colorGray));
    }

    private void filterData(String paramsName, String param) {
        params.put(paramsName, param);
        checkSortFilter();
        switch (param) {
            case "0":
                mItemPopSyntheticalIv.setVisibility(View.VISIBLE);
                mItemPopSalesIv.setVisibility(View.GONE);
                mItemPopPriceHighToLowIv.setVisibility(View.GONE);
                mItemPopPriceLowToHighIv.setVisibility(View.GONE);
                break;
            case "1":
                mItemPopSyntheticalIv.setVisibility(View.GONE);
                mItemPopSalesIv.setVisibility(View.VISIBLE);
                mItemPopPriceHighToLowIv.setVisibility(View.GONE);
                mItemPopPriceLowToHighIv.setVisibility(View.GONE);
                break;
            case "2":
                mItemPopSyntheticalIv.setVisibility(View.GONE);
                mItemPopSalesIv.setVisibility(View.GONE);
                mItemPopPriceHighToLowIv.setVisibility(View.VISIBLE);
                mItemPopPriceLowToHighIv.setVisibility(View.GONE);
                break;
            case "3":
                mItemPopSyntheticalIv.setVisibility(View.GONE);
                mItemPopSalesIv.setVisibility(View.GONE);
                mItemPopPriceHighToLowIv.setVisibility(View.GONE);
                mItemPopPriceLowToHighIv.setVisibility(View.VISIBLE);
                break;
        }
        commodityListPresenter.getData(params, false);
        pop.dismiss();
    }

    public void showPop() {
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(fview);
        background.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        commodityListPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(CommodityListActivity.this, null);
    }
}
