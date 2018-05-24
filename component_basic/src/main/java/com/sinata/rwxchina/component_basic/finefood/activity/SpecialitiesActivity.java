package com.sinata.rwxchina.component_basic.finefood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.utils.imageUtils.bigimage.BigImageActivity;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.finefood.adapter.SpecialitiesAdapter;
import com.sinata.rwxchina.component_basic.finefood.entity.CharacteristicEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 美食特色菜页面
 * @modifyRecord
 */

public class SpecialitiesActivity extends BaseActivity{
    /**标题栏*/
    private ImageView back;
    private TextView title;
    private View statusBar;
    /**特色菜列表*/
    private RecyclerView specialitiesRecycler;
    /**特色菜列表实体类*/
    private SpecialitiesAdapter adapter;
    private String shopId;
    @Override
    public void initParms(Bundle params) {
        shopId=params.getString("shopId");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_food_specialities;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        specialitiesRecycler=findViewById(R.id.food_specilalities_recycler);
        specialitiesRecycler.setLayoutManager(new LinearLayoutManager(this));
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        statusBar = findViewById(R.id.normal_fakeview);
        title.setText("特色菜");
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.food_comment_back){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getSpecialitiesList();
        setTitleBarView();
    }


    private void getSpecialitiesList(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
        params.put("goods_is_tuijian","1");
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<CharacteristicEntity> entities=new Gson().fromJson(result,new TypeToken<List<CharacteristicEntity>>(){}.getType());
                adapter=new SpecialitiesAdapter(SpecialitiesActivity.this,R.layout.food_specialities_item,entities);
                specialitiesRecycler.setAdapter(adapter);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId()==R.id.specialities_item_img){
                            Intent intent=new Intent(SpecialitiesActivity.this, BigImageActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("url",HttpPath.IMAGEURL+((List<CharacteristicEntity>)adapter.getData()).get(position).getDefault_image());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETGOODLIST,params);
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
        StatusBarUtil.setTranslucentForImageViewInFragment(SpecialitiesActivity.this, null);
    }
}
