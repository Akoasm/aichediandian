package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.IntegralBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface IntegralContract {
    interface View extends BaseContract.BaseView{
        void showView(IntegralBean integralBean,boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(IntegralBean integralBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
