package com.sinata.rwxchina.component_basic.basic.basicgroupdetail;

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
import com.sinata.rwxchina.basiclib.utils.controlutils.TextviewUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.view.MyScrollView;
import com.sinata.rwxchina.component_basic.R;
import com.sinata.rwxchina.component_basic.basic.basicbanner.BannerUtils;
import com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy.GroupDetailUtils;
import com.sinata.rwxchina.component_basic.basic.basicnearby.NearByUtils;
import com.sinata.rwxchina.component_basic.basic.basicnotice.NoticeUtils;
import com.sinata.rwxchina.component_basic.finefood.activity.FoodActivity;

/**
 * @author HRR
 * @datetime 2017/12/22
 * @describe 团购详情页面
 * @modifyRecord
 */

public class GroupDetailActivity extends BaseActivity {
    private BaseShopInfo shopInfo;
    private BaseGoodsInfo groupInfo;
    /**banner*/
    private View bannerView;
    /**店铺信息*/
    private TextView name,price,originalprice,quantity;
    /**条件*/
    private View conditionView;
    /**套餐*/
    private View groupView;
    /**附近商家*/
    private View nearByView;
    /**购买须知*/
    private View noticeView;
    /**下单*/
    private TextView payMoney;
    private Button buy;
    /**titleBar*/
    private View titleBarView;
    private View statusBar;
    private NestedScrollView scrollView;
    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
        String group=params.getString("group");
        String shop=params.getString("shop");
        if (!TextUtils.isEmpty(group)){
            groupInfo=new Gson().fromJson(group,BaseGoodsInfo.class);
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
        return R.layout.activity_basic_group;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        titleBarView = findViewById(R.id.basic_group_title);
        statusBar = findViewById(R.id.title_bar_fakeview);
        scrollView = findViewById(R.id.food_group_scrollview);
        bannerView=findViewById(R.id.basic_group_banner);
        name=findViewById(R.id.food_group_name);
        price=findViewById(R.id.food_group_currentprice);
        originalprice=findViewById(R.id.food_group_originalprice);
        quantity=findViewById(R.id.food_group_quantity);
        conditionView=findViewById(R.id.basic_group_condition);
        groupView=findViewById(R.id.basic_group_group);
        nearByView=findViewById(R.id.basic_group_nearBy);
        noticeView=findViewById(R.id.basic_group_notice);
        payMoney=findViewById(R.id.pay_money);
        buy=findViewById(R.id.go_pay);
    }

    @Override
    public void setListener() {
        buy.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.go_pay){
            SinglePayment.getSinglePayment().setIs_youhui("0");
            StartPayMentUtils.startPayment(GroupDetailActivity.this,groupInfo,shopInfo);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setGroup();
        setGroupDetail();
        setNotice();
        setNearby();
        setPay();
        setTitleBarView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 设置套餐基本信息
     */
    private void setGroup(){
        BaseGoodsInfo info=groupInfo;
        BannerUtils.setBanner(bannerView,this,info);
        name.setText(info.getGoods_name());
        price.setText("￥"+info.getGoods_price()+"/"+info.getGoods_unit());
        originalprice.setText("￥"+info.getGoods_market_price()+"/"+info.getGoods_unit());
        TextviewUtils.setStrikethrough(originalprice);
        quantity.setText("已售"+info.getGoods_sale());
        ConditionUtils.setCondition(this,conditionView,info.getGoods_labels_attr());
    }

    /**
     * 设置套餐详情
     */
    private void setGroupDetail(){
        GroupDetailUtils.setGroupDetailGroup(groupView,this,groupInfo.getGoods_type(),groupInfo.getGoods_group_data());
    }

    /**
     * 设置附近商家
     */
    private void setNearby(){
        NearByUtils.getNearBy(nearByView,this,shopInfo);
    }
    /**
     * 设置用户须知
     */
    private void setNotice(){

        NoticeUtils.setNotice(noticeView,this,groupInfo);
    }

    /**
     * 设置支付
     */
    private void setPay(){
        payMoney.setText("实付金额：￥"+groupInfo.getGoods_price());
    }



    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setTitleBar(this,titleBarView,groupInfo.getGoods_name());
        ImmersionUtils.setImmersionCanBack(getWindow(),this,scrollView,titleBarView,statusBar);
    }

    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(GroupDetailActivity.this, null);
    }
}
