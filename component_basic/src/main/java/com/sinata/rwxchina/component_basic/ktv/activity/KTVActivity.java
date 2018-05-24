package com.sinata.rwxchina.component_basic.ktv.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.basiclib.utils.amaputils.LocationUtils;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.component_basic.basic.basiccashcoupon.CashCouponUtils;
import com.sinata.rwxchina.component_basic.basic.basiccheckself.CheckSelfUtils;
import com.sinata.rwxchina.component_basic.basic.basiccomment.CommentUtils;
import com.sinata.rwxchina.component_basic.basic.basicgroup.GroupUtils;
import com.sinata.rwxchina.component_basic.basic.basicinformation.InformationUtils;
import com.sinata.rwxchina.component_basic.basic.basicname.NameUtils;
import com.sinata.rwxchina.component_basic.basic.basicnearby.NearByUtils;
import com.sinata.rwxchina.component_basic.basic.basicservice.ServiceUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.ktv.adapter.KTVReserveAdapter;
import com.sinata.rwxchina.component_basic.ktv.adapter.KTVTypeAdapter;
import com.sinata.rwxchina.component_basic.ktv.entity.DateEntity;
import com.sinata.rwxchina.component_basic.ktv.entity.KTVEntity;
import com.sinata.rwxchina.component_basic.ktv.entity.KTVReserveEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2017/10/23.
 * KTV详情页
 */

public class KTVActivity extends BaseActivity {
    private KTVEntity ktvInfo;
    private BaseShopInfo shopInfo;
    /**店铺基本信息*/
    private View bannerView,nameView,informationView;
    /**自助买单*/
    private View discount;
    /**代金券*/
    private View cashCouponView;
    /**团购*/
    private View groupView;
    /**评价*/
    private View commentView;
    /**更多服务*/
    private View serviceView;
    /**附近商家*/
    private View nearByView;
    /**预定*/
    private View reserveView;
    private TabLayout ktvDate;
    private RecyclerView ktvTypeRecycler;
    private KTVTypeAdapter typeAdapter;
    private RecyclerView reserveRecycler;
    private KTVReserveAdapter ktvadapter;
    private List<BaseGoodsInfo> reserveliset;
    private int selectedPos=0;
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
        return R.layout.activity_ktv;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        reserveView=findViewById(R.id.activity_ktv_reserve);
        titleBarView = findViewById(R.id.ktv_title);
        scrollView = findViewById(R.id.ktv_scrollView);
        statusBar = findViewById(R.id.title_bar_fakeview);
        bannerView=findViewById(R.id.ktv_banner);
        nameView=findViewById(R.id.ktv_name);
        informationView=findViewById(R.id.ktv_information);
        discount=findViewById(R.id.ktv_checkSelf);
        cashCouponView=findViewById(R.id.ktv_cashcoupon);
        groupView=findViewById(R.id.ktv_group);
        commentView=findViewById(R.id.ktv_comment);
        serviceView=findViewById(R.id.ktv_service);
        nearByView=findViewById(R.id.ktv_nearby);
        ktvDate=findViewById(R.id.ktv_date);
        ktvTypeRecycler=findViewById(R.id.ktv_type);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ktvTypeRecycler.setLayoutManager(manager);
        reserveRecycler=findViewById(R.id.ktv_reserve_rechcler);
        reserveRecycler.setLayoutManager(new LinearLayoutManager(this));
        reserveliset=new ArrayList<BaseGoodsInfo>();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getKTV();
        setComment();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();

    }

    private void getKTV(){
        Map<String,String> params=new HashMap<String,String>();
        params.put("shopid",shopid);
        ApiManager apiManager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ktvInfo=new Gson().fromJson(result,KTVEntity.class);
                shopInfo=ktvInfo.getShopinfo();
                setShop();
                setCheckSelf();
                setGroup();
                setReserve();
                setCashCoupon();
                setService();
                setNearBy();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETKTV,params);
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
        LogUtils.e("设置代金券");
        CashCouponUtils.setCashCoupon(cashCouponView,this,ktvInfo.getGoodsvolume(),shopInfo);
    }

    /***
     * 设置团购列表
     */
    private void setGroup(){
        GroupUtils.setGroup(groupView,this,ktvInfo.getGoodsgroup(),shopInfo);
    }

    /**
     * 设置预定
     */
    private void setReserve(){
        //设置tablayout
        List<DateEntity> date=ktvInfo.getGoods_date();
        for (int i=0;i<date.size();i++){
            ktvDate.addTab(ktvDate.newTab().setText(date.get(i).getDate_title()+"\n"+date.get(i).getDate_content()));
        }
        //如果预定商品为空或长度为0，则隐藏
        if (ktvInfo.getGoods()==null||ktvInfo.getGoods().size()==0){
            reserveView.setVisibility(View.GONE);
            return;
        }
        typeAdapter=new KTVTypeAdapter(KTVActivity.this,R.layout.item_ktvtype,ktvInfo.getGoods());
        ktvTypeRecycler.setAdapter(typeAdapter);
        ktvadapter=new KTVReserveAdapter(R.layout.item_ktv_reserve,reserveliset);
        reserveRecycler.setAdapter(ktvadapter);
        //设置预定item点击监听
        ktvadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.item_ktv_reserve_btn){
                    StartPayMentUtils.startPayment(KTVActivity.this,reserveliset.get(position),shopInfo);

                }
            }
        });
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                reserveliset=ktvInfo.getGoods().get(position).getList();
                ktvadapter.setNewData(reserveliset);
                ktvadapter.notifyDataSetChanged();
                unClick(adapter,ktvTypeRecycler,selectedPos);
                selectedPos=position;
                click(adapter,ktvTypeRecycler,selectedPos);
            }
        });
        try {
            checkOne();
        }catch (Exception e){
            LogUtils.e("设置KTV异常："+e.toString());
        }
    }

    /**
     * 默认选中第一个
     */
    private void checkOne(){
        reserveliset=ktvInfo.getGoods().get(0).getList();
        ktvadapter.setNewData(reserveliset);
        ktvadapter.notifyDataSetChanged();
        click(typeAdapter,ktvTypeRecycler,selectedPos);
    }

    /**
     * 设置item被点击高亮
     * @param adapter
     * @param position
     */
    private void click(BaseQuickAdapter adapter,RecyclerView recyclerView,int position){
        adapter.getViewByPosition(recyclerView,position,R.id.item_ktvtype).setBackgroundDrawable(KTVActivity.this.getResources().getDrawable(R.drawable.right_angle_border_orange));
    }

    /**
     * 取消item高亮效果
     * @param adapter
     * @param position
     */
    private void unClick(BaseQuickAdapter adapter,RecyclerView recyclerView,int position){
        adapter.getViewByPosition(recyclerView,position,R.id.item_ktvtype).setBackgroundDrawable(KTVActivity.this.getResources().getDrawable(R.drawable.right_angle_border));
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
        StatusBarUtil.setTranslucentForImageViewInFragment(KTVActivity.this, null);
    }
    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,shopname);
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }
}
