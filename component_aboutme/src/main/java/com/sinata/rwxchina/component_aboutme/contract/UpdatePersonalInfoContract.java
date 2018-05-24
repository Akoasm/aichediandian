package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface UpdatePersonalInfoContract {
    interface View extends BaseContract.BaseView{
        void showView (String s);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void updateInfo(Map<String,String> params, List<File> files);
    }
}
