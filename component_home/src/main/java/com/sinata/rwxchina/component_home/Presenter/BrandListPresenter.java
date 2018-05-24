package com.sinata.rwxchina.component_home.Presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_home.Bean.BrandBean;
import com.sinata.rwxchina.component_home.Contract.BranListContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BrandListPresenter extends RxPresenter<BranListContract.View> implements BranListContract.Presenter<BranListContract.View>{
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public BrandListPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData() {
         apiManager = new ApiManager(baseActivity, new StringCallBack() {
             @Override
             public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                 List<BrandBean> list = new Gson().fromJson(result, new TypeToken<List<BrandBean>>(){}.getType());
                 mView.showView(list);
             }

             @Override
             public void onResultError(ApiException e, String method) {

             }
         });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.BRANDLIST,map);
    }
}
