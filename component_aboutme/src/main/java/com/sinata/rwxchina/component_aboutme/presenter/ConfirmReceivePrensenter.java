package com.sinata.rwxchina.component_aboutme.presenter;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.component_aboutme.bean.ConfirmInfoBean;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.contract.ConfirmReceiveContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ConfirmReceivePrensenter extends RxPresenter<ConfirmReceiveContract.View> implements ConfirmReceiveContract.Presenter<ConfirmReceiveContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;
    @Override
    public void getData(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (msg.equals("操作成功")) {
                    ConfirmInfoBean confirmInfoBean = new Gson().fromJson(result, ConfirmInfoBean.class);
                    mView.showView(confirmInfoBean);
                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.CONFIRMRECEIVE,params);
    }

    public ConfirmReceivePrensenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
}
