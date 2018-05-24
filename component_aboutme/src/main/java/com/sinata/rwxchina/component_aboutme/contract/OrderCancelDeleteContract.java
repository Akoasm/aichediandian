package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

/**
 * @author:zy
 * @detetime:2018/1/2
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface OrderCancelDeleteContract {
    interface View extends BaseContract.BaseView{
        void showView(int position);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void cancelOrder(String orderson,int position);
        void deleteOrder(String orderson,int position);
    }
}
