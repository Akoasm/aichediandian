package com.sinata.rwxchina.component_aboutme.bean;

/**
 * @author:zy
 * @detetime:2017/12/28
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ConfirmInfoBean {

    /**
     * receipt_name : 飞飞飞
     * receipt_phone : 111111111111
     * receipt_address : FDfdwffwfw
     * goods_name : 演示套餐2（测试）
     * goods_img : /Uploads/User/1/a16ce583b86e4d4db268ac12d4af67b1.jpg
     * goods_market_price : 180
     * goods_price : 140
     */

    private String receipt_name;
    private String receipt_phone;
    private String receipt_address;
    private String goods_name;
    private String goods_img;
    private String goods_market_price;
    private String goods_price;
    private String goods_number;

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getReceipt_phone() {
        return receipt_phone;
    }

    public void setReceipt_phone(String receipt_phone) {
        this.receipt_phone = receipt_phone;
    }

    public String getReceipt_address() {
        return receipt_address;
    }

    public void setReceipt_address(String receipt_address) {
        this.receipt_address = receipt_address;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_market_price() {
        return goods_market_price;
    }

    public void setGoods_market_price(String goods_market_price) {
        this.goods_market_price = goods_market_price;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }
}
