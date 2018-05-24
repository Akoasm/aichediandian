package com.sinata.rwxchina.component_basic.finefood.entity;

import com.sinata.rwxchina.basiclib.base.BaseGoodsInfo;
import com.sinata.rwxchina.basiclib.basic.basicGroup.GroupEntity;
import com.sinata.rwxchina.basiclib.entity.BaseShopInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 美食首页实体类
 * @modifyRecord
 */

public class FoodEntity {
    private BaseShopInfo shopinfo;
    private List<BaseGoodsInfo> goodsvolume;
    private List<BaseGoodsInfo> goodsgroup;
    private List<CharacteristicEntity> goodsfeature;
    private String goodsfeature_names;

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

    public List<BaseGoodsInfo> getGoodsgroup() {
        return goodsgroup;
    }

    public void setGoodsgroup(List<BaseGoodsInfo> goodsgroup) {
        this.goodsgroup = goodsgroup;
    }

    public List<CharacteristicEntity> getGoodsfeature() {
        return goodsfeature;
    }

    public void setGoodsfeature(List<CharacteristicEntity> goodsfeature) {
        this.goodsfeature = goodsfeature;
    }

    public String getGoodsfeature_names() {
        return goodsfeature_names;
    }

    public void setGoodsfeature_names(String goodsfeature_names) {
        this.goodsfeature_names = goodsfeature_names;
    }
}
