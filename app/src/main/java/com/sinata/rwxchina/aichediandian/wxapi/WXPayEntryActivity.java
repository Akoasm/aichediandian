package com.sinata.rwxchina.aichediandian.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.CommodityOrderCancelOrCompleteActivity;
import com.sinata.rwxchina.basiclib.commonclass.activity.EntertainmentOrderBuySuccessActivity;
import com.sinata.rwxchina.basiclib.payment.entity.SinglePayment;
import com.sinata.rwxchina.basiclib.payment.utils.PayTypeUtils;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.activity.MyOrderActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Administrator
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), WXPayEntryActivity.this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		EventBus.getDefault().post("finsh");
		if (resp.errCode==0){
			LogUtils.e("支付成功");
			Intent intent=null;
			Bundle bundle=new Bundle();
			//是否是商城支付（0：不是，1：是）
			if ("0".equals(SinglePayment.getSinglePayment().getIs_mall())){
				intent=new Intent(this, EntertainmentOrderBuySuccessActivity.class);
			}else {
				intent=new Intent(this, CommodityOrderCancelOrCompleteActivity.class);
			}
			bundle.putString("orderson",PayTypeUtils.orderson);
			intent.putExtras(bundle);
			startActivity(intent);
			EventBus.getDefault().post("finish");
			finish();
			//将创建订单时需要传的参数类设置为空
			SinglePayment.setInstance(null);
		}else if (resp.errCode==-1){
			Intent order=new Intent(this, MyOrderActivity.class);
			Bundle bundle=new Bundle();
			bundle.putInt("page", 0);
			order.putExtras(bundle);
			startActivity(order);
			EventBus.getDefault().post("finish");
			finish();
			LogUtils.e("支付失败");
		}else if (resp.errCode==-2){
			Intent order=new Intent(this, MyOrderActivity.class);
			Bundle bundle=new Bundle();
			bundle.putInt("page", 0);
			order.putExtras(bundle);
			startActivity(order);
			EventBus.getDefault().post("finish");
			finish();
			LogUtils.e("用户取消");
		}
	}

}