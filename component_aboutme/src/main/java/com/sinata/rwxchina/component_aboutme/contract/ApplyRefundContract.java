package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface ApplyRefundContract {
    interface View extends BaseContract.BaseView{
        void showView(String s);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void postData(Map<String,String> param, List<File> params);
    }
}
