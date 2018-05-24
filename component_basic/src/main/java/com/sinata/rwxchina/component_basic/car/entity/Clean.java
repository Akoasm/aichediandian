package com.sinata.rwxchina.component_basic.car.entity;

/**
 * @author HRR
 * @datetime 2017/11/14
 * @describe 同洗车美容等商品父类
 * @modifyRecord
 */

public class Clean {
    /**
     * goods_unit : 次
     * goods_name : 洗车
     * goods_price : 188
     * goods_market_price :
     * goods_id : 1
     */

    private String goods_unit;
    private String goods_name;
    private String goods_price;
    private String goods_market_price;
    private String goods_id;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_market_price() {
        return goods_market_price;
    }

    public void setGoods_market_price(String goods_market_price) {
        this.goods_market_price = goods_market_price;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public String toString() {
        return "Clean{" +
                "goods_unit='" + goods_unit + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_market_price='" + goods_market_price + '\'' +
                ", goods_id='" + goods_id + '\'' +
                '}';
    }
}
