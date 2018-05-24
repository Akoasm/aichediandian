package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.RefundInfoBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/21
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface RefundInfoContract {
    interface View extends BaseContract.BaseView{
        void showView(RefundInfoBean refundInfoBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params);
    }
}
