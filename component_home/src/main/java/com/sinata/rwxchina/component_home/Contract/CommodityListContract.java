package com.sinata.rwxchina.component_home.Contract;

import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface CommodityListContract {
    interface View extends BaseContract.BaseView{
        void showView(List<CommodityBean> commodityListBean);
        void stopRefresh(boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<CommodityBean> commodityListBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params,boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
