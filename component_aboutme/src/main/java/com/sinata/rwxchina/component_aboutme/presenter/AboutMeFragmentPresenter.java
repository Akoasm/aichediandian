package com.sinata.rwxchina.component_aboutme.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.component_aboutme.bean.AboutMeFragmentFunctionBean;
import com.sinata.rwxchina.component_aboutme.bean.PersonalBean;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.contract.AboutMeFragmentContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author:zy
 * @detetime:2017/11/13
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class AboutMeFragmentPresenter extends RxPresenter<AboutMeFragmentContract.View> implements AboutMeFragmentContract.Presenter<AboutMeFragmentContract.View>  {
    private ApiManager apiManager;
    private BaseActivity appCompatActivity;

    public AboutMeFragmentPresenter(BaseActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public Map<String,String> addParams() {
        Map<String,String> params = new HashMap();
        return params;
    }

    @Override
    public void getPersonalData() {
        apiManager = new ApiManager(appCompatActivity, new StringCallBack() {

            @Override
            public void onResultNext(String resulte, String method, int code,String msg, PageInfo pageInfo) {
                LogUtils.e("zzz",resulte);
                if (code!=0)
                    mView.showLoginView();
                if (code==0&&!TextUtils.isEmpty(resulte)) {
                        PersonalBean personalBean = new Gson().fromJson(resulte, PersonalBean.class);
                        mView.showView(personalBean);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {
                LogUtils.e(e.toString());
            }
        });
        apiManager.get(HttpPath.PERSONALINFO,addParams());
    }

    @Override
    public void getIcon() {
        apiManager = new ApiManager(appCompatActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws JSONException {
                if (!TextUtils.isEmpty(result)){
                    List<AboutMeFragmentFunctionBean> list = new Gson().fromJson(result,new TypeToken<List<AboutMeFragmentFunctionBean>>(){}.getType());
//                    LogUtils.e("zzz",list.get(0).toString());
                    mView.ShowFunction(list);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {
                LogUtils.e(e.toString());
            }
        });
        Map<String,String> map = new HashMap<>();
        map.put("cityid","1");
        apiManager.get(HttpPath.GETFUCNTIONICON,map);
    }
}
