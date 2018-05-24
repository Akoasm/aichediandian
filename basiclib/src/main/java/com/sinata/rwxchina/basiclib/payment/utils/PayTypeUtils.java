package com.sinata.rwxchina.basiclib.payment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.R;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityOrderCancelOrCompleteActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.EntertainmentOrderBuySuccessActivity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.payment.ali.PayResult;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.utils.appUtils.ScreenUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.basiclib.view.alertdialog.AlertDialog;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 支付页面弹出支付方式支付工具类
 * @modifyRecord
 */

public class PayTypeUtils {
    private BaseActivity mC;
    private  CheckBox alipay;
    private  CheckBox weixinpay;
    /**支付宝弹窗*/
    private  PopupWindow payTypePop;
    /**订单号*/
    public static String orderson;
    private LinearLayout submitPay;
    /**支付价格*/
    private TextView payPrice;
    /**商品信息*/
    private BaseGoodsInfo goodsInfo;
    /**商品所属商铺信息*/
    private BaseShopInfo shopInfo;
    public PayTypeUtils(BaseActivity context) {
        this.mC = context;
        initView();
    }

    public BaseGoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(BaseGoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public BaseShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(BaseShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public void initView(){
        View view= LayoutInflater.from(mC).inflate(R.layout.popup_payment_method,null);
        submitPay=view.findViewById(R.id.payType_submitPay);
        //支付宝和微信支付方式点击监听
        alipay=view.findViewById(R.id.payment_method_Alipay_choose);
        weixinpay=view.findViewById(R.id.payment_method_wechat_choose);
        payPrice=view.findViewById(R.id.payType_payMoney);
        ImageView close=view.findViewById(R.id.popup_payment_method_cancle);
        //选择支付方式弹窗
        payTypePop=new PopupWindow(mC);
        payTypePop.setWidth(ScreenUtils.getScreenWidth(mC));
        payTypePop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        payTypePop.setContentView(view);
        //将这两个属性设置为false，使点击popupwindow外面其他地方会消失
        payTypePop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        payTypePop.setOutsideTouchable(true);
        payTypePop.setFocusable(true);
        payTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mC.backgroundAlpha(mC,1);
            }
        });
        //设置取消按钮
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payTypePop.isShowing()){
                    payTypePop.dismiss();
                }
            }
        });

        alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (weixinpay.isChecked()){
                    weixinpay.setChecked(false);
                }
                if (isChecked){
                    //设置支付方式为支付宝支付
                    SinglePayment.getSinglePayment().setPaytype("12");
                    LogUtils.e("PayTypeUtils","支付方式="+ SinglePayment.getSinglePayment().getPaytype());
                    alipay.setChecked(true);
                }else {
                    alipay.setChecked(false);
                }
            }
        });
        weixinpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (alipay.isChecked()){
                    alipay.setChecked(false);
                }
                if (isChecked){
                    //设置支付方式为微信支付
                    SinglePayment.getSinglePayment().setPaytype("13");
                    LogUtils.e("PayTypeUtils","支付方式="+ SinglePayment.getSinglePayment().getPaytype());
                    weixinpay.setChecked(true);
                }else {
                    weixinpay.setChecked(false);
                }
            }
        });
    }

    /**
     * 弹出支付方式弹窗
     * @param payMoney
     * @param relativeView
     */
    public void submitPay(String payMoney, View relativeView){
        payPrice.setText("￥"+payMoney);
        payTypePop.showAtLocation(relativeView, Gravity.BOTTOM,0,0);
        mC.backgroundAlpha(mC,0.3f);
        submitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(mC).builder().setTitle("提示消息")
                        .setMsg("是否前往支付界面")
                        .setPositiveButton("马上就去", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                creatOrder();
                            }
                        }).setNegativeButton("再逛逛", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setCancelable(false).show();
            }
        });
    }


    /**
     * 继续支付（再次支付）
     */
    public void againPay(String payMoney, View relativeView, final String orderson){
        payPrice.setText("￥"+payMoney);
        payTypePop.showAtLocation(relativeView, Gravity.BOTTOM,0,0);
        mC.backgroundAlpha(mC,0.3f);
        submitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(mC).builder().setTitle("提示消息")
                        .setMsg("是否前往支付界面")
                        .setPositiveButton("马上就去", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                payAgain(orderson);
                            }
                        }).setNegativeButton("再逛逛", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setCancelable(false).show();
            }
        });
    }

    /**
     * 继续支付网络请求，需要继续支付时请调用againPay()方法
     */
    private void payAgain(String orderson){
        SinglePayment payment=SinglePayment.getSinglePayment();
        Map<String,String> params=new HashMap<>();
        params.put("orderson",orderson);
        params.put("paytype",payment.getPaytype());
        ApiManager manager=new ApiManager((BaseActivity) mC, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("PayTypeUtils","再次支付："+result);
                if (payTypePop.isShowing()){
                    payTypePop.dismiss();
                }
                isPay(result);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.PAY_AGAIN,params);
    }

    /**
     * 创建订单
     */
    private  void creatOrder(){
        final SinglePayment payment= SinglePayment.getSinglePayment();
        LogUtils.e("PayTypeUtils","paytype="+payment.toString());
        Map<String,String> params=new HashMap<>();
        params.put("paytype",payment.getPaytype());
        params.put("is_youhui",payment.getIs_youhui());
        params.put("is_mall",payment.getIs_mall());
        params.put("shopid",payment.getShopid());
        params.put("goods_id",payment.getGoods_id());
        params.put("goods_number",payment.getGoods_number());
        params.put("goodsmprices",payment.getGoodsmprices());
        params.put("goodsprices",payment.getGoodsprices());
        params.put("nosale_money",payment.getNosale_money());
        params.put("couponid",payment.getCouponid());
        params.put("money",payment.getMoney());
        params.put("integral_money",payment.getIntegral_money());
        params.put("freight_money",payment.getFreight_money());
        //当订单是商城订单时，收货人，联系方式，地址，不能为空
        //当订单不是商城订单时，酒店、ktv、养生的团购商品需要填写到店时间
        if ("1".equals(payment.getIs_mall())){
            String receipt_name=payment.getReceipt_name();
            String receipt_phone=payment.getReceipt_phone();
            String receipt_address=payment.getReceipt_address();
            if (TextUtils.isEmpty(receipt_name)||TextUtils.isEmpty(receipt_phone)||TextUtils.isEmpty(receipt_address)){
                ToastUtils.showShort("请完善收货信息");
                return;
            }else {
                params.put("receipt_name",payment.getReceipt_name());
                params.put("receipt_phone",payment.getReceipt_phone());
                params.put("receipt_address",payment.getReceipt_address());
            }
        }else {
            if (shopInfo!=null&&goodsInfo!=null){
                if ("2".equals(shopInfo.getShop_type())||"4".equals(shopInfo.getShop_type())||"6".equals(shopInfo.getShop_type())){
                    if ("1".equals(goodsInfo.getGoods_is_group())){
                        String date_toshop=payment.getBespeak_date_toshop();
                        if (TextUtils.isEmpty(date_toshop)){
                            ToastUtils.showShort("请选择到店时间");
                            return;
                        }else {
                            params.put("bespeak_date_toshop",date_toshop);
                        }
                    }
                }
            }
        }
        params.put("bespeak_date",payment.getBespeak_date());
        params.put("bespeak_leave_date",payment.getBespeak_leave_date());
        ApiManager manager=new ApiManager((BaseActivity) this.mC, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("PayTypeUtils","创建订单="+result);
                if (payTypePop.isShowing()){
                    payTypePop.dismiss();
                }
                isPay(result);
            }

            @Override
            public void onResultError(ApiException e, String method) {
                LogUtils.e("PayTypeUtils","创建订单失败="+e.toString());
            }
        });
        manager.post(HttpPath.CREATORDER,params);
    }

    private void isPay(String result) throws Exception{
        SinglePayment payment= SinglePayment.getSinglePayment();
        JSONObject jsonObject=new JSONObject(result);
        orderson=jsonObject.getString("orderson");
        //是否需要继续支付（0：不需要，1：需要）
        int isneedpay=jsonObject.getInt("is_needpay");
        if (isneedpay==1){
            String sign=jsonObject.getString("sign");
            //支付宝支付
            if ("12".equals(payment.getPaytype())){
                LogUtils.e("PayTypeUtils","支付宝0");
                payAli(sign);
            }else if ("13".equals(payment.getPaytype())){//微信支付
                payWeixin(result);
            }
            if (payTypePop.isShowing()){
                payTypePop.dismiss();
            }

        }else {//不需要继续支付，直接跳转到购买完成页面
            LogUtils.e("PayTypeUtils","不需要支付");
            paySuccess();
        }
    }

    /**
     * 调起支付宝支付
     * @param orderInfo
     */
    private  void payAli(final String orderInfo){
        LogUtils.e("PayTypeUtils","支付宝1");
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                LogUtils.e("PayTypeUtils","支付宝2");
                PayTask alipay = new PayTask((Activity) mC);
                Map<String, String> result = alipay.payV2(orderInfo,true);
                LogUtils.e("PayTypeUtils",result.toString());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private  void payWeixin(String result) throws JSONException {
        JSONObject jsonObject=new JSONObject(result);//注册到微信
        IWXAPI api = WXAPIFactory.createWXAPI(mC, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
        PayReq request = new PayReq();
        request.appId = jsonObject.getString("appid");
        request.partnerId = jsonObject.getString("partnerid");
        request.prepayId= jsonObject.getString("prepayid");
        request.packageValue = jsonObject.getString("package");
        request.nonceStr= jsonObject.getString("noncestr");
        request.timeStamp= jsonObject.getString("timestamp");
        request.sign=jsonObject.getString("sign");
        api.sendReq(request);
    }

    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    EventBus.getDefault().post("finish");
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.e("PayTypeUtils","resultinfo:"+resultInfo+"   resultstatus:"+resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        paySuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        try {
                            Class cla=Class.forName("com.sinata.rwxchina.component_aboutme.activity.MyOrderActivity");
                            Intent order=new Intent(mC, cla);
                            Bundle bundle=new Bundle();
                            bundle.putInt("page", 0);
                            order.putExtras(bundle);
                            mC.startActivity(order);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        //将创建订单时需要传的参数类设置为空
                        SinglePayment.setInstance(null);
                        LogUtils.e("支付失败");
                    }
                    break;
                }
                case 2:
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 支付成功
     */
    private  void paySuccess(){
        Intent intent=null;
        Bundle bundle=new Bundle();


        //是否是商城支付（0：不是，1：是）
        if ("0".equals(SinglePayment.getSinglePayment().getIs_mall())){
            intent=new Intent(mC, EntertainmentOrderBuySuccessActivity.class);
        }else {
            intent=new Intent(mC, CommodityOrderCancelOrCompleteActivity.class);
        }
        bundle.putString("orderson",orderson);
        intent.putExtras(bundle);
        mC.startActivity(intent);
        ((BaseActivity) mC).finish();
        //将创建订单时需要传的参数类设置为空
        SinglePayment.setInstance(null);
    }
}
