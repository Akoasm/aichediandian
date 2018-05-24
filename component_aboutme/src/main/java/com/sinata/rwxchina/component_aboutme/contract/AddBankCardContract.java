package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface AddBankCardContract {
    interface View extends BaseContract.BaseView{
        void showView();
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void commitData();
    }
}
