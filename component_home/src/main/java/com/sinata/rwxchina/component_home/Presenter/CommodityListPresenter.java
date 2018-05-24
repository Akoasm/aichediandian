package com.sinata.rwxchina.component_home.Presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_home.Contract.CommodityListContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class CommodityListPresenter extends RxPresenter<CommodityListContract.View> implements CommodityListContract.Presenter<CommodityListContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public CommodityListPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(Map params, final boolean isRefresh) {
       apiManager = new ApiManager(baseActivity, new StringCallBack() {
           @Override
           public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
               JSONArray array = new JSONArray(result);
               List<CommodityBean> listBeen = null;
               if (array.length()>0) {
                   listBeen = new Gson().fromJson(result, new TypeToken<List<CommodityBean>>() {
                   }.getType());
                   mView.showView(listBeen);
                   if (pageInfo != null) {
                       mView.getPage(pageInfo);
                   }
               }else {
                   mView.showView(listBeen);
               }
               mView.stopRefresh(isRefresh);
           }

           @Override
           public void onResultError(ApiException e, String method) {
               mView.stopRefresh(isRefresh);
           }

       });
        apiManager.get(HttpPath.GETGOODLIST,params);
    }
    @Override
    public void loadMore(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    List<CommodityBean> list = new Gson().fromJson(result,new TypeToken<List<CommodityBean>>(){}.getType());
                    mView.showLoadMore(list);
                    if (pageInfo!=null){
                        mView.getPage(pageInfo);
                    }

            }

            @Override
            public void onResultError(ApiException e, String method) {
            }
        });
        apiManager.get(HttpPath.GETGOODLIST,params);
    }
}
