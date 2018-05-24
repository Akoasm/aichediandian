package com.sinata.rwxchina.basiclib.payment.entity;

/**
 * @author HRR
 * @datetime 2017/12/28
 * @describe 创建订单时请求参数实体类
 * @modifyRecord
 */

public class SinglePayment {
    /**付款方式（12-支付宝，13-微信）*/
    private String paytype;
    /**是否为自主买单(0-不是 1-是),默认0*/
    private String is_youhui="0";
    /**是否为商城订单(0-不是 1-是),默认 0*/
    private String is_mall="0";
    /**店铺ID*/
    private String shopid="";
    /**购买的商品ID*/
    private String goods_id="";
    /**购买的商品数量(默认为1)*/
    private String goods_number="1";
    /**所有商品原始总价格(若是自助买单,则是用输入的原始总价格),正数*/
    private String goodsmprices="";
    /**所有商品优惠总价格(若是自助买单,则是折扣后的价格),正数*/
    private String goodsprices="";
    /**自助买单,不打折的金额,单位元*/
    private String nosale_money="";
    /**优惠卷ID( 若用户选择了优惠卷时 )*/
    private String couponid="";
    /**余额抵扣( 若用户选择了余额抵扣时 ),正数,如 5.5*/
    private String money="";
    /**积分抵扣( 若用户选择了积分抵扣时 ),正数,如 6*/
    private String integral_money="";
    /**运费（购买商城商品时才需要必传）*/
    private String freight_money="";
    /**收货人姓名（购买商城商品时才需要必传）*/
    private String receipt_name="";
    /**收货人联系电话（购买商城商品时才需要必传）*/
    private String receipt_phone="";
    /**收货地址（购买商城商品时才需要必传）*/
    private String receipt_address="";
    /**用户预约到商店的时间,如 2018-01-10 22:00*/
    private String bespeak_date_toshop="";
    /**预约到店时间*/
    private String bespeak_date="";
    /**预约离店时间*/
    private String bespeak_leave_date="";

    /**volatile关键字用来禁止指令重排序优化*/
    private static volatile SinglePayment instance = null;

    private SinglePayment(){}

    public static SinglePayment getSinglePayment(){
        if (instance == null) {
            synchronized (SinglePayment.class) {
                if (instance == null) {
                    instance = new SinglePayment();
                }
            }
        }
        return instance;
    }

    public static void setInstance(SinglePayment instance) {
        SinglePayment.instance = instance;
    }

    public String getBespeak_date_toshop() {
        return bespeak_date_toshop;
    }

    public void setBespeak_date_toshop(String bespeak_date_toshop) {
        this.bespeak_date_toshop = bespeak_date_toshop;
    }

    public String getNosale_money() {
        return nosale_money;
    }

    public void setNosale_money(String nosale_money) {
        this.nosale_money = nosale_money;
    }

    public String getBespeak_date() {
        return bespeak_date;
    }

    public void setBespeak_date(String bespeak_date) {
        this.bespeak_date = bespeak_date;
    }

    public String getBespeak_leave_date() {
        return bespeak_leave_date;
    }

    public void setBespeak_leave_date(String bespeak_leave_date) {
        this.bespeak_leave_date = bespeak_leave_date;
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getIs_youhui() {
        return is_youhui;
    }

    public void setIs_youhui(String is_youhui) {
        this.is_youhui = is_youhui;
    }

    public String getIs_mall() {
        return is_mall;
    }

    public void setIs_mall(String is_mall) {
        this.is_mall = is_mall;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getGoodsmprices() {
        return goodsmprices;
    }

    public void setGoodsmprices(String goodsmprices) {
        this.goodsmprices = goodsmprices;
    }

    public String getGoodsprices() {
        return goodsprices;
    }

    public void setGoodsprices(String goodsprices) {
        this.goodsprices = goodsprices;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getIntegral_money() {
        return integral_money;
    }

    public void setIntegral_money(String integral_money) {
        this.integral_money = integral_money;
    }

    public String getFreight_money() {
        return freight_money;
    }

    public void setFreight_money(String freight_money) {
        this.freight_money = freight_money;
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

    @Override
    public String toString() {
        return "SinglePayment{" +
                "paytype='" + paytype + '\'' +
                ", is_youhui='" + is_youhui + '\'' +
                ", is_mall='" + is_mall + '\'' +
                ", shopid='" + shopid + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_number='" + goods_number + '\'' +
                ", goodsmprices='" + goodsmprices + '\'' +
                ", goodsprices='" + goodsprices + '\'' +
                ", nosale_money='" + nosale_money + '\'' +
                ", couponid='" + couponid + '\'' +
                ", money='" + money + '\'' +
                ", integral_money='" + integral_money + '\'' +
                ", freight_money='" + freight_money + '\'' +
                ", receipt_name='" + receipt_name + '\'' +
                ", receipt_phone='" + receipt_phone + '\'' +
                ", receipt_address='" + receipt_address + '\'' +
                ", bespeak_date='" + bespeak_date + '\'' +
                ", bespeak_leave_date='" + bespeak_leave_date + '\'' +
                '}';
    }
}
