package com.sinata.rwxchina.component_home.entity;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 首页品牌区实体类
 * @modifyRecord
 */

public class BrandEntity {

    /**
     * goods_type : 10010
     * name : 轮胎
     * logo : /Uploads/App/goodstype/1.jpg
     * describe : 轮子就是好呀
     */

    private String goods_type;
    private String name;
    private String logo;
    private String describe;

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "goods_type='" + goods_type + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
