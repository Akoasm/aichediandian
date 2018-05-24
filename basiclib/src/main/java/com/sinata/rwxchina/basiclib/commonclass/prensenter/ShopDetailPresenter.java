package com.sinata.rwxchina.basiclib.commonclass.prensenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.ShopDetailContract;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/1/3
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ShopDetailPresenter extends RxPresenter<ShopDetailContract.View> implements ShopDetailContract.Presenter<ShopDetailContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public ShopDetailPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getTypeCommodity(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<ShopInfoBean.GoodsBean> commodityBean = new Gson().fromJson(result,new TypeToken<List<ShopInfoBean.GoodsBean>>(){}.getType());
                mView.showTypeCommodity(commodityBean);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.GETGOODLIST,params);
    }

    @Override
    public void getBrandCommodity(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<ShopInfoBean.GoodsBean> commodityBean = new Gson().fromJson(result,new TypeToken<List<ShopInfoBean.GoodsBean>>(){}.getType());
                mView.showBrandCommodity(commodityBean);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.GETGOODLIST,params);
    }
}
