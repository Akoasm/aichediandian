package com.sinata.rwxchina.component_aboutme.contract;

import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_aboutme.bean.BalanceDetailBean;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BalanceDetailContract {
    interface View extends BaseContract.BaseView{
        void showView(List<BalanceDetailBean> balanceDetailBeen,boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<BalanceDetailBean> balanceDetailBeen);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
