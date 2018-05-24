package com.sinata.rwxchina.component_basic.basic.basiccashcoupon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy.GroupDetailUtils;
import com.sinata.rwxchina.component_basic.basic.basicinformation.InformationUtils;
import com.sinata.rwxchina.component_basic.basic.basicname.NameUtils;
import com.sinata.rwxchina.component_basic.basic.basicnearby.NearByUtils;
import com.sinata.rwxchina.component_basic.basic.basicnotice.NoticeUtils;

/**
 * @author HRR
 * @datetime 2017/12/21
 * @describe 代金券详情页面，可直接购买
 * @modifyRecord
 */

public class CashCouponBuyActivity extends BaseActivity {
    private BaseShopInfo shopInfo;
    private BaseGoodsInfo goodsInfo;
    /**banner*/
    private View bannerView;
    /**代金券信息*/
    private TextView name,explain,price,original_price;
    private Button panicBuy;
    /**商家信息*/
    private View shopView;
    private View informationView;
    /**附近店铺*/
    private View nearByView;
    /**服务内容或套餐*/
    private View serviceView;
    /**购买须知*/
    private View noticeView;
    /**titleBar*/
    private View titleBarView;
    private View statusBar;
    private NestedScrollView scrollView;
    @Override
    public void initParms(Bundle params) {
        String shop=params.getString("shop");
        String cashCoupon=params.getString("cashCoupon");
        if (!TextUtils.isEmpty(shop)){
            shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
        }
        if (!TextUtils.isEmpty(cashCoupon)){
            goodsInfo=new Gson().fromJson(cashCoupon,BaseGoodsInfo.class);
        }
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_voucher_buy;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        titleBarView=findViewById(R.id.voucher_buy_title);
        statusBar = findViewById(R.id.title_bar_fakeview);
        scrollView = findViewById(R.id.voucher_buy_scrollview);
        bannerView=findViewById(R.id.voucher_buy_banner);
        name=findViewById(R.id.voucher_buy_name);
        explain=findViewById(R.id.voucher_buy_explain);
        price=findViewById(R.id.voucher_buy_present_price);
        original_price=findViewById(R.id.voucher_buy_original_price);
        panicBuy=findViewById(R.id.voucher_buy_panicBuy);
        shopView=findViewById(R.id.voucher_shop);
        informationView=findViewById(R.id.voucher_address);
        nearByView=findViewById(R.id.voucher_nerBy);
        serviceView=findViewById(R.id.voucher_service);
        noticeView=findViewById(R.id.voucher_notice);
    }

    @Override
    public void setListener() {
        panicBuy.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.voucher_buy_panicBuy){
            StartPayMentUtils.startPayment(this,goodsInfo,shopInfo);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setShop();
        setCashCoupon();
        setService();
        setNotice();
        setNearBy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitleBarView();
    }

    /**
     * 设置代金券信息
     */
    private void setCashCoupon(){
        name.setText(goodsInfo.getGoods_name());
        explain.setText(goodsInfo.getVolume_description());
        price.setText("￥"+goodsInfo.getGoods_price());
        original_price.setText("最高门市价： ￥"+goodsInfo.getGoods_market_price());
    }

    /**
     * 设置店铺基本信息
     */
    private void setShop(){
        BannerUtils.setBanner(bannerView,this,goodsInfo);
        NameUtils.setName(shopView,shopInfo);
        InformationUtils.setInformation(informationView,this,shopInfo);
    }

    /**
     * 设置附近店铺
     */
    public void setNearBy(){
        NearByUtils.getNearBy(nearByView,this,shopInfo);
    }

    /**
     * 设置服务内容
     */
    public void setService(){
        GroupDetailUtils.setCashCouponGroup(serviceView,this,shopInfo.getShop_type(),goodsInfo.getGoods_group_data());
    }

    /**
     * 设置用户须知
     */
    private void setNotice(){
        NoticeUtils.setNotice(noticeView,this,goodsInfo);
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,goodsInfo.getGoods_name());
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }
    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(CashCouponBuyActivity.this, null);
    }
}
