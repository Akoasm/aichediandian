package com.sinata.rwxchina.component_basic.regimen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.regimen.adapter.ReserveAdapter;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/1/12
 * @describe 养生模块预定列表页面
 * @modifyRecord
 */

public class ReserveListActivity extends BaseActivity {
    private RecyclerView reserveRecycler;
    private ReserveAdapter reserveAdapter;
    private List<BaseGoodsInfo> goodsInfos;
    private BaseShopInfo shopInfo;
    private ImageView back;
    private TextView title;
    @Override
    public void initParms(Bundle params) {
        String goods=params.getString("goods");
        String shop=params.getString("shop");
        if (!TextUtils.isEmpty(goods)){
            goodsInfos=new Gson().fromJson(goods,new TypeToken<List<BaseGoodsInfo>>(){}.getType());
        }
        if (!TextUtils.isEmpty(shop)){
            shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_reservelist;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        reserveRecycler=findViewById(R.id.activity_reservelist_recycler);
        reserveRecycler.setLayoutManager(new LinearLayoutManager(this));
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        title.setText("全部预定");
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
        reserveAdapter=new ReserveAdapter(R.layout.item_health_reserve,goodsInfos);
        reserveRecycler.setAdapter(reserveAdapter);
        reserveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StartPayMentUtils.startPayment(ReserveListActivity.this,goodsInfos.get(position),shopInfo);
            }
        });
    }
}
