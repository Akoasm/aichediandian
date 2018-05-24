package com.sinata.rwxchina.basiclib.payment.mallpayment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.AddressListBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.payment.entity.CouponEntity;
import com.sinata.rwxchina.basiclib.payment.entity.DeductibleEntity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.utils.DiscountUtils;
import com.sinata.rwxchina.basiclib.payment.utils.MallPayAddressUtils;
import com.sinata.rwxchina.basiclib.payment.utils.MallPayShopUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayBalanceUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayCashcouponUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayIntegralUtils;
import com.sinata.rwxchina.basiclib.payment.utils.PayTypeUtils;
import com.sinata.rwxchina.basiclib.payment.utils.TotalMoneyUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 商城支付页面
 * @modifyRecord
 */

public class MallPayMentActivity extends BaseActivity  implements PayIntegralUtils.CallIntegral,PayBalanceUtils.CallBalance
                                                                ,PayCashcouponUtils.CallCoupon,TotalMoneyUtils.CallTotalMoney{
    private DeductibleEntity deductible;
    /**标题栏*/
    private View titleBar;
    private ImageView back;
    private TextView title;
    /**收货地址*/
    private View addressView;
    /**商品信息*/
    private View goodsInformationView;
    /**代金券*/
    private View voucherView;
    /**积分*/
    private View integralView;
    /**余额*/
    private View balanceView;
    /**总额*/
    private View moneyView;
    /**底部支付栏*/
    private TextView payMoneyTv;
    private Button submitPay;
    /**购买商品店铺信息*/
    private ShopInfoBean.ShopinfoBean shopInfo;
    /**购买商品信息*/
    private CommodityBean goodsInfo;
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
    /**收货地址工具列*/
    private MallPayAddressUtils address;
    @Override
    public void initParms(Bundle params) {
        String bean=params.getString("shopinfo");
        String goods=params.getString("commodity");
        if (!TextUtils.isEmpty(bean)){
            ShopInfoBean info=new Gson().fromJson(bean,ShopInfoBean.class);
            shopInfo=info.getShopinfo();
        }
        if (!TextUtils.isEmpty(goods)){
            goodsInfo=new Gson().fromJson(goods,CommodityBean.class);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_mall_payment;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        SinglePayment.getSinglePayment().setIs_mall("1");
        addressView=findViewById(R.id.mallpayment_address);
        titleBar=findViewById(R.id.payment_titleBar);
        back=findViewById(R.id.food_comment_back);
        title=findViewById(R.id.food_comment_title_tv);
        goodsInformationView=findViewById(R.id.mallpayment_shop);
        voucherView=findViewById(R.id.mallpayment_voucher);
        integralView=findViewById(R.id.mallpayment_integral);
        moneyView=findViewById(R.id.mallpayment_money);
        balanceView=findViewById(R.id.mallpayment_balance);
        payMoneyTv=findViewById(R.id.pay_money);
        submitPay=findViewById(R.id.go_pay);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        submitPay.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int id=v.getId();
        if (id==R.id.food_comment_back){
            finish();
        }else if(id==R.id.go_pay){
            setOrder();
            gotoPay();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        address=new MallPayAddressUtils(this,addressView);
        discountmoney=(new BigDecimal(goodsInfo.getGoods_price()).multiply(new BigDecimal(SinglePayment.getSinglePayment().getGoods_number()))).add(new BigDecimal(goodsInfo.getGoods_freight()));
        getwallet();
        setPayment();
        initAddress();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**初始化地址*/
    private void initAddress(){
        address.getAddress();
    }

    /**设置地址*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAddressView(AddressListBean addressBean){
        address.setAddress(addressBean);
    }

    private void setPayment(){
        title.setText("订单详情");
        goodsMoney=new BigDecimal(goodsInfo.getGoods_price()).multiply(new BigDecimal(SinglePayment.getSinglePayment().getGoods_number()));
        //设置商品信息
        MallPayShopUtils.setPayShop(this,goodsInformationView,shopInfo.getShop_name(),goodsInfo);

        SinglePayment.getSinglePayment().setGoods_id(goodsInfo.getGoods_id());
        SinglePayment.getSinglePayment().setShopid(goodsInfo.getShopid());
    }

    /**
     * 设置订单信息
     */
    private void setOrder(){
        SinglePayment.getSinglePayment().setGoodsprices("0");
        SinglePayment.getSinglePayment().setGoodsmprices(goodsMoney.toString());
        SinglePayment.getSinglePayment().setFreight_money(goodsInfo.getGoods_freight());
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
        params.put("Is_mall","1");
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                deductible=new Gson().fromJson(result,DeductibleEntity.class);
                //选择代金券
                PayCashcouponUtils.initCoupon(MallPayMentActivity.this,voucherView,deductible);
                //选择积分
                PayIntegralUtils.initIntegral(MallPayMentActivity.this,integralView,deductible);
                //余额抵扣
                PayBalanceUtils.initBalance(MallPayMentActivity.this,balanceView,deductible);
                useDiscount();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETWALLET,params);
    }

    /**
     * 使用优惠券信息
     */
    private void useDiscount(){
        setCashcoupon(discountmoney.toString(),shopInfo.getShop_type(),shopInfo.getShopid());
        BigDecimal money=discountmoney;
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
        LogUtils.e("Mallpay","money="+money.toString());
        //设置商城总额
        TotalMoneyUtils.setTotalMoney(this,moneyView,discountmoney,goodsInfo);
        payMoneyTv.setText("实付金额：￥"+money.toString());
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
        PayCashcouponUtils.isMall="1";
    }

    /**
     * 弹出选择支付方式弹窗
     */
    private void gotoPay(){
        if (payMoney!=null){
            PayTypeUtils paytype=new PayTypeUtils(this);
            paytype.submitPay(payMoney.toString(),submitPay);
        }
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
    public void callTotalMoney(BigDecimal money) {
        //需要支付的价格
        payMoney=money;
//        payMoneyTv.setText("实付金额：￥"+payMoney.toString());
        //订单初始总价格
        SinglePayment.getSinglePayment().setGoodsmprices(goodsMoney.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finish(String str){
        if ("finish".equals(str)){
            this.finish();
        }
    }
}
