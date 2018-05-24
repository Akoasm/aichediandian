package com.sinata.rwxchina.component_basic.regimen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
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
import com.sinata.rwxchina.component_basic.finefood.activity.SpecialitiesActivity;
import com.sinata.rwxchina.component_basic.regimen.adapter.HealthTechnicianAdapter;
import com.sinata.rwxchina.component_basic.regimen.adapter.ReserveAdapter;
import com.sinata.rwxchina.component_basic.regimen.entity.HealthEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2017/10/23.
 * 养生详情页
 */

public class HealthActivity extends BaseActivity {
    private HealthEntity health;
    private List<BaseGoodsInfo> goodsInfos;
    private String shopId;
    /**商铺信息*/
    private View bannerView,nameView,informationView;
    /**自助买单*/
    private View discount;
    /**代金券*/
    private View cashCouponView;
    /**预定*/
    private View reserveView;
    private RecyclerView reserveRecycler;
    private ReserveAdapter reserveAdapter;
    private LinearLayout allReserve;
    /**团购*/
    private View groupView;
    /**技师*/
    private View technicianView;
    private RecyclerView technicianRecycelr;
    private HealthTechnicianAdapter healthTechnicianAdapter;
    private TextView allTechnicianTv;
    private ImageView allTechnicianIv;
    /**评价*/
    private View commentView;
    /**更多服务*/
    private View serviceView;
    /**附近商家*/
    private View nearByView;

    private String shopname;
    /**titleBar*/
    private View titleBarView;
    private View statusBar;
    private NestedScrollView scrollView;
    @Override
    public void initParms(Bundle parms) {
        shopId = parms.getString("shopid");
        shopname = parms.getString("shop_name");
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_health;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        goodsInfos=new ArrayList<BaseGoodsInfo>();
        technicianView=findViewById(R.id.health_technician);
        reserveView=findViewById(R.id.health_reserve);
        titleBarView = findViewById(R.id.health_title);
        scrollView = findViewById(R.id.health_scrollView);
        statusBar = findViewById(R.id.title_bar_fakeview);
        bannerView=findViewById(R.id.health_banner);
        nameView=findViewById(R.id.health_name);
        informationView=findViewById(R.id.health_information);
        discount=findViewById(R.id.basc_health_discount);
        cashCouponView=findViewById(R.id.health_cashCoupon);
        reserveRecycler=findViewById(R.id.health_reserve_recycle);
        reserveRecycler.setLayoutManager(new LinearLayoutManager(this));
        allReserve=findViewById(R.id.health_reserve_all);
        groupView=findViewById(R.id.health_groups);
        technicianRecycelr=findViewById(R.id.health_technician_recycle);
        LinearLayoutManager technicianManager=new LinearLayoutManager(this);
        technicianManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        technicianRecycelr.setLayoutManager(technicianManager);
        allTechnicianTv=findViewById(R.id.health_alljishi);
        allTechnicianIv=findViewById(R.id.health_jishi_img);
        commentView=findViewById(R.id.health_comment);
        serviceView=findViewById(R.id.health_service);
        nearByView=findViewById(R.id.health_nearby);
    }

    @Override
    public void setListener() {
        allTechnicianIv.setOnClickListener(this);
        allTechnicianTv.setOnClickListener(this);
        allReserve.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.health_alljishi||id==R.id.health_jishi_img){
            String shop=new Gson().toJson(health.getShopinfo());
            Intent intent=new Intent(HealthActivity.this,TechnicianListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("shop_info", shop);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if (id==R.id.health_reserve_all){
            String good=new Gson().toJson(health.getGoods());
            String shop=new Gson().toJson(health.getShopinfo());
            Intent intent=new Intent(this,ReserveListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("goods",good);
            bundle.putString("shop",shop);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getHealthData();
        setComment();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();

    }

    /**
     * 获取养生店铺数据
     */
    private void getHealthData(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopId);
        ApiManager getHealth=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                health=new Gson().fromJson(result,HealthEntity.class);
                shopId=health.getShopinfo().getShopid();
                setShop();
                setCheckSelf();
                setCashCoupon();
                setReserve();
                setGroup();
                setTechnician();
                setService();
                setNearBy();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        getHealth.post(HttpPath.GETHEALTH,params);
    }

    /**
     * 设置养生商铺信息
     */
    private void setShop(){
        //设置轮播图
        BannerUtils.setBanner(bannerView,this,health.getShopinfo());
        //设置店铺基本信息
        NameUtils.setName(nameView,health.getShopinfo());
        InformationUtils.setInformation(informationView,this,health.getShopinfo());
    }

    /**
     * 设置自助买单
     */
    private void setCheckSelf(){
        CheckSelfUtils.setCheckSelf(this,discount,health.getShopinfo());
    }
    /**
     * 设置代金券
     */
    private void setCashCoupon(){
        CashCouponUtils.setCashCoupon(cashCouponView,this,health.getGoodsvolume(),health.getShopinfo());
    }

    /**
     * 设置预定
     */
    private void setReserve(){
        //如果店铺预定商品数量为空或者等于0，则隐藏预定
        //如果店铺预定商品数量大于三个，则只显示三个
        if (health.getGoods()==null||health.getGoods().size()==0){
            reserveView.setVisibility(View.GONE);
        }else if (health.getGoods().size()>3){
            goodsInfos.add(health.getGoods().get(0));
            goodsInfos.add(health.getGoods().get(1));
            goodsInfos.add(health.getGoods().get(2));
            allReserve.setVisibility(View.VISIBLE);
        }else {
            goodsInfos=health.getGoods();
            allReserve.setVisibility(View.GONE);
        }
        reserveAdapter=new ReserveAdapter(R.layout.item_health_reserve,goodsInfos);
        reserveRecycler.setAdapter(reserveAdapter);
        reserveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StartPayMentUtils.startPayment(HealthActivity.this,health.getGoods().get(position),health.getShopinfo());
            }
        });
    }

    /***
     * 设置团购列表
     */
    private void setGroup(){
        GroupUtils.setGroup(groupView,this,health.getGoodsgroup(),health.getShopinfo());
    }

    /**
     * 设置技师列表
     */
    private void setTechnician(){
        if (health.getShopteamson()==null||health.getShopteamson().size()==0){
            technicianView.setVisibility(View.GONE);
            return;
        }
        healthTechnicianAdapter =new HealthTechnicianAdapter(this,R.layout.item_health_technician,health.getShopteamson());
        technicianRecycelr.setAdapter(healthTechnicianAdapter);
        healthTechnicianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String shop=new Gson().toJson(health.getShopinfo());
                Intent technician=new Intent(HealthActivity.this,TechnicianActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("shop",shop);
                bundle.putString("teamsonid",health.getShopteamson().get(position).getTeamsonid());
                technician.putExtras(bundle);
                startActivity(technician);
            }
        });
    }

    /**
     * 设置用户评价
     */
    private void setComment(){
        CommentUtils.getComment(commentView,this,shopId);
    }

    /**
     * 设置更多服务
     */
    private void setService(){
        ServiceUtils.setService(serviceView,health.getShopinfo().getShop_service());
    }

    /**
     * 设置附近店铺
     */
    public void setNearBy(){
        NearByUtils.getNearBy(nearByView,this,health.getShopinfo());
    }

    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(HealthActivity.this, null);
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,shopname);
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }
    @Override
    public void steepStatusBar() {
        super.steepStatusBar();
        //状态栏透明
        StatusBarUtil.setTransparentForImageViewInFragment(HealthActivity.this, null);
    }
}
