package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.BankCardBean;
import com.sinata.rwxchina.component_aboutme.contract.BankCardContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BankCardPresenter extends RxPresenter<BankCardContract.View>implements BankCardContract.Presenter<BankCardContract.View>{
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public BankCardPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData() {
        ApiManager apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    List<BankCardBean> list = new Gson().fromJson(result,new TypeToken<List<BankCardBean>>(){}.getType());
                    mView.showView(list);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.BANKCARDLIST,map);
    }
}
