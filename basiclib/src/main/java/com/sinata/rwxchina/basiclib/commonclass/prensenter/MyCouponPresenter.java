package com.sinata.rwxchina.basiclib.commonclass.prensenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.Couponbean;
import com.sinata.rwxchina.basiclib.commonclass.contract.MyCouponContract;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class MyCouponPresenter extends RxPresenter<MyCouponContract.View> implements MyCouponContract.Presenter<MyCouponContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public MyCouponPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(@Nullable Map<String, String> params, final boolean isRefresh) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("zzz",result);
                List<Couponbean> list = null;
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() == 0) {
                    mView.showView(list, isRefresh);
                } else {
                    list = new Gson().fromJson(result, new TypeToken<List<Couponbean>>(){}.getType());
                    mView.showView(list, isRefresh);
                    if (pageInfo != null)
                        mView.getPage(pageInfo);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.COUPON, false, params);
    }

    @Override
    public void loadMore(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)) {
                    List<Couponbean> list = new Gson().fromJson(result, new TypeToken<List<Couponbean>>() {
                    }.getType());
                    mView.showLoadMore(list);
                    if (pageInfo != null) {
                        mView.getPage(pageInfo);
                    }
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
    }
}
