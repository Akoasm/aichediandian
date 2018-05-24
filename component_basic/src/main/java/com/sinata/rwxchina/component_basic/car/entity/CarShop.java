package com.sinata.rwxchina.component_basic.car.entity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/11/13
 * @describe 汽车商铺实体类
 * @modifyRecord
 */

public class CarShop {

    /**
     * wash : [{"goods_unit":"次","goods_name":"洗车2","goods_price":"","goods_market_price":"","goods_id":"9"}]
     * shop : {"shop_lat":"30.100538","shopid":"7","shop_lng":"104.007895","shop_show":"","shop_address":"成都市成华区","shop_starlevel":"0","shop_telephone":"18888888","shop_name":"成都汽修"}
     * repair : [{"goods_unit":"次","goods_name":"洗车","goods_price":"188","goods_market_price":"","goods_id":"1"}]
     * cosmetology : [{"goods_unit":"次","goods_name":"汽车美容","goods_price":"","goods_market_price":"","goods_id":"7"}]
     * maintain : [{"goods_unit":"次","goods_name":"洗车","goods_price":"188","goods_market_price":"","goods_id":"1"}]
     */

    private ShopBean shop;
    private List<WashBean> wash;
    private List<RepairBean> repair;
    private List<CosmetologyBean> cosmetology;
    private List<MaintainBean> maintain;

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public List<WashBean> getWash() {
        return wash;
    }

    public void setWash(List<WashBean> wash) {
        this.wash = wash;
    }

    public List<RepairBean> getRepair() {
        return repair;
    }

    public void setRepair(List<RepairBean> repair) {
        this.repair = repair;
    }

    public List<CosmetologyBean> getCosmetology() {
        return cosmetology;
    }

    public void setCosmetology(List<CosmetologyBean> cosmetology) {
        this.cosmetology = cosmetology;
    }

    public List<MaintainBean> getMaintain() {
        return maintain;
    }

    public void setMaintain(List<MaintainBean> maintain) {
        this.maintain = maintain;
    }

    public static class ShopBean {
        /**
         * shop_lat : 30.100538
         * shopid : 7
         * shop_lng : 104.007895
         * shop_show :
         * shop_address : 成都市成华区
         * shop_starlevel : 0
         * shop_telephone : 18888888
         * shop_name : 成都汽修
         */

        private String shop_lat;
        private String shopid;
        private String shop_lng;
        private String shop_show;
        private String shop_address;
        private String shop_starlevel;
        private String shop_telephone;
        private String shop_name;

        public String getShop_lat() {
            return shop_lat;
        }

        public void setShop_lat(String shop_lat) {
            this.shop_lat = shop_lat;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShop_lng() {
            return shop_lng;
        }

        public void setShop_lng(String shop_lng) {
            this.shop_lng = shop_lng;
        }

        public String getShop_show() {
            return shop_show;
        }

        public void setShop_show(String shop_show) {
            this.shop_show = shop_show;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_starlevel() {
            return shop_starlevel;
        }

        public void setShop_starlevel(String shop_starlevel) {
            this.shop_starlevel = shop_starlevel;
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
    }

    public static class WashBean  extends Clean {
    }

    public static class RepairBean extends Clean {
    }

    public static class CosmetologyBean  extends Clean {
    }

    public static class MaintainBean  extends Clean {
    }
}
