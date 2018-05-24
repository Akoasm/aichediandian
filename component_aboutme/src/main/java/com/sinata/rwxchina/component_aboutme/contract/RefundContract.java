package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.RefundOrderBean;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/19
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface RefundContract {
    interface View extends BaseContract.BaseView{
        void ShowView(List<RefundOrderBean> list,boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<RefundOrderBean> list);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params, boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
