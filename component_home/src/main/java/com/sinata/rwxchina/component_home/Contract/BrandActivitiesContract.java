package com.sinata.rwxchina.component_home.Contract;

import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_home.entity.HotEntity;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/20
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BrandActivitiesContract {
    interface View extends BaseContract.BaseView{
        void showView(List<HotEntity> list,boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<HotEntity> list);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params,boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
