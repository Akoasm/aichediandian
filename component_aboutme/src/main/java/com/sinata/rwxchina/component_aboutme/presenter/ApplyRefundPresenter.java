package com.sinata.rwxchina.component_aboutme.presenter;

import android.support.annotation.Nullable;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.activity.RefundingActivity;
import com.sinata.rwxchina.component_aboutme.contract.ApplyRefundContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.sinata.rwxchina.basiclib.utils.uploadfileutils.CreateRequestBody.CreateRequestBody;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ApplyRefundPresenter extends RxPresenter<ApplyRefundContract.View> implements ApplyRefundContract.Presenter<ApplyRefundContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public ApplyRefundPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void postData(Map<String, String> param, @Nullable List<File> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                mView.showView(msg);
                if (msg.equals("操作成功")) {
                    baseActivity.startActivity(RefundingActivity.class);
                    baseActivity.finish();
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        if (params != null)
            apiManager.postFile(HttpPath.APPLYREFUND, param, CreateRequestBody(params));
        else
            apiManager.post(HttpPath.APPLYREFUND, param);
    }


}
