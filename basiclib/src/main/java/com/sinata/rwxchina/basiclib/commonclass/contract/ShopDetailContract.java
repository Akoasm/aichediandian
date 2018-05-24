package com.sinata.rwxchina.basiclib.commonclass.contract;

import com.sinata.rwxchina.basiclib.commonclass.Bean.CommodityBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/1/3
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface ShopDetailContract {
    interface View extends BaseContract.BaseView {
        void showTypeCommodity(List<ShopInfoBean.GoodsBean> commodityBean);

        void showBrandCommodity(List<ShopInfoBean.GoodsBean> commodityBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
         void getTypeCommodity(Map<String,String> params);
        void getBrandCommodity(Map<String,String> params);
    }
}
