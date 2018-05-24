package com.sinata.rwxchina.component_basic.hotel.activity;

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
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.utils.StartPayMentUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.component_basic.basic.basicgroupdetail.GroupDetailActivity;
import com.sinata.rwxchina.component_basic.basic.basicnotice.NoticeUtils;

import java.math.BigDecimal;

/**
 * @author HRR
 * @datetime 2018/1/10
 * @describe 酒店预定页面
 * @modifyRecord
 */

public class HotelReserveActivity extends BaseActivity {
    private BaseGoodsInfo goodsInfo;
    private BaseShopInfo shopInfo;
    private NestedScrollView scrollView;
    private View status;
    /**标题栏*/
    private View titleView;
    /**banner*/
    private View bannerView;
    /**商品详情*/
    private TextView goodsName;
    private TextView description;
    private TextView type;
    /**购买须知*/
    private View noticeView;
    /**底部购买栏*/
    private TextView payMoney;
    private Button goPay;
    /**预定酒店天数*/
    private int number;
    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
        String goods=params.getString("goods");
        String shop=params.getString("shop");
        goodsInfo=new Gson().fromJson(goods,BaseGoodsInfo.class);
        shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
        number=params.getInt("num");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_hotelreserve;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        status=findViewById(R.id.hotelReserve_statue);
        scrollView=findViewById(R.id.hotelReserve_scollview);
        titleView=findViewById(R.id.hotelReserve_title);
        bannerView=findViewById(R.id.hotelReserve_banner);
        goodsName=findViewById(R.id.hotelReserve_name);
        description=findViewById(R.id.hotelReserve_description);
        type=findViewById(R.id.hotelReserve_type);
        noticeView=findViewById(R.id.hotelReserve_notice);
        payMoney=findViewById(R.id.pay_money);
        goPay=findViewById(R.id.go_pay);
    }

    @Override
    public void setListener() {
        goPay.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id == R.id.go_pay) {
            StartPayMentUtils.startPayment(this,goodsInfo,shopInfo);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

        BannerUtils.setBanner(bannerView,this,goodsInfo);
        NoticeUtils.setNotice(noticeView,this,goodsInfo);
        type.setText(goodsInfo.getGoods_type_labels_str()+" "+goodsInfo.getGoods_bed_type()+"米");
        goodsName.setText(goodsInfo.getGoods_name());
        description.setText(goodsInfo.getGoods_description());
        BigDecimal money=new BigDecimal(goodsInfo.getGoods_price());
        money=money.multiply(new BigDecimal(number));
        payMoney.setText("实付金额：￥"+money.toString());
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
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleView,status);
        ImmersionUtils.setTitleBar(this,titleView,goodsInfo.getGoods_name());
    }
    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }
}
