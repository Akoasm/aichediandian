package com.sinata.rwxchina.basiclib.payment.entity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/1/5
 * @describe 优惠券，积分，余额抵扣信息实体类
 * @modifyRecord
 */

public class DeductibleEntity {

    /**
     * coupon : {"count":4,"list":[{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"}]}
     * integral : 10000000
     * money : 10000000.00
     */

    private CouponBean coupon;
    private String integral;
    private String money;
    private String integral_deduction;
    public CouponBean getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponBean coupon) {
        this.coupon = coupon;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getIntegral_deduction() {
        return integral_deduction;
    }

    public void setIntegral_deduction(String integral_deduction) {
        this.integral_deduction = integral_deduction;
    }

    public static class CouponBean {
        /**
         * count : 4
         * list : [{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"},{"couponid":"2","money":"5","coupon_name":"测试礼包","coupon_describe":"测试\r\ncdcdcdcd\r\ncdcd","end_time":"2026-03-24"}]
         */

        private int count;
        private List<CouponEntity> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CouponEntity> getList() {
            return list;
        }

        public void setList(List<CouponEntity> list) {
            this.list = list;
        }

    }
}
