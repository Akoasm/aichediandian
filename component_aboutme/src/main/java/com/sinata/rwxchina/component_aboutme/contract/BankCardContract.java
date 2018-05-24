package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.BankCardBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BankCardContract {
    interface View extends BaseContract.BaseView{
        void showView(List<BankCardBean> bankCardBeen);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();
    }
}
