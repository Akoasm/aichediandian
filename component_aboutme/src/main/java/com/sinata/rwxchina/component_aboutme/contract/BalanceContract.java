package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.BalanceBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BalanceContract {
    interface View extends BaseContract.BaseView{
        void showView(BalanceBean balanceBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        Map<String,String> addParams();
        void getData();
    }
}
