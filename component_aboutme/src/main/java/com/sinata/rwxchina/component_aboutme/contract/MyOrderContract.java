package com.sinata.rwxchina.component_aboutme.contract;

import android.support.annotation.Nullable;

import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.OrderBean;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/14
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface MyOrderContract {
    interface View extends BaseContract.BaseView{
        void showView(List<OrderBean> list, boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<OrderBean> list);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(@Nullable Map<String,String> params,boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
