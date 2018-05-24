package com.sinata.rwxchina.component_home.entity;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 首页精品特卖实体类
 * @modifyRecord
 */

public class BoutiqueEntity {

    /**
     * goods_id : 1
     * shopid : 2
     * boutique_tilte : 精品特卖1
     * default_image : /Uploads/_old/advs/33e15f207e19a04ae7f92aabd51267ef.png
     */

    private String goods_id;
    private String shopid;
    private String boutique_tilte;
    private String default_image;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getBoutique_tilte() {
        return boutique_tilte;
    }

    public void setBoutique_tilte(String boutique_tilte) {
        this.boutique_tilte = boutique_tilte;
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    @Override
    public String toString() {
        return "BoutiqueEntity{" +
                "goods_id='" + goods_id + '\'' +
                ", shopid='" + shopid + '\'' +
                ", boutique_tilte='" + boutique_tilte + '\'' +
                ", default_image='" + default_image + '\'' +
                '}';
    }
}
