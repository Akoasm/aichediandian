package com.sinata.rwxchina.component_basic.finefood.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.DividerRecyclerItemDecoration;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.component_basic.basic.basiccashcoupon.CashCouponUtils;
import com.sinata.rwxchina.component_basic.basic.basiccheckself.CheckSelfUtils;
import com.sinata.rwxchina.component_basic.basic.basiccomment.CommentUtils;
import com.sinata.rwxchina.component_basic.basic.basicgroup.GroupUtils;
import com.sinata.rwxchina.component_basic.basic.basicinformation.InformationUtils;
import com.sinata.rwxchina.component_basic.basic.basicname.NameUtils;
import com.sinata.rwxchina.component_basic.basic.basicnearby.NearByUtils;
import com.sinata.rwxchina.component_basic.basic.basicservice.ServiceUtils;
import com.sinata.rwxchina.component_basic.finefood.adapter.CharacteristicAdapter;
import com.sinata.rwxchina.component_basic.finefood.entity.FoodEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wj on 2017/10/23.
 * 美食详情页
 */

public class FoodActivity extends BaseActivity{
    private FoodEntity foodInfo;
    private BaseShopInfo shopInfo;
    /**店铺基本信息*/
    private View bannerView,nameView,informationView;
    /**自助买单*/
    private View discount;
    /**代金券*/
    private View cashCouponView;
    /**团购*/
    private View groupView;
    /**特色菜*/
    private View specialDishesView;
    private RecyclerView characteristicRecycler;
    private TextView characteristicTv;
    private CharacteristicAdapter adapter;
    private TextView charavteristicMore;
    /**评价*/
    private View commentView;
    /**更多服务*/
    private View serviceView;
    /**附近商家*/
    private View nearByView;
    private String shopid;
    private String shopname;
    /**titleBar*/
    private View titleBarView;
    private View statusBar;
    private NestedScrollView scrollView;
    @Override
    public void initParms(Bundle parms) {
        shopid = parms.getString("shopid");
        shopname = parms.getString("shop_name");

        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_food;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        specialDishesView=findViewById(R.id.food_specialDishes);
        titleBarView=findViewById(R.id.food_titleBar);
        statusBar = findViewById(R.id.title_bar_fakeview);
        scrollView = findViewById(R.id.food_scrollview);
        bannerView=findViewById(R.id.food_banner);
        nameView=findViewById(R.id.food_name);
        informationView=findViewById(R.id.food_information);
        discount=findViewById(R.id.food_checkSelf);
        cashCouponView=findViewById(R.id.food_cashcoupon);
        charavteristicMore=findViewById(R.id.food_tv_characteristic);
        groupView=findViewById(R.id.food_group);
        characteristicRecycler=findViewById(R.id.food_characteristic_rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        characteristicRecycler.setLayoutManager(linearLayoutManager);
        characteristicTv=findViewById(R.id.food_characteristic_tv);
        commentView=findViewById(R.id.food_comment);
        serviceView=findViewById(R.id.food_service);
        nearByView=findViewById(R.id.food_nearby);
    }

    @Override
    public void setListener() {
        charavteristicMore.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.food_tv_characteristic){
            Intent intent=new Intent(FoodActivity.this,SpecialitiesActivity.class);
            intent.putExtra("shopId",shopInfo.getShopid());
            startActivity(intent);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getFood();
        setComment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,shopname);
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }

    /**
     * 获取美食商铺信息
     */
    private void getFood(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopid);
        params.put("lng", LocationUtils.getLng(this));
        params.put("lat",LocationUtils.getLat(this));
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                foodInfo=new Gson().fromJson(result,FoodEntity.class);
                shopInfo=foodInfo.getShopinfo();
                setShop();
                setCheckSelf();
                setGroup();
                setCharacteristic();
                setCashCoupon();
                setService();
                setNearBy();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETFOOD,params);
    }

    /***
     * 设置店铺基本信息
     */
    private void setShop(){
        BannerUtils.setBanner(bannerView,this,shopInfo);
        NameUtils.setName(nameView,shopInfo);
        InformationUtils.setInformation(informationView,this,shopInfo);
    }

    /**
     * 设置自助买单
     */
    private void setCheckSelf(){
        CheckSelfUtils.setCheckSelf(this,discount,shopInfo);
    }
    /**
     * 设置代金券
     */
    private void setCashCoupon(){
        CashCouponUtils.setCashCoupon(cashCouponView,this,foodInfo.getGoodsvolume(),shopInfo);
    }

    /***
     * 设置团购列表
     */
    private void setGroup(){
        GroupUtils.setGroup(groupView,this,foodInfo.getGoodsgroup(),shopInfo);
    }

    /**
     * 设置特色菜
     */
    private void setCharacteristic(){
        if (foodInfo.getGoodsfeature()==null||foodInfo.getGoodsfeature().size()==0){
            specialDishesView.setVisibility(View.GONE);
            return;
        }
        adapter=new CharacteristicAdapter(this,R.layout.food_characteristic_item,foodInfo.getGoodsfeature());
        characteristicRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(FoodActivity.this,SpecialitiesActivity.class);
                intent.putExtra("shopId",shopInfo.getShopid());
                startActivity(intent);
            }
        });
        String goodsFeature=foodInfo.getGoodsfeature_names();
        if (TextUtils.isEmpty(goodsFeature)){
            characteristicTv.setVisibility(View.GONE);
        }else {
            characteristicTv.setVisibility(View.VISIBLE);
            characteristicTv.setText(foodInfo.getGoodsfeature_names());
        }
    }
    /**
     * 设置用户评价
     */
    private void setComment(){
        CommentUtils.getComment(commentView,this,shopid);
    }

    /**
     * 设置更多服务
     */
    private void setService(){
        ServiceUtils.setService(serviceView,shopInfo.getShop_service());
    }

    /**
     * 设置附近店铺
     */
    public void setNearBy(){
        NearByUtils.getNearBy(nearByView,this,shopInfo);
    }

    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(FoodActivity.this, null);
    }


}
