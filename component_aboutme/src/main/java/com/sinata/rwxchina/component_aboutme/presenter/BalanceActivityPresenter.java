package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.BalanceBean;
import com.sinata.rwxchina.component_aboutme.contract.BalanceContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceActivityPresenter extends RxPresenter<BalanceContract.View> implements BalanceContract.Presenter<BalanceContract.View>{
    private ApiManager apiManager;
    private BaseActivity appCompatActivity;

    public BalanceActivityPresenter(BaseActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public Map<String, String> addParams() {
        Map<String,String> map = new HashMap<>();
        return map;
    }

    @Override
    public void getData() {
      apiManager = new ApiManager(appCompatActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws JSONException {
                 if (!TextUtils.isEmpty(result)){
                     BalanceBean balanceBean = new Gson().fromJson(result,BalanceBean.class);
                     mView.showView(balanceBean);
                 }
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
        apiManager.get(HttpPath.MYWALLET,addParams());
    }
}
