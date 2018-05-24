package com.sinata.rwxchina.component_basic.hotel.entity;

import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe 酒店首页实体类
 * @modifyRecord
 */

public class HotelEntity {
    private BaseShopInfo shopinfo;
    private List<HotelReserveEntity> goods;

    public BaseShopInfo getShopInfo() {
        return shopinfo;
    }

    public void setShopInfo(BaseShopInfo shopInfo) {
        this.shopinfo = shopInfo;
    }

    public List<HotelReserveEntity> getGoods() {
        return goods;
    }

    public void setGoods(List<HotelReserveEntity> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "shopInfo=" + shopinfo +
                ", goods=" + goods +
                '}';
    }
}
