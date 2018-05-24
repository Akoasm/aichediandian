package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.WithDrawBean;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface WithDrawContract {
    interface View extends BaseContract.BaseView{
        void showView(WithDrawBean withDrawBean);
        void withDrawSuccess(String msg);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();
        void commitData();
    }
}
