package com.sinata.rwxchina.component_home.Presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.logUtils.LogUtils;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_home.Contract.BrandActivitiesContract;
import com.sinata.rwxchina.component_home.entity.HotEntity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BrandActivitiesPresenter extends RxPresenter<BrandActivitiesContract.View> implements BrandActivitiesContract.Presenter<BrandActivitiesContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public BrandActivitiesPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(Map<String, String> params,final boolean isRefresh) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("zxc",result);
                JSONArray jsonArray = new JSONArray(result);
                List<HotEntity> list = null;
                if (jsonArray.length() > 0) {
                    list = new Gson().fromJson(result, new TypeToken<List<HotEntity>>() {
                    }.getType());
                    mView.showView(list, isRefresh);
                    if (pageInfo != null)
                        mView.getPage(pageInfo);
                } else {
                    mView.showView(list, isRefresh);
                }

            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.BRANDACTIVITIES, params);
    }

    @Override
    public void loadMore(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<HotEntity> list = new Gson().fromJson(result, new TypeToken<List<HotEntity>>() {
                }.getType());
                mView.showLoadMore(list);
                if (pageInfo != null)
                    mView.getPage(pageInfo);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.BRANDACTIVITIES, params);
    }
}
