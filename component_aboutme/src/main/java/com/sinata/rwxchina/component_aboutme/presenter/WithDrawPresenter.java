package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.WithDrawBean;
import com.sinata.rwxchina.component_aboutme.contract.WithDrawContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithDrawPresenter extends RxPresenter<WithDrawContract.View>implements WithDrawContract.Presenter<WithDrawContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;
    private Params params;

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public WithDrawPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public interface Params{
        Map<String,String> addParams();
    }
    @Override
    public void getData() {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {

                if (!TextUtils.isEmpty(result)) {
                    WithDrawBean withDrawBean = new Gson().fromJson(result, WithDrawBean.class);
                    mView.showView(withDrawBean);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {
                LogUtils.e(e.toString());
            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.WITHDRAWDATA,map);
    }

    @Override
    public void commitData() {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                  mView.withDrawSuccess(msg);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.WITHDRAW,params.addParams());
    }
}
