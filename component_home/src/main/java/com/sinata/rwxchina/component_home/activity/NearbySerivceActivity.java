package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicmap.BasicShopListMapActivity;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.NearbyServiceAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zy
 * @detetime:2018/1/5
 * @describe：周边查询
 * @modifyRecord:修改记录
 */

public class NearbySerivceActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener {
    private LinearLayout mListScreeningSyntheticalLinear;
    private LinearLayout mListScreeningSearchLinear;
    private SmartRefreshLayout mNearbyServiceSrl;
    private ListView mNearbyServiceLv;
    private String keyWord;
    private NearbyServiceAdapter adapter;
    private TextView title, map;
    private ImageView back;
    private View statusBar, background;
    private PopupWindow popupWindow;
    private View fview, popItem, popSearch;
    private RelativeLayout mPopNearbyServiceFilter1000Rl;
    private RelativeLayout mPopNearbyServiceFilter2000Rl;
    private RelativeLayout mPopNearbyServiceFilter5000Rl;
    private RelativeLayout mPopNearbyServiceFilterAllTownRl;
    private EditText mNearbySearchEt;
    private TextView mListScreeningDistanceTv;
    private TextView mListScreeningSearchTv;
    private List<PoiItem> list;
    private int distance = 10000;
    private int page = 1;
    private int pageCount = 0;

    @Override
    public void initParms(Bundle params) {
        keyWord = params.getString("keyWord");
        setList(page, distance, keyWord);
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_nearbyservice;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        popSearch = LayoutInflater.from(this).inflate(R.layout.pop_nearbyservice_search, null);
        mListScreeningSyntheticalLinear = findViewById(R.id.list_screening_synthetical_linear);
        mListScreeningSearchLinear = findViewById(R.id.list_screening_search_linear);
        mNearbyServiceSrl = findViewById(R.id.nearbyService_srl);
        mNearbyServiceLv = findViewById(R.id.nearbyService_lv);
        fview = view.findViewById(R.id.filter_ll);
        title = findViewById(R.id.titleLayout_title_tv);
        back = findViewById(R.id.back);
        map = findViewById(R.id.rightIcon_tv);
        statusBar = findViewById(R.id.home_title_fakeview);
        popItem = LayoutInflater.from(this).inflate(R.layout.pop_nearbysevice_distance, null);
        mPopNearbyServiceFilter1000Rl = popItem.findViewById(R.id.popNearbyServiceFilter_1000_rl);
        mPopNearbyServiceFilter2000Rl = popItem.findViewById(R.id.popNearbyServiceFilter_2000_rl);
        mPopNearbyServiceFilter5000Rl = popItem.findViewById(R.id.popNearbyServiceFilter_5000_rl);
        mPopNearbyServiceFilterAllTownRl = popItem.findViewById(R.id.popNearbyServiceFilter_allTown_rl);
        mNearbySearchEt = popSearch.findViewById(R.id.nearby_search_et);
        mListScreeningDistanceTv = findViewById(R.id.list_screening_synthetical_tv);
        mListScreeningSearchTv = findViewById(R.id.list_screening_search_tv);
        background = findViewById(R.id.nearbyService_background);
    }

    @Override
    public void setListener() {
        mNearbyServiceSrl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (pageCount > page)
                    setList(page, distance, keyWord);
                else {
                    ToastUtils.showShort("没有更多了");
                    refreshlayout.finishLoadmore();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                setList(page, distance, keyWord);
            }
        });
        map.setOnClickListener(this);
        mListScreeningSyntheticalLinear.setOnClickListener(this);
        mListScreeningSearchLinear.setOnClickListener(this);
        mPopNearbyServiceFilter1000Rl.setOnClickListener(this);
        mPopNearbyServiceFilter2000Rl.setOnClickListener(this);
        mPopNearbyServiceFilter5000Rl.setOnClickListener(this);
        mPopNearbyServiceFilterAllTownRl.setOnClickListener(this);
        mNearbySearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    String keyWord = mNearbySearchEt.getText().toString().trim();
                    if (!TextUtils.isEmpty(keyWord)) {
                        setList(page=1, distance, keyWord);
                        popupWindow.dismiss();
                    } else {
                        filterData(distance);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle = new Bundle();
        int i = v.getId();
        if (i == R.id.list_screening_synthetical_linear) {
            showPop(popItem);
        } else if (i == R.id.list_screening_search_linear) {
            showPop(popSearch);
        } else if (i == R.id.popNearbyServiceFilter_1000_rl) {
            filterData(1000);
        } else if (i == R.id.popNearbyServiceFilter_2000_rl) {
            filterData(2000);
        } else if (i == R.id.popNearbyServiceFilter_5000_rl) {
            filterData(5000);
        } else if (i == R.id.popNearbyServiceFilter_allTown_rl) {
            filterData(0);
        } else if (i == R.id.rightIcon_tv) {
            bundle.putBoolean("isNearbyService", true);
            bundle.putParcelableArrayList("nearbyService", (ArrayList<? extends Parcelable>) list);
            if (list != null)
                startActivity(BasicShopListMapActivity.class, bundle);
        }
    }

    private void showPop(View pop) {
        popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                background.setVisibility(View.GONE);
            }
        });
        background.setVisibility(View.VISIBLE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(fview);
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        setTitleBarView();

    }

    private void filterData(int distance) {
        this.distance = distance;
        setList(page = 1, distance, keyWord);
        if (distance > 0)
            mListScreeningDistanceTv.setText(distance + "米");
        else
            mListScreeningDistanceTv.setText("全城");
        popupWindow.dismiss();
    }

    private void setList(int page, int distance, String keyWord) {
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", LocationUtils.getCity(this));
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this, query);
        if (distance > 0)
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(Double.parseDouble(LocationUtils.getLat(this)), Double.parseDouble(LocationUtils.getLng(this))), distance));
        else
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(Double.parseDouble(LocationUtils.getLat(this)), Double.parseDouble(LocationUtils.getLng(this))), 50000));
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    private void setTitle() {
        title.setText(keyWord);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        map.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_list_location);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        map.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        pageCount = poiResult.getPageCount();
        List<PoiItem> list = new ArrayList<>();
        if (page > 1) {
            if (page < poiResult.getPageCount()) {
                list = adapter.getList();
                list.addAll(poiResult.getPois());
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            } else {
                if (mNearbyServiceSrl.isLoading()) {
                    ToastUtils.showShort("没有更多了");
                } else {
                    list = poiResult.getPois();
                    adapter = new NearbyServiceAdapter(poiResult.getPois(), NearbySerivceActivity.this);
                    mNearbyServiceLv.setAdapter(adapter);
                }
            }

        } else {
            if (adapter == null) {
                adapter = new NearbyServiceAdapter(poiResult.getPois(), NearbySerivceActivity.this);
                mNearbyServiceLv.setAdapter(adapter);
            } else {
                adapter.setList(poiResult.getPois());
                adapter.notifyDataSetChanged();
            }
            if (mNearbyServiceSrl.isRefreshing())
                mNearbyServiceSrl.finishRefresh();
            list = poiResult.getPois();
        }
        mNearbyServiceSrl.finishLoadmore();
        this.list = list;
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

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
        StatusBarUtil.setTranslucentForImageViewInFragment(NearbySerivceActivity.this, null);
    }
}
