package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface ApplyRefund_MerhcantContract  {
     interface View extends BaseContract.BaseView{
         void showView (String s);
     }
     interface Presenter<T> extends BaseContract.BasePresenter<T>{
         void postData(Map<String,String> params);
     }
}
