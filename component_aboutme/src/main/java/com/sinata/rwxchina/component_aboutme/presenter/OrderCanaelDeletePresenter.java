package com.sinata.rwxchina.component_aboutme.presenter;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.basiclib.utils.toastUtils.ToastUtils;
import com.sinata.rwxchina.component_aboutme.contract.OrderCancelDeleteContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/1/2
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class OrderCanaelDeletePresenter extends RxPresenter<OrderCancelDeleteContract.View> implements OrderCancelDeleteContract.Presenter<OrderCancelDeleteContract.View>{
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public OrderCanaelDeletePresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void cancelOrder(String orderson, final int position){

      apiManager = new ApiManager(HttpPath.BASEHOST,baseActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
              mView.showView(position);
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
        Map<String,String> params = new HashMap<>();
        params.put("orderson",orderson);
        apiManager.get(HttpPath.CANCELORDER,params);
    }

    @Override
    public void deleteOrder(String orderson, final int position) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                mView.showView(position);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> params = new HashMap<>();
        params.put("orderson",orderson);
        apiManager.get(HttpPath.DELETEORDER,params);
    }
}
