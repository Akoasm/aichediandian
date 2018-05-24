package com.sinata.rwxchina.basiclib.payment.ordinarypayment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.basic.basiccashcouponbuy.GroupDetailUtils;
import com.sinata.rwxchina.basiclib.commonclass.activity.EntertainmentOrderBuySuccessActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.entity.CouponEntity;
import com.sinata.rwxchina.basiclib.payment.entity.DeductibleEntity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.utils.DiscountUtils;
import com.sinata.rwxchina.basiclib.payment.utils.InformationUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayBalanceUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayCashcouponUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayComeTimeUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayGroupUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayIntegralUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayNumberUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PaySelfUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayTypeUtils;
import com.sinata.rwxchina.basiclib.utils.immersionutils.ImmersionUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.sharedutils.SharedUserUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 普通支付页面
 * @modifyRecord
 */

public class PayMentActivity extends BaseActivity implements PaySelfUtils.CallDiscount,DiscountUtils.CallDiscountMoney,PayNumberUtils.CallNumberPrice
                                                            ,PayIntegralUtils.CallIntegral,PayBalanceUtils.CallBalance,PayCashcouponUtils.CallCoupon
                                                            ,PayComeTimeUtils.CallComeTime{
   private DeductibleEntity deductible;
    /**标题栏*/
    private View titleBar;
    private ImageView back;
    private TextView title;
    /**商品信息*/
    private View goodsInformationView;
    /**自助买单*/
    private View paySelfView;
    /**购买数量及合计*/
    private View payNumberView;
    /**到店时间*/
    private View comeTimeVeiw;
    /**套餐*/
    private View groupView;
    /**折扣*/
    private View discountView;
    /**代金券*/
    private View voucherView;
    /**积分*/
    private View integralView;
    /**余额*/
    private View balanceView;
    /**绑定手机号*/
    private View phoneView;
    private TextView phoneTV;
    /**底部支付栏*/
    private TextView payMoneyTv;
    private Button submitPay;
    /**是否为优惠买单(0:不是，1：是)*/
    private String is_youhui;
    /**是否为预定商品*/
    private String is_reserve;
    /**是否为代金券商品(0:不是，1：是)*/
    private String is_volume;
    /**是否为套餐商品*/
    private String is_group;
    /**购买商品店铺信息*/
    private BaseShopInfo shopInfo;
    /**购买商品信息*/
    private BaseGoodsInfo goodsInfo;
    /**商品总价格*/
    private BigDecimal goodsMoney;
    /**折扣后或者商品总价格，在使用代金券、积分、余额之前的金额*/
    private BigDecimal discountmoney;
    /**最后需要支付的价格*/
    private BigDecimal payMoney;
    /**使用的优惠券，为空则证明没有使用优惠券*/
    private CouponEntity coupon;
    /**是否使用优惠券，默认不使用*/
    private boolean isCoupon=false;
    /**是否使用积分兑换，默认不使用*/
    private boolean isIntegral=false;
    /**是否使用余额抵扣，默认不使用*/
    private boolean isBalance=false;
    /**是否第一次获取优惠信息*/
    private boolean isFirst=true;
    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
        String shop=params.getString("shopInfo");
        String goods=params.getString("goodsInfo");
        LogUtils.e("payment","goods="+goods);
        shopInfo=new Gson().fromJson(shop,BaseShopInfo.class);
        goodsInfo=new Gson().fromJson(goods,BaseGoodsInfo.class);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_shop_payment;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        titleBar=findViewById(R.id.home_title_fakeview);
        back=findViewById(R.id.back);
        title=findViewById(R.id.titleLayout_title_tv);
        goodsInformationView=findViewById(R.id.payment_goodsInformation);
        paySelfView=findViewById(R.id.payment_paySelf);
        payNumberView=findViewById(R.id.payment_payNumber);
        comeTimeVeiw=findViewById(R.id.payment_comeTime);
        groupView=findViewById(R.id.payment_group);
        discountView=findViewById(R.id.payment_discount);
        voucherView=findViewById(R.id.payment_voucher);
        integralView=findViewById(R.id.payment_integral);
        balanceView=findViewById(R.id.payment_balance);
        phoneView=findViewById(R.id.payment_phoneNumber);
        phoneTV=findViewById(R.id.payment_phone);
        payMoneyTv=findViewById(R.id.pay_money);
        submitPay=findViewById(R.id.go_pay);
        setTitleBarView();
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        submitPay.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.back){
            finish();
        }else if(id==R.id.go_pay){
            gotoPay();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setPayment();
        setView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }



    /**
     * 初始化支付页面布局
     */
    private void setView(){

        is_volume= goodsInfo.getIs_volume();
        is_group= goodsInfo.getGoods_is_group();
        is_reserve=goodsInfo.getGoods_is_freight();
        //是否为优惠买单（0：不是，1：是）
        if ("0".equals(is_youhui)){
            //汽车服务不显示绑定手机号
            if ("1".equals(shopInfo.getShop_type())){
                comeTimeVeiw.setVisibility(View.GONE);
            }
            paySelfView.setVisibility(View.GONE);
            discountView.setVisibility(View.GONE);
            title.setText("订单详情");
            //判断是否为代金券商品(0:不是，1：是)
            if ("1".equals(is_volume)){
                buyVoucher();
            }else if ("1".equals(is_group)){//判断是否为团购商品（0：不是，1：是）
                buyGroup();
            }else {//预定商品
                //TODO 后台暂时对预定没有特殊处理，默认为不是代金券商品不是团购商品就是预定商品
                buyReserve();
            }
            //设置商品信息
            InformationUtils.setInformation(PayMentActivity.this,goodsInformationView,goodsInfo,shopInfo);
            //设置购买数量
            PayNumberUtils.setPayNumber(PayMentActivity.this,payNumberView,goodsInfo);
            //设置到店时间
            PayComeTimeUtils.setComeTime(this,comeTimeVeiw);
            //设置预约手机号
            String phone= SharedUserUtils.getLoginPhone(this);
            try{
                phone=phone.substring(0, 3) + "****" + phone.substring(7, 11);
            }catch (Exception e){

            }
            phoneTV.setHint(phone);
        }else {
            buySelf();
        }
    }

    /**
     * 自助买单
     */
    private void buySelf(){
        title.setText("自助买单");
        goodsInformationView.setVisibility(View.GONE);
        payNumberView.setVisibility(View.GONE);
        comeTimeVeiw.setVisibility(View.GONE);
        groupView.setVisibility(View.GONE);
        phoneView.setVisibility(View.GONE);
        PaySelfUtils.setPaySelf(this,paySelfView);
        DiscountUtils.setDiscount(this,discountView,shopInfo,new BigDecimal("0"),new BigDecimal("0"));
    }

    private void setPayment(){
        SinglePayment.getSinglePayment().setGoods_id(goodsInfo.getGoods_id());
        SinglePayment.getSinglePayment().setShopid(goodsInfo.getShopid());
        //如果商品数量为空，则默认购买一件商品
        String goodsNumber=SinglePayment.getSinglePayment().getGoods_number();
        if (TextUtils.isEmpty(goodsNumber)){
            SinglePayment.getSinglePayment().setGoods_number("1");
        }
        is_youhui= SinglePayment.getSinglePayment().getIs_youhui();
        if ("0".equals(is_youhui)){
            showLog("不是优惠买单");
            discountmoney=new BigDecimal(SinglePayment.getSinglePayment().getGoods_number())
                    .multiply(new BigDecimal(goodsInfo.getGoods_price()));
            goodsMoney=discountmoney;
        }
    }

    /**
     * 预定商品
     */
    private void buyReserve(){
        payNumberView.setVisibility(View.GONE);
        GroupDetailUtils.setGroupDetailGroup(groupView,this,goodsInfo.getGoods_type(),goodsInfo.getGoods_group_data());
    }

    /**
     * 团购买单
     */
    private void buyGroup(){
        payNumberView.setVisibility(View.GONE);
        //美食团购不用显示到店时间，显示购买数量
        if ("3".equals(shopInfo.getShop_type())){
            payNumberView.setVisibility(View.VISIBLE);
            comeTimeVeiw.setVisibility(View.GONE);
            groupView.setVisibility(View.GONE);
        }
        //汽车服务不显示购买数量和手机号
        if ("1".equals(shopInfo.getShop_type())){
            payNumberView.setVisibility(View.GONE);
        }
        GroupDetailUtils.setGroupDetailGroup(groupView,this,goodsInfo.getGoods_type(),goodsInfo.getGoods_group_data());
    }

    /**
     * 代金券买单
     */
    private void buyVoucher(){
        comeTimeVeiw.setVisibility(View.GONE);
        groupView.setVisibility(View.GONE);
        voucherView.setVisibility(View.GONE);
    }

    /**
     * 获取优惠券、积分、余额信息
     */
    private void getwallet(){
        Map<String,String> params=new HashMap<>();
        params.put("cityid","1");
        params.put("price",discountmoney.toString());
        params.put("shop_type",shopInfo.getShop_type());
        params.put("shopid",shopInfo.getShopid());
        params.put("Is_mall","0");
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {

                deductible=new Gson().fromJson(result,DeductibleEntity.class);
                if (isFirst){
                    //选择代金券
                    PayCashcouponUtils.initCoupon(PayMentActivity.this,voucherView,deductible);
                    //选择积分
                    PayIntegralUtils.initIntegral(PayMentActivity.this,integralView,deductible);
                    //余额抵扣
                    PayBalanceUtils.initBalance(PayMentActivity.this,balanceView,deductible);
                    isFirst=false;
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETWALLET,params,false);
    }


    /**
     * 使用优惠券信息
     */
    private void useDiscount(){
        getwallet();
        setCashcoupon(discountmoney.toString(),shopInfo.getShop_type(),shopInfo.getShopid());
        BigDecimal money=discountmoney;
        showLog("折扣前money："+money.toString());
        if (isCoupon){
            if (coupon!=null){
                money=PayCashcouponUtils.getCoupon(coupon,money);
            }
        }
        if (isIntegral){
            money=PayIntegralUtils.setIntegral(deductible,money);

        }else {
            //设置支付订单的积分抵扣金额
            SinglePayment.getSinglePayment().setIntegral_money("0");
            PayIntegralUtils.setIntegral(deductible);
        }
        if (isBalance){
            money=PayBalanceUtils.setBalance(deductible,money);
        }else {
            //设置支付订单的余额折扣金额
            SinglePayment.getSinglePayment().setMoney("0");
            PayBalanceUtils.setBalance(deductible);
        }
        showLog("折扣后money："+money.toString());
        payMoney=money;
        if (payMoney.compareTo(new BigDecimal("0"))<0){
            payMoney=new BigDecimal("0.0");
        }
        payMoneyTv.setText("实付金额：￥"+payMoney.toString());
        //订单初始总价格
        SinglePayment.getSinglePayment().setGoodsmprices(goodsMoney.toString());
        //订单最后需要支付的金额
        SinglePayment.getSinglePayment().setGoodsprices(payMoney.toString());
    }

    /**
     * 弹出选择支付方式弹窗
     */
    private void gotoPay(){
        if (payMoney!=null){
            PayTypeUtils paytype=new PayTypeUtils(this);
            paytype.setShopInfo(shopInfo);
            paytype.setGoodsInfo(goodsInfo);
            paytype.submitPay(payMoney.toString(),submitPay);
        }
    }

    /**
     * 设置选择代金券跳转时传递的数据
     * @param price
     * @param shopType
     * @param shopId
     */
    private void setCashcoupon(String price,String shopType,String shopId){
        PayCashcouponUtils.price=price;
        PayCashcouponUtils.shopType=shopType;
        PayCashcouponUtils.shopId=shopId;
        PayCashcouponUtils.isMall="0";
    }

    @Override
    public void callDiscount(BigDecimal discount) {
        //减去折扣优惠价格之后需要支付的价格（选择代金券、积分、余额之前的价格）
        if (goodsMoney!=null){
            discountmoney=discount;
            useDiscount();
        }
    }

    @Override
    public void callPrice(BigDecimal orderPrice, BigDecimal discount) {
        //自助买单订单总价格
        goodsMoney=orderPrice;
        //计算参与折扣的价格
        DiscountUtils.setDiscount(this,discountView,shopInfo,orderPrice,discount);
    }

    @Override
    public void callNumberPrice(int buyNum, BigDecimal price) {
        //订单总价格
        goodsMoney=price;
        //修改购买数量之后的实付金额（选择代金券、积分、余额之前的价格）
        discountmoney=price;
        useDiscount();
    }

    @Override
    public void callIsIntegral(boolean isIntegral) {
        //是否使用积分
        this.isIntegral=isIntegral;
        useDiscount();
    }

    @Override
    public void callIsBalance(boolean isBalance) {
        //是否使用余额
        this.isBalance=isBalance;
        useDiscount();
    }

    @Override
    public void callIsCoupon(CouponEntity coupon, boolean isCoupon) {
        //是否使用优惠券
        this.isCoupon=isCoupon;
        //使用的优惠券信息，为空则没有使用优惠券
        this.coupon=coupon;
        useDiscount();
    }

    @Override
    public void callComeTime(Date date) {
        //选择到店时间
        String date_toshop=(1900+date.getYear())+"-"+(1+date.getMonth())+"-"+date.getDate()+" "+date.getHours()+":00";
        showLog(date_toshop);
        SinglePayment.getSinglePayment().setBespeak_date_toshop(date_toshop);
    }

    /**
     * 设置标题栏
     */
    private void setTitleBarView(){
        ImmersionUtils.setListImmersion(getWindow(),this,titleBar);
    }
    @Override
    public void setSetStatusBar(boolean setStatusBar) {
        super.setSetStatusBar(setStatusBar);
        StatusBarUtil.setTranslucentForImageViewInFragment(PayMentActivity.this, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finsh(String str){
        if ("finish".equals(str)){
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //将到店时间设为空字符串
        SinglePayment.getSinglePayment().setBespeak_date_toshop("");
        //将购买数量设为1
        SinglePayment.getSinglePayment().setGoods_number("1");
    }

}
