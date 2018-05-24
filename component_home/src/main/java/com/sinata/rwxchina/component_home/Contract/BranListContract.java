package com.sinata.rwxchina.component_home.Contract;

import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;
import com.sinata.rwxchina.component_home.Bean.BrandBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BranListContract {
    interface View extends BaseContract.BaseView{
        void showView(List<BrandBean> brandBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();
    }
}
