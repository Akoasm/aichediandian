package com.sinata.rwxchina.basiclib.commonclass.contract;

import com.sinata.rwxchina.basiclib.basic.basicComment.CommentEntity;
import com.sinata.rwxchina.basiclib.commonclass.Bean.ShopInfoBean;
import com.sinata.rwxchina.basiclib.utils.rxUtils.BaseContract;


import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface CommodityDetailContract {
    interface View extends BaseContract.BaseView{
        void showComment(List<CommentEntity> commentEntity);
        void showLogo(ShopInfoBean shopInfoBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getCommentData(Map<String,String> params);
        void getShopLogo(Map<String,String> params);
    }
}
