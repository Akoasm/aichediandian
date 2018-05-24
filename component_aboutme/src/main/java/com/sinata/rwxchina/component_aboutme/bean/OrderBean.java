package com.sinata.rwxchina.component_aboutme.bean;

import java.io.Serializable;

/**
 * @author:zy
 * @detetime:2017/12/14
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class OrderBean implements Serializable{


    /**
     * is_mall : 0
     * orderson : dindanceshi
     * pay_money : 50
     * out_trade_no : dindanceshi
     * indent_state : 50
     * before_money : 0.00
     * goods_name : 演示商品
     * goods_img :
     * goods_market_price : 100
     * goods_price : 10
     * goods_number : 2
     */

    private String is_mall;
    private String orderson;
    private String pay_money;
    private String out_trade_no;
    private String indent_state;
    private String before_money;
    private String goods_name;
    private String goods_img;
    private String goods_market_price;
    private String goods_price;
    private String goods_number;
    private String goods_id;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getIs_mall() {
        return is_mall;
    }

    public void setIs_mall(String is_mall) {
        this.is_mall = is_mall;
    }

    public String getOrderson() {
        return orderson;
    }

    public void setOrderson(String orderson) {
        this.orderson = orderson;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getIndent_state() {
        return indent_state;
    }

    public void setIndent_state(String indent_state) {
        this.indent_state = indent_state;
    }

    public String getBefore_money() {
        return before_money;
    }

    public void setBefore_money(String before_money) {
        this.before_money = before_money;
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

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }
}
