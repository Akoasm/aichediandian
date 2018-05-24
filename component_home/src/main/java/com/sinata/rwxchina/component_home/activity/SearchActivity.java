package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.nex3z.flowlayout.FlowLayout;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.HotSearchAdapter;
import com.sinata.rwxchina.component_home.entity.HotSearchEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/27
 * @describe：首页搜索页面
 * @modifyRecord:
 */


public class SearchActivity extends BaseActivity {
    //搜索框
    private EditText search_et;
    private ImageView search_img;
    //取消搜索
    private TextView cancle_search;
    //热门搜索
    private FlowLayout hot_flow;
    //历史搜索
    private RecyclerView history_recycler;
    //清空历史
    private ImageView clear_history;
    //是否清空历史pop
    private PopupWindow popup_clear;
    private View view;
    //pop中确定、取消按钮
    private RelativeLayout pop_confirm, pop_cancle;
    //pop中内容
    private TextView pop_content;
    //热门搜索实体类
    private HotSearchEntity hotSearchEntity;
    //热门搜索适配器
    private HotSearchAdapter hotSearchAdapter;

    private List<HotSearchEntity> hotList;
    private String keyword;//搜索内容，默认为空

    private View statusBar;

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
        return R.layout.activity_search;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        search_et = view.findViewById(R.id.home_search_edit);
        search_img = view.findViewById(R.id.home_search_image);
        cancle_search = view.findViewById(R.id.home_search_cancle);
        hot_flow = view.findViewById(R.id.home_search_flowlayout);
        history_recycler = view.findViewById(R.id.home_search_history_recycler);
        clear_history = view.findViewById(R.id.home_search_clear);
        pop_confirm = view.findViewById(R.id.popup_call_callup);
        pop_cancle = view.findViewById(R.id.popup_call_cancle);
        pop_content = view.findViewById(R.id.popup_phoneNum);
        statusBar = findViewById(R.id.search_fakeview);
    }

    @Override
    public void setListener() {
        cancle_search.setOnClickListener(this);
        clear_history.setOnClickListener(this);
        search_img.setOnClickListener(this);
//        pop_confirm.setOnClickListener(this);
//        pop_cancle.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.home_search_cancle) {
            finish();
        } else if (i == R.id.home_search_clear) {
            if (popup_clear.isShowing()) {
                popup_clear.dismiss();
            } else {
                popup_clear.showAtLocation(clear_history, Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            setPop();
        } else if (i == R.id.popup_call_callup) {

        } else if (i == R.id.popup_call_cancle) {
            popup_clear.dismiss();
        }else if(i == R.id.home_search_image){
            keyword = search_et.getText().toString();
            if (TextUtils.isEmpty(keyword)) {
                ToastUtils.showShort("搜索内容不能为空");
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("keyword", keyword);
                startActivity(SearchResultActivity.class, bundle);
            }
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setHot();
        setTitleBarView();
    }

    /**
     * 热门搜索
     */
    private void setHot() {
        Map<String, String> params = new HashMap<>();
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                hotList = gson.fromJson(result, new TypeToken<List<HotSearchEntity>>() {
                }.getType());
                setHotData();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.HOTSEARCH, params);
    }


    private void setHistory() {


    }

    private void setHotData() {
        for (int i = 0; i < hotList.size(); i++) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_home_search_recycler,null);
            textView.setText(hotList.get(i).getKeyword());
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("keyword", hotList.get(finalI).getKeyword());
                    startActivity(SearchResultActivity.class, bundle);
                }
            });
            hot_flow.addView(textView);
        }
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keyword = search_et.getText().toString();
                    if (TextUtils.isEmpty(keyword)) {
                        ToastUtils.showShort("搜索内容不能为空");
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("keyword", keyword);
                        startActivity(SearchResultActivity.class, bundle);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setPop() {
        if (popup_clear == null) {
            view = LayoutInflater.from(this).inflate(R.layout.popup_callup, null);
            popup_clear = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            // 点击外部消失
            popup_clear.setOutsideTouchable(true);
            popup_clear.setBackgroundDrawable(new BitmapDrawable());
            popup_clear.setFocusable(true);
            // 设置SelectPicPopupWindow弹出窗体动画效果
            popup_clear.setAnimationStyle(R.style.PopupAnimation);
        }


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
        StatusBarUtil.setTranslucentForImageViewInFragment(SearchActivity.this, null);
    }
}
