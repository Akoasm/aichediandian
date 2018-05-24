package com.sinata.rwxchina.basiclib.commonclass.contract;


import com.sinata.rwxchina.basiclib.commonclass.Bean.OrderDetailBean;
import com.sinata.rwxchina.basiclib.commonclass.Bean.QRBean;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface OrderDetailContract {
    interface View extends BaseContract.BaseView{
       void showView(List<OrderDetailBean> entertainmentOrderDetailBean);
        void showQRView(QRBean qrBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String,String> params);
        void getQRCode(String orderson);
    }

}
