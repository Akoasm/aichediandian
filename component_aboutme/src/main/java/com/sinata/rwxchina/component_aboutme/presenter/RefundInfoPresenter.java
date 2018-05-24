package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.RefundInfoBean;
import com.sinata.rwxchina.component_aboutme.contract.RefundInfoContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class RefundInfoPresenter extends RxPresenter<RefundInfoContract.View> implements RefundInfoContract.Presenter<RefundInfoContract.View>{
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public RefundInfoPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    RefundInfoBean refundInfoBean = new Gson().fromJson(result,RefundInfoBean.class);
                    mView.showView(refundInfoBean);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.REFUNDSTATUS,params);
    }
}