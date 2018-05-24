package com.sinata.rwxchina.basiclib.commonclass.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/25
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class OrderDetailBean {

    /**
     * uid : 4
     * createdate :
     * user_tel : 1898989988989
     * receipt_name : 飞飞飞
     * receipt_phone : 111111111111
     * receipt_address : FDfdwffwfw
     * paydate : 2017-12-11 10:41:26
     * orderson : 12dfde9999
     * out_trade_no : 12dfde9999
     * indent_state : 90
     * before_money : 0.00
     * pay_money : 10.00
     * freight : 5.00
     * send_mode : 普通快递
     * shopid : 1194
     * is_mall : 0
     * money : 0.00
     * integral_money : 0.00
     * coupon_money : 0.00
     * goods_number :
     * goods_name : 演示套餐2（测试）
     * goods_img : /Uploads/User/1/a16ce583b86e4d4db268ac12d4af67b1.jpg
     * goods_market_price : 180
     * goods_price : 140
     * goods_labels_attr : ["随时退","过期退","免预约"]
     * goods_group_data : [{"m_meun_name":"荤菜","meun_list":[{"m_goods_name":"鲜毛肚","m_goods_number":"1份","m_goods_money":"35"},{"m_goods_name":"麻辣牛肉","m_goods_number":"1份","m_goods_money":"35"},{"m_goods_name":"虾饺","m_goods_number":"1份","m_goods_money":"25"}]},{"m_meun_name":"素菜","meun_list":[{"m_goods_name":"金针菇","m_goods_number":"1份","m_goods_money":"8"},{"m_goods_name":"木耳","m_goods_number":"1份","m_goods_money":"10"}]},{"m_meun_name":"锅底（2选1）","meun_list":[{"m_goods_name":"鸳鸯锅","m_goods_number":"1份","m_goods_money":"45"},{"m_goods_name":"牛油辣锅","m_goods_number":"份","m_goods_money":"45"}]}]
     * user_info : {"money":"0.00","integral":"0"}
     * shop_info : {"shop_address":"","shop_telephone":"","shop_name":"测试商户hp","shop_starlevel":"0","shop_lng":"","shop_lat":""}
     */

    private String uid;
    private String createdate;
    private String user_tel;
    private String receipt_name;
    private String receipt_phone;
    private String receipt_address;
    private String paydate;
    private String orderson;
    private String out_trade_no;
    private String indent_state;
    private String before_money;
    private String after_money;
    private String pay_money;
    private String freight;
    private String send_mode;
    private String shopid;
    private String is_mall;
    private String money;
    private String integral_money;
    private String coupon_money;
    private String goods_number;
    private String goods_name;
    private String goods_img;
    private String goods_market_price;
    private String goods_price;
    private UserInfoBean user_info;
    private ShopInfoBean shop_info;
    private List<String> goods_labels_attr;
    private List<GoodsGroupDataBean> goods_group_data;
    private String paytype;
    /**
     * default_image : /Uploads/goods/zizhumaidan.png
     * goods_type : 0
     * goods_labels_attr : []
     * goods_group_data : []
     * bespeak_date_toshop :
     * bespeak_date :
     * bespeak_leave_date :
     * user_info : {"money":"2833.00","integral":"0","user_head":"/Uploads/head/44617865359d9b694e9cba.jpeg"}
     */

    private String default_image;
    private String goods_type;
    private String bespeak_date_toshop;
    private String bespeak_date;
    private String bespeak_leave_date;

    public String getAfter_money() {
        return after_money;
    }

    public void setAfter_money(String after_money) {
        this.after_money = after_money;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
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

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
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

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getSend_mode() {
        return send_mode;
    }

    public void setSend_mode(String send_mode) {
        this.send_mode = send_mode;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getIs_mall() {
        return is_mall;
    }

    public void setIs_mall(String is_mall) {
        this.is_mall = is_mall;
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

    public String getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(String coupon_money) {
        this.coupon_money = coupon_money;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
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

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public ShopInfoBean getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfoBean shop_info) {
        this.shop_info = shop_info;
    }

    public List<String> getGoods_labels_attr() {
        return goods_labels_attr;
    }

    public void setGoods_labels_attr(List<String> goods_labels_attr) {
        this.goods_labels_attr = goods_labels_attr;
    }

    public List<GoodsGroupDataBean> getGoods_group_data() {
        return goods_group_data;
    }

    public void setGoods_group_data(List<GoodsGroupDataBean> goods_group_data) {
        this.goods_group_data = goods_group_data;
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "uid='" + uid + '\'' +
                ", createdate='" + createdate + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", receipt_name='" + receipt_name + '\'' +
                ", receipt_phone='" + receipt_phone + '\'' +
                ", receipt_address='" + receipt_address + '\'' +
                ", paydate='" + paydate + '\'' +
                ", orderson='" + orderson + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", indent_state='" + indent_state + '\'' +
                ", before_money='" + before_money + '\'' +
                ", pay_money='" + pay_money + '\'' +
                ", freight='" + freight + '\'' +
                ", send_mode='" + send_mode + '\'' +
                ", shopid='" + shopid + '\'' +
                ", is_mall='" + is_mall + '\'' +
                ", money='" + money + '\'' +
                ", integral_money='" + integral_money + '\'' +
                ", coupon_money='" + coupon_money + '\'' +
                ", goods_number='" + goods_number + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_img='" + goods_img + '\'' +
                ", goods_market_price='" + goods_market_price + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", user_info=" + user_info +
                ", shop_info=" + shop_info +
                ", goods_labels_attr=" + goods_labels_attr +
                ", goods_group_data=" + goods_group_data +
                ", paytype='" + paytype + '\'' +
                '}';
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getBespeak_date_toshop() {
        return bespeak_date_toshop;
    }

    public void setBespeak_date_toshop(String bespeak_date_toshop) {
        this.bespeak_date_toshop = bespeak_date_toshop;
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

    public static class UserInfoBean {
        /**
         * money : 0.00
         * integral : 0
         */

        private String money;
        private String integral;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }
    }

    public static class ShopInfoBean {
        /**
         * shop_address :
         * shop_telephone :
         * shop_name : 测试商户hp
         * shop_starlevel : 0
         * shop_lng :
         * shop_lat :
         */

        private String shop_address;
        private String shop_telephone;
        private String shop_name;
        private String shop_starlevel;
        private String shop_lng;
        private String shop_lat;
        private String shop_logo;

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_telephone() {
            return shop_telephone;
        }

        public void setShop_telephone(String shop_telephone) {
            this.shop_telephone = shop_telephone;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_starlevel() {
            return shop_starlevel;
        }

        public void setShop_starlevel(String shop_starlevel) {
            this.shop_starlevel = shop_starlevel;
        }

        public String getShop_lng() {
            return shop_lng;
        }

        public void setShop_lng(String shop_lng) {
            this.shop_lng = shop_lng;
        }

        public String getShop_lat() {
            return shop_lat;
        }

        public void setShop_lat(String shop_lat) {
            this.shop_lat = shop_lat;
        }
    }

    public static class GoodsGroupDataBean {
        /**
         * m_meun_name : 荤菜
         * meun_list : [{"m_goods_name":"鲜毛肚","m_goods_number":"1份","m_goods_money":"35"},{"m_goods_name":"麻辣牛肉","m_goods_number":"1份","m_goods_money":"35"},{"m_goods_name":"虾饺","m_goods_number":"1份","m_goods_money":"25"}]
         */

        private String m_meun_name;
        private List<MeunListBean> meun_list;

        public String getM_meun_name() {
            return m_meun_name;
        }

        public void setM_meun_name(String m_meun_name) {
            this.m_meun_name = m_meun_name;
        }

        public List<MeunListBean> getMeun_list() {
            return meun_list;
        }

        public void setMeun_list(List<MeunListBean> meun_list) {
            this.meun_list = meun_list;
        }

        public static class MeunListBean {
            /**
             * m_goods_name : 鲜毛肚
             * m_goods_number : 1份
             * m_goods_money : 35
             */

            private String m_goods_name;
            private String m_goods_number;
            private String m_goods_money;

            public String getM_goods_name() {
                return m_goods_name;
            }

            public void setM_goods_name(String m_goods_name) {
                this.m_goods_name = m_goods_name;
            }

            public String getM_goods_number() {
                return m_goods_number;
            }

            public void setM_goods_number(String m_goods_number) {
                this.m_goods_number = m_goods_number;
            }

            public String getM_goods_money() {
                return m_goods_money;
            }

            public void setM_goods_money(String m_goods_money) {
                this.m_goods_money = m_goods_money;
            }
        }
    }

}
