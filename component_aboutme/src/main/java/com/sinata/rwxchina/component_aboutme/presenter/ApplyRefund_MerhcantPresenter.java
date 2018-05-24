package com.sinata.rwxchina.component_aboutme.presenter;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.contract.ApplyRefund_MerhcantContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ApplyRefund_MerhcantPresenter extends RxPresenter<ApplyRefund_MerhcantContract.View> implements ApplyRefund_MerhcantContract.Presenter<ApplyRefund_MerhcantContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public ApplyRefund_MerhcantPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void postData(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                mView.showView(msg);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.APPLYREFUND,params);
    }
}
