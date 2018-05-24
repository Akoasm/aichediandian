package com.sinata.rwxchina.component_basic.ktv.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe KTV首页实体类
 * @modifyRecord
 */

public class KTVEntity {
    private BaseShopInfo shopinfo;
    private List<BaseGoodsInfo> goodsvolume;
    private List<DateEntity> goods_date;
    private List<KTVReserveEntity> goods;
    private List<BaseGoodsInfo> goodsgroup;

    public BaseShopInfo getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(BaseShopInfo shopinfo) {
        this.shopinfo = shopinfo;
    }

    public List<BaseGoodsInfo> getGoodsvolume() {
        return goodsvolume;
    }

    public void setGoodsvolume(List<BaseGoodsInfo> goodsvolume) {
        this.goodsvolume = goodsvolume;
    }

    public List<DateEntity> getGoods_date() {
        return goods_date;
    }

    public void setGoods_date(List<DateEntity> goods_date) {
        this.goods_date = goods_date;
    }

    public List<KTVReserveEntity> getGoods() {
        return goods;
    }

    public void setGoods(List<KTVReserveEntity> goods) {
        this.goods = goods;
    }

    public List<BaseGoodsInfo> getGoodsgroup() {
        return goodsgroup;
    }

    public void setGoodsgroup(List<BaseGoodsInfo> goodsgroup) {
        this.goodsgroup = goodsgroup;
    }
}
