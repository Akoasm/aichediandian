package com.sinata.rwxchina.basiclib.commonclass.prensenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.basic.basicComment.CommentEntity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.commonclass.contract.CommodityDetailContract;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class CommodityDetailPresenter extends RxPresenter<CommodityDetailContract.View>implements CommodityDetailContract.Presenter<CommodityDetailContract.View>{
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public CommodityDetailPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getCommentData(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                List<CommentEntity> list = new Gson().fromJson(result,new TypeToken<List<CommentEntity>>(){}.getType());
                mView.showComment(list);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.GETCOMMENT,params);
    }

    @Override
    public void getShopLogo(Map<String, String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ShopInfoBean shopInfoBean = new Gson().fromJson(result,ShopInfoBean.class);
                mView.showLogo(shopInfoBean);
            }

            @Override
            public void onResultError(ApiException e, String method) {
            }
        });
        apiManager.get(HttpPath.SHOPDETAIL,params);

    }


    public void getData(){

    }

}
