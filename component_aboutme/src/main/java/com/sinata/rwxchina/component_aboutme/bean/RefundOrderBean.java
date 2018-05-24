package com.sinata.rwxchina.component_aboutme.bean;

import java.io.Serializable;

/**
 * @author:zy
 * @detetime:2017/12/26
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class RefundOrderBean implements Serializable{

    /**
     * payer_account :
     * payer_name :
     * paytype : 0
     * pay_money : 0.00
     * goods_number :
     * orderson : dingdanceshi
     * out_trade_no : dingdanceshi
     * indent_state : 51
     * before_money : 0.00
     * goods_name : 演示套餐2（测试）
     * goods_img : /Uploads/User/1/a16ce583b86e4d4db268ac12d4af67b1.jpg
     * goods_market_price : 180
     * goods_price : 140
     */

    private String payer_account;
    private String payer_name;
    private String paytype;
    private String pay_money;
    private String goods_number;
    private String orderson;
    private String out_trade_no;
    private String indent_state;
    private String before_money;
    private String goods_name;
    private String goods_img;
    private String goods_market_price;
    private String goods_price;
    private String is_mall;

    public String getIs_mall() {
        return is_mall;
    }

    public void setIs_mall(String is_mall) {
        this.is_mall = is_mall;
    }

    public String getPayer_account() {
        return payer_account;
    }

    public void setPayer_account(String payer_account) {
        this.payer_account = payer_account;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getOrderson() {
        return orderson;
    }

    public void setOrderson(String orderson) {
        this.orderson = orderson;
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
}
