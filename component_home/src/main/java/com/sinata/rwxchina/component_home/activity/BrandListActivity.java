package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_home.Bean.BrandBean;
import com.sinata.rwxchina.component_home.Contract.BranListContract;
import com.sinata.rwxchina.component_home.Presenter.BrandListPresenter;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.BrandListRVAdapter;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：品牌专区列表
 * @modifyRecord:修改记录
 */

public class BrandListActivity extends BaseActivity implements BranListContract.View {
    private RecyclerView recyclerView;
    private TextView title;
    private ImageView back;
    private View statusBar;
    private BrandListPresenter brandListPresenter;
    private BrandListRVAdapter adapter;

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
        return R.layout.activity_brand_area_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.brand_area_recycler);
        View titleViwe = view.findViewById(R.id.branList_title);
        title = titleViwe.findViewById(R.id.titleLayout_title_tv);
        back = titleViwe.findViewById(R.id.back);
        statusBar = findViewById(R.id.home_title_fakeview);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        brandListPresenter = new BrandListPresenter(this);
        brandListPresenter.attachView(this);
        brandListPresenter.getData();
        setTitleBarView();
    }

    @Override
    public void showView(List<BrandBean> brandBean) {
        adapter = new BrandListRVAdapter(brandBean, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

    }
    private void setTitle() {
        title.setText("品牌专区");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        brandListPresenter.detachView();
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
        StatusBarUtil.setTranslucentForImageViewInFragment(BrandListActivity.this, null);
    }
}
