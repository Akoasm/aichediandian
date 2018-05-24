package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.IntegralBean;
import com.sinata.rwxchina.component_aboutme.contract.IntegralContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class IntegralPresenter extends RxPresenter<IntegralContract.View> implements IntegralContract.Presenter<IntegralContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;
    @Override
    public void getData(final boolean isRefresh) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                IntegralBean integralBean = new Gson().fromJson(result,IntegralBean.class);
                if (TextUtils.isEmpty(integralBean.getIntegral()))
                    integralBean.setIntegral("0");
                if (integralBean.getIntegral_list().size()>0){
                    mView.showView(integralBean,isRefresh);
                    if (pageInfo!=null)
                        mView.getPage(pageInfo);
                }else {
                    mView.showView(integralBean,isRefresh);
                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.INTEGRAL,map);
    }

    @Override
    public void loadMore(Map<String, String> params) {
            apiManager = new ApiManager(baseActivity, new StringCallBack() {
                @Override
                public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    IntegralBean integralBean = new Gson().fromJson(result,IntegralBean.class);
                    mView.showLoadMore(integralBean);
                    if (pageInfo!=null)
                        mView.getPage(pageInfo);
                }

                @Override
                public void onResultError(ApiException e, String method) {

                }
            });
         apiManager.get(HttpPath.INTEGRAL,params);
    }

    public IntegralPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
}
