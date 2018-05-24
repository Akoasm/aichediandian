package com.sinata.rwxchina.component_aboutme.presenter;

import com.sinata.rwxchina.basiclib.HttpPath;
import com.sinata.rwxchina.basiclib.base.BaseActivity;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.ApiManager;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.RxPresenter;
import com.sinata.rwxchina.component_aboutme.contract.AddBankCardContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class AddBankCardPresenter extends RxPresenter<AddBankCardContract.View> implements AddBankCardContract.Presenter<AddBankCardContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;
    private Params params;
    public interface Params{
        Map<String,String> addParams();
    }

    public AddBankCardPresenter(BaseActivity baseActivity,Params params) {
        this.baseActivity = baseActivity;
        this.params = params;
    }

    @Override
    public void commitData() {
      apiManager = new ApiManager(baseActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
              mView.showView();
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
        apiManager.post(HttpPath.ADDBANKCARD,params.addParams());
    }
}
