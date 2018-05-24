package com.sinata.rwxchina.component_basic.regimen.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/15
 * @describe 养生首页数据实体类
 * @modifyRecord
 */

public class HealthEntity {
    private BaseShopInfo shopinfo;
    private List<BaseGoodsInfo> goodsvolume;
    private List<BaseGoodsInfo> goods;
    private List<BaseGoodsInfo> goodsgroup;
    private List<TechnicianEntity> shopteamson;

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

    public List<BaseGoodsInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<BaseGoodsInfo> goods) {
        this.goods = goods;
    }

    public List<BaseGoodsInfo> getGoodsgroup() {
        return goodsgroup;
    }

    public void setGoodsgroup(List<BaseGoodsInfo> goodsgroup) {
        this.goodsgroup = goodsgroup;
    }

    public List<TechnicianEntity> getShopteamson() {
        return shopteamson;
    }

    public void setShopteamson(List<TechnicianEntity> shopteamson) {
        this.shopteamson = shopteamson;
    }
}
