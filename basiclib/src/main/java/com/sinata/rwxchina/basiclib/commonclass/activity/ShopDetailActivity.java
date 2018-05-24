package com.sinata.rwxchina.basiclib.commonclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.adpter.ShopDetailCommodityAdapter;
import com.sinata.rwxchina.basiclib.commonclass.adpter.ShopInfoBrandRVAdapter;
import com.sinata.rwxchina.basiclib.commonclass.adpter.ShopInfoTypeRVAdapter;
import com.sinata.rwxchina.basiclib.commonclass.contract.ShopDetailContract;
import com.sinata.rwxchina.basiclib.commonclass.prensenter.ShopDetailPresenter;
import com.sinata.rwxchina.basiclib.utils.ViewPagerUtils.ViewPagerUtils;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author:zy
 * @detetime:2018/1/2
 * @describe：自营商城
 * @modifyRecord:修改记录
 */

public class ShopDetailActivity extends BaseActivity implements ShopDetailContract.View {
    private ViewPager mBrandSelfShopViewpager;
    private LinearLayout mBrandSelfShopDots;
    private RecyclerView mBrandSelfShopClassify;
    private RecyclerView mBrandSelfShopLabel;
    private RecyclerView mBrandSelfShopCommodity;
    private ShopInfoBean shopInfoBean;
    private TextView mTitleBarTitleTv;
    private ImageView mTitleBarBackCircle;
    private ShopInfoTypeRVAdapter shopInfoTypeRVAdapter;
    private ShopDetailPresenter shopDetailPresenter;
    private ShopInfoBrandRVAdapter shopInfoBrandRVAdapter;
    private ShopDetailCommodityAdapter shopDetailCommodityAdapter;
    private Map<String,String> param ;


    @Override
    public void initParms(Bundle params) {
        param = new HashMap<>();
        shopInfoBean = (ShopInfoBean) params.getSerializable("shopInfo");
        param.put("is_mallgoods","1");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_brand_self_shop;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mBrandSelfShopViewpager = findViewById(R.id.brand_self_shop_viewpager);
        mBrandSelfShopDots = findViewById(R.id.brand_self_shop_dots);
        mBrandSelfShopClassify = findViewById(R.id.brand_self_shop_classify);
        mBrandSelfShopLabel = findViewById(R.id.brand_self_shop_label);
        mBrandSelfShopCommodity = findViewById(R.id.brand_self_shop_commodity);
        mTitleBarTitleTv = findViewById(R.id.title_bar_title_tv);
        mTitleBarBackCircle = findViewById(R.id.title_bar_back_circle);
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
        showView();
        shopDetailPresenter = new ShopDetailPresenter(this);
        shopDetailPresenter.attachView(this);
    }

    private void showView() {
        ViewPagerUtils viewPagerUtils = new ViewPagerUtils(mBrandSelfShopDots, mBrandSelfShopViewpager, this, shopInfoBean.getShopinfo().getShop_show());
        viewPagerUtils.viewpagergo();
        shopInfoTypeRVAdapter = new ShopInfoTypeRVAdapter(shopInfoBean.getGoods_type_list(), this);
        mBrandSelfShopClassify.setLayoutManager(new LinearLayoutManager(this));
        shopInfoTypeRVAdapter.setOnItemCilckListener(new ShopInfoTypeRVAdapter.OnItemCilckListener() {
            @Override
            public void onCilckListener(ShopInfoTypeRVAdapter.ViewHolder viewHolder, int position, boolean isChecked) {
                if (position == 0){
                    param.put("goods_type","");
                }else {
                    param.put("goods_type",shopInfoTypeRVAdapter.getList().get(position-1).getGoods_type());
                }
                shopDetailPresenter.getTypeCommodity(param);
            }

        });
        mBrandSelfShopClassify.setNestedScrollingEnabled(false);
        mBrandSelfShopClassify.setAdapter(shopInfoTypeRVAdapter);
        shopInfoBrandRVAdapter = new ShopInfoBrandRVAdapter(shopInfoBean.getGoods_brand_list(),this);
        mBrandSelfShopLabel.setLayoutManager(new GridLayoutManager(this,3));
        shopInfoBrandRVAdapter.setOnItemCilckListener(new ShopInfoBrandRVAdapter.OnItemCilckListener() {
            @Override
            public void onCilckListener(ShopInfoBrandRVAdapter.ViewHolder viewHolder, int position, boolean isChecked) {
                param.put("brand_id",shopInfoBrandRVAdapter.getList().get(position).getBrand_id());
                shopDetailPresenter.getBrandCommodity(param);

            }
        });
        mBrandSelfShopLabel.setNestedScrollingEnabled(false);
        mBrandSelfShopLabel.setAdapter(shopInfoBrandRVAdapter);

        shopDetailCommodityAdapter = new ShopDetailCommodityAdapter(shopInfoBean.getGoods(),this);
        mBrandSelfShopCommodity.setLayoutManager(new GridLayoutManager(this,2));
        shopDetailCommodityAdapter.setOnItemCilckListener(new ShopDetailCommodityAdapter.OnItemCilckListener() {
            @Override
            public void onCilckListener(ShopDetailCommodityAdapter.ViewHolder viewHolder, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("commodity",new Gson().fromJson(new Gson().toJson(shopDetailCommodityAdapter.getList().get(position)),CommodityBean.class));
                startActivity(CommodityDetailActivity.class,bundle);
            }
        });
        mBrandSelfShopCommodity.setNestedScrollingEnabled(false);
        mBrandSelfShopCommodity.setAdapter(shopDetailCommodityAdapter);

    }

    private void setTitle() {
        mTitleBarTitleTv.setText(shopInfoBean.getShopinfo().getShop_name());
        mTitleBarTitleTv.setTextColor(ContextCompat.getColor(this,R.color.white));
        mTitleBarBackCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showTypeCommodity(List<ShopInfoBean.GoodsBean> commodityBean) {
         shopDetailCommodityAdapter.setList(commodityBean);
        shopDetailCommodityAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBrandCommodity(List<ShopInfoBean.GoodsBean> commodityBean) {
        shopDetailCommodityAdapter.setList(commodityBean);
        shopDetailCommodityAdapter.notifyDataSetChanged();
    }
}
