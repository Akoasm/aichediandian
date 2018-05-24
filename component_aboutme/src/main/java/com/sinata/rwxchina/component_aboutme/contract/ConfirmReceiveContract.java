package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.component_aboutme.bean.ConfirmInfoBean;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface ConfirmReceiveContract {
    interface View extends BaseContract.BaseView{
        void showView(ConfirmInfoBean confirmInfoBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params);
    }
}
