package com.sinata.rwxchina.component_basic.car.entity;

import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 汽车服务商铺实体类
 * @modifyRecord
 */

public class CarShopInfo {
    private BaseShopInfo shopinfo;
    private List<CarClassfiyInfo> goods;

    public BaseShopInfo getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(BaseShopInfo shopinfo) {
        this.shopinfo = shopinfo;
    }

    public List<CarClassfiyInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<CarClassfiyInfo> goods) {
        this.goods = goods;
    }
}
