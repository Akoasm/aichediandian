package com.sinata.rwxchina.component_basic.car.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;


/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 汽车服务商品实体类
 * @modifyRecord
 */

public class CarGoodsInfo extends BaseGoodsInfo{
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
