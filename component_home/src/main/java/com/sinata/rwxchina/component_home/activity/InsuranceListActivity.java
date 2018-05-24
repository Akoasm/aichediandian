package com.sinata.rwxchina.component_home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.imageUtils.ImageUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerGridItemDecoration;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.component_home.R;
import com.sinata.rwxchina.component_home.adapter.InsuranceListAdapter;
import com.sinata.rwxchina.component_home.entity.InsuanceListEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2017/12/26
 * @describe：保险列表
 * @modifyRecord:
 */


public class InsuranceListActivity extends BaseActivity {
    //实体类
    private InsuanceListEntity insuanceEntity;
    private TextView title;
    private ImageView back,bannerImg;
    private RecyclerView recycler;
    private View statusBar;
    //适配器
    private InsuranceListAdapter listAdapter;

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
        return R.layout.activity_aotu_insurance_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        title = view.findViewById(R.id.food_comment_title_tv);
        back = view.findViewById(R.id.food_comment_back);
        bannerImg = view.findViewById(R.id.aotu_insurance_list_image);
        statusBar = findViewById(R.id.normal_fakeview);
        recycler = view.findViewById(R.id.aotu_insurance_list_recycler);
        recycler.setLayoutManager(new GridLayoutManager(this,3));
        title.setText("买保险");
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if(v.getId() == R.id.food_comment_back){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getData();
        setTitleBarView();
    }

    private void getData(){
        Map<String,String> params=new HashMap<String,String>();
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                Gson gson = new Gson();
                insuanceEntity = gson.fromJson(result,InsuanceListEntity.class);
                setBanner();
                setList();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.INSURANCELIST,params);
    }

    private void setBanner(){
        List<InsuanceListEntity.BannerListBean> bannerList = insuanceEntity.getBanner_list();
        ImageUtils.showImage(this,HttpPath.IMAGEURL+bannerList.get(0).getUrlsmall(),bannerImg);
    }
    private void setList(){
        final List<InsuanceListEntity.ListBean> listBean = insuanceEntity.getList();
        listAdapter = new InsuranceListAdapter(R.layout.item_aotu_insurance_list,listBean,this);
        recycler.setAdapter(listAdapter);
        recycler.addItemDecoration(new DividerGridItemDecoration(this));
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id",listBean.get(position).getId());
                startActivity(InsuranceDetailsActivity.class,bundle);
            }
        });
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
        StatusBarUtil.setTranslucentForImageViewInFragment(InsuranceListActivity.this, null);
    }
}
