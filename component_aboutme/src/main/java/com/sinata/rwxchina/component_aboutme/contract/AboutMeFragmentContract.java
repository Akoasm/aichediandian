package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.component_aboutme.bean.AboutMeFragmentFunctionBean;
import com.sinata.rwxchina.component_aboutme.bean.PersonalBean;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/13
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface AboutMeFragmentContract {
    interface View extends BaseContract.BaseView{
        void showView(PersonalBean personalBean);
        void showLoginView();
        void ShowFunction(List<AboutMeFragmentFunctionBean> list);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        Map<String,String> addParams();
        void getPersonalData();
        void getIcon();
    }
}
