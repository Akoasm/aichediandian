package com.sinata.rwxchina.basiclib.commonclass.prensenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.QRBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.OrderDetailContract;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class OrderDetailPresnter extends RxPresenter<OrderDetailContract.View> implements OrderDetailContract.Presenter<OrderDetailContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public OrderDetailPresnter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("zxc",result);
                List<OrderDetailBean> orderDetailBean = new Gson().fromJson(result,new TypeToken<List<OrderDetailBean>>(){}.getType());
                mView.showView(orderDetailBean);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.ORDERDETAIL,params);
    }

    @Override
    public void getQRCode(String orderson) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                QRBean qrBean = new Gson().fromJson(result,QRBean.class);
                mView.showQRView(qrBean);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> params = new HashMap<>();
        params.put("orderson",orderson);
        apiManager.get(HttpPath.ORDERQRCODE,params);
    }
}
