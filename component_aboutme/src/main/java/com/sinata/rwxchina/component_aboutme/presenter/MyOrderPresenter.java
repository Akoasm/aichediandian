package com.sinata.rwxchina.component_aboutme.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;
import com.sinata.rwxchina.component_aboutme.contract.MyOrderContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/14
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class MyOrderPresenter extends RxPresenter<MyOrderContract.View> implements MyOrderContract.Presenter<MyOrderContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public MyOrderPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void     getData(Map<String,String> params, final boolean isRefresh) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("zxc",result);
                JSONArray jsonArray = new JSONArray(result);
                List<OrderBean> list = null;
                if (jsonArray.length()>0){
                     list = new Gson().fromJson(result,new TypeToken<List<OrderBean>>(){}.getType());
                     mView.showView(list,isRefresh);
                    if (pageInfo != null)
                        mView.getPage(pageInfo);
                }else{
                    mView.showView(list,isRefresh);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        if (params==null)
            params = new HashMap<>();
        apiManager.get(HttpPath.MYORDER,false,params);
    }

    @Override
    public void loadMore(Map<String, String> params) {
            apiManager = new ApiManager(baseActivity, new StringCallBack() {
                @Override
                public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    List<OrderBean> list = new Gson().fromJson(result,new TypeToken<List<OrderBean>>(){}.getType());
                    mView.showLoadMore(list);
                    if (pageInfo != null)
                        mView.getPage(pageInfo);
                }

                @Override
                public void onResultError(ApiException e, String method) {

                }
            });
        apiManager.get(HttpPath.MYORDER,params);
    }
}
