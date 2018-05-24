package com.sinata.rwxchina.basiclib.commonclass.contract;

import android.support.annotation.Nullable;

import com.sinata.rwxchina.basiclib.commonclass.Bean.Couponbean;
import com.sinata.rwxchina.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/27
 * @describe：类描述
 * @modifyRecord:修改记录s
 */

public interface MyCouponContract {
    interface View extends BaseContract.BaseView{
        void showView(List<Couponbean> list, boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<Couponbean> list);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(@Nullable Map<String,String> params, boolean isRefresh);
        void loadMore(Map<String,String> params);
    }
}
