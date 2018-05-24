package com.sinata.rwxchina.basiclib.payment.entity;

/**
 * @author HRR
 * @datetime 2018/1/5
 * @describe 优惠券信息实体类
 * @modifyRecord
 */

public class CouponEntity {


    /**
     * couponid : 2
     * money : 5
     * coupon_name : 测试礼包
     * coupon_describe : 测试cdcdcdcdcdcd
     * end_time : 2026-03-24
     */

    private String couponid;
    private String money;
    private String coupon_name;
    private String coupon_describe;
    private String end_time;

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_describe() {
        return coupon_describe;
    }

    public void setCoupon_describe(String coupon_describe) {
        this.coupon_describe = coupon_describe;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
