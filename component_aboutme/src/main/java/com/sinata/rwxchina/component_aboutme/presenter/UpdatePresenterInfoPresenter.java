package com.sinata.rwxchina.component_aboutme.presenter;

import android.support.annotation.Nullable;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.basiclib.utils.uploadfileutils.CreateRequestBody;
import com.sinata.rwxchina.component_aboutme.contract.UpdatePersonalInfoContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/1/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class UpdatePresenterInfoPresenter extends RxPresenter<UpdatePersonalInfoContract.View> implements UpdatePersonalInfoContract.Presenter<UpdatePersonalInfoContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    @Override
    public void updateInfo(Map<String, String> params, @Nullable List<File> list) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                mView.showView(msg);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        if (list == null)
            apiManager.post(HttpPath.UPDATEPERSONALINFO, params);
        else
            apiManager.postFile(HttpPath.UPDATEPERSONALINFO, params, CreateRequestBody.CreateRequestBody(list));
    }

    public UpdatePresenterInfoPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
}
