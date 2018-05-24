package com.sinata.rwxchina.basiclib.commonclass.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ShopInfoBean implements Serializable{


    /**
     * shopinfo : {"shopid":"1994","shop_name":"测试SL","shop_type":"2","shop_zh_type":"酒店","shop_logo":"/Uploads/User/4/20171207/dbaf67a137d435b6b2a89fb181550878.jpg","shop_show":["/Uploads/User/1994/6e6df0d1295f3113a989ba6c4c88effe.jpg","/Uploads/User/1994/845834ed1ba2766de1ba2b40acb035f2.jpg","/Uploads/User/1994/e9ecfa24e93dd09d47c43e30d7d37975.jpg"],"shop_telephone":"152-8106-3766","shop_address":"四川省成都市青羊区苏坡街道园首山庄","shop_lng":"103.986902","shop_lat":"30.670545","distance":"11354.45","shop_goodsmoney_min":"12","shop_starlevel":"4.5","shop_synopsis":"四川省成都市青羊区苏坡街道园首山庄","shop_service":"","shop_point":"","shop_sale":"0","shop_is_xiche":"0","shop_is_baoy":"0","shop_is_meir":"0","shop_is_weix":"0","shop_is_discount":"1","shop_discount":"9.5","shop_discount_explain":"","shop_juan":"","shop_tuan":"","shop_type_labels_zh":"快捷","shop_people_avgmoney":"55","is_shop":"1","is_webshop":"1"}
     * goods_brand_list : [{"brand_id":"219","brand_name":"米其林","brand_logo":"/Uploads/App/brand/goods/6b49ab5c9c463ec58330779f03adcb69.jpg"},{"brand_id":"231","brand_name":"普利司通","brand_logo":"/Uploads/App/brand/goods/3c6c84197d9c3a6b5c2aa8845e22c587.jpg"}]
     * goods_type_list : [{"goods_type":"10010","goods_type_zh":"轮胎"},{"goods_type":"10020","goods_type_zh":"润滑油"},{"goods_type":"10030","goods_type_zh":"清洁保养"},{"goods_type":"10040","goods_type_zh":"车载配件"},{"goods_type":"10050","goods_type_zh":"其它品类"}]
     * goods : [{"goods_id":"2305784880","shopid":"1994","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"10050","goods_type_zh":"其它品类","goods_name":"其他品类","goods_subtitle":"其他品类002","default_image":"/Uploads/User/1994/0d62a52e7e2f44c90999a46c908ff0e4.jpg","addition_image":["/Uploads/User/1994/bbb3490e837b9aa4df292245099bf19c.jpg","/Uploads/User/1994/52fcc1bcc190674977e4f748800d0e13.jpg","/Uploads/User/1994/82aa356ebbd9e674dcacba2a96855169.jpg"],"goods_score":"0","goods_type_labels":[],"goods_unit":"瓶","goods_market_price":"258","goods_price":"147","goods_number":"999","goods_description":"其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类","goods_description_img":["/Uploads/User/1994/f70be827d797c4d974dadbf647703e06.jpg","/Uploads/User/1994/18a43dd20593dce7a39d5e90556c8d01.jpg"],"goods_status":"1","goods_attribute":"","goods_is_wifi":"0","goods_is_window":"0","goods_is_breakfast":"0","goods_floor":"","goods_live_people":"","goods_bed_type":"","goods_acreage":"","goods_rule":"","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"0","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1514267471","createdate":"2017-12-26 13:51:11","updatetime":"","is_delete":"0","is_mallgoods":"1","goods_sale":"0","goods_period":"","goods_services":"","goods_prompt":"","goods_scope_people":"","goods_labels_attr":["原厂配件","品质保障"],"goods_is_freight":"0","goods_freight":"0.00","goods_type_labels_str":""},{"goods_id":"2305784884","shopid":"1994","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"10050","goods_type_zh":"其它品类","goods_name":"其他品类01","goods_subtitle":"其他品类002","default_image":"/Uploads/User/1994/0d62a52e7e2f44c90999a46c908ff0e4.jpg","addition_image":["/Uploads/User/1994/bbb3490e837b9aa4df292245099bf19c.jpg","/Uploads/User/1994/52fcc1bcc190674977e4f748800d0e13.jpg","/Uploads/User/1994/82aa356ebbd9e674dcacba2a96855169.jpg"],"goods_score":"0","goods_type_labels":[],"goods_unit":"瓶","goods_market_price":"258","goods_price":"147","goods_number":"999","goods_description":"其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类","goods_description_img":["/Uploads/User/1994/f70be827d797c4d974dadbf647703e06.jpg","/Uploads/User/1994/18a43dd20593dce7a39d5e90556c8d01.jpg"],"goods_status":"1","goods_attribute":"","goods_is_wifi":"0","goods_is_window":"0","goods_is_breakfast":"0","goods_floor":"","goods_live_people":"","goods_bed_type":"","goods_acreage":"","goods_rule":"","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"0","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1514267471","createdate":"2017-12-26 13:51:11","updatetime":"","is_delete":"0","is_mallgoods":"1","goods_sale":"0","goods_period":"","goods_services":"","goods_prompt":"","goods_scope_people":"","goods_labels_attr":["原厂配件","品质保障"],"goods_is_freight":"0","goods_freight":"0.00","goods_type_labels_str":""},{"goods_id":"2305784879","shopid":"1994","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"10040","goods_type_zh":"车载配件","goods_name":"车配件","goods_subtitle":"车配件0002","default_image":"/Uploads/User/1994/ea9214d96d8dbff837b5c0a877df53ba.jpg","addition_image":["/Uploads/User/1994/23d69f24f674619074c0163f752d7306.jpg","/Uploads/User/1994/d2e8d816596eab0c54f3ce39c28ed57c.jpg","/Uploads/User/1994/86bb55a220d2d6cb0ac9cd9cc18bc3a9.jpg","/Uploads/User/1994/af8b8b715911b71fa728b8141b032ec9.jpg","/Uploads/User/1994/35cc8e89240599d59b764f132853c827.jpg"],"goods_score":"0","goods_type_labels":[],"goods_unit":"个","goods_market_price":"258","goods_price":"147","goods_number":"999","goods_description":"车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件","goods_description_img":["/Uploads/User/1994/3848af472be343ac9a2eb9e93e2a898b.jpg","/Uploads/User/1994/b46b4bc6044266d5520d5056bb0e1515.jpg","/Uploads/User/1994/5f03c960b5962ad348595565914789d8.jpg"],"goods_status":"1","goods_attribute":"","goods_is_wifi":"0","goods_is_window":"0","goods_is_breakfast":"0","goods_floor":"","goods_live_people":"","goods_bed_type":"","goods_acreage":"","goods_rule":"","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"0","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1514267392","createdate":"2017-12-26 13:49:52","updatetime":"","is_delete":"0","is_mallgoods":"1","goods_sale":"0","goods_period":"","goods_services":"","goods_prompt":"","goods_scope_people":"","goods_labels_attr":["原厂配件","品质保障"],"goods_is_freight":"1","goods_freight":"15.00","goods_type_labels_str":""},{"goods_id":"2305784883","shopid":"1994","brand_id":"0","brand_zh":"","is_volume":"0","volume_description":"","goods_type":"10040","goods_type_zh":"车载配件","goods_name":"车配件01","goods_subtitle":"车配件0002","default_image":"/Uploads/User/1994/ea9214d96d8dbff837b5c0a877df53ba.jpg","addition_image":["/Uploads/User/1994/23d69f24f674619074c0163f752d7306.jpg","/Uploads/User/1994/d2e8d816596eab0c54f3ce39c28ed57c.jpg","/Uploads/User/1994/86bb55a220d2d6cb0ac9cd9cc18bc3a9.jpg","/Uploads/User/1994/af8b8b715911b71fa728b8141b032ec9.jpg","/Uploads/User/1994/35cc8e89240599d59b764f132853c827.jpg"],"goods_score":"0","goods_type_labels":[],"goods_unit":"个","goods_market_price":"258","goods_price":"147","goods_number":"999","goods_description":"车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件车配件","goods_description_img":["/Uploads/User/1994/3848af472be343ac9a2eb9e93e2a898b.jpg","/Uploads/User/1994/b46b4bc6044266d5520d5056bb0e1515.jpg","/Uploads/User/1994/5f03c960b5962ad348595565914789d8.jpg"],"goods_status":"1","goods_attribute":"","goods_is_wifi":"0","goods_is_window":"0","goods_is_breakfast":"0","goods_floor":"","goods_live_people":"","goods_bed_type":"","goods_acreage":"","goods_rule":"","goods_scope_range":"","goods_reminder":"","goods_room":"","goods_is_pack":"0","goods_pack":"","goods_is_group":"0","goods_group_data":[],"goods_validity_time":"","goods_service_time":"","goods_address":"","goods_lng":"","goods_lat":"","sort":"0","createtime":"1514267392","createdate":"2017-12-26 13:49:52","updatetime":"","is_delete":"0","is_mallgoods":"1","goods_sale":"0","goods_period":"","goods_services":"","goods_prompt":"","goods_scope_people":"","goods_labels_attr":["原厂配件","品质保障"],"goods_is_freight":"1","goods_freight":"15.00","goods_type_labels_str":""}]
     */

    private ShopinfoBean shopinfo;
    private List<GoodsBrandListBean> goods_brand_list;
    private List<GoodsTypeListBean> goods_type_list;
    private List<GoodsBean> goods;

    public ShopinfoBean getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(ShopinfoBean shopinfo) {
        this.shopinfo = shopinfo;
    }

    public List<GoodsBrandListBean> getGoods_brand_list() {
        return goods_brand_list;
    }

    public void setGoods_brand_list(List<GoodsBrandListBean> goods_brand_list) {
        this.goods_brand_list = goods_brand_list;
    }

    public List<GoodsTypeListBean> getGoods_type_list() {
        return goods_type_list;
    }

    public void setGoods_type_list(List<GoodsTypeListBean> goods_type_list) {
        this.goods_type_list = goods_type_list;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class ShopinfoBean implements Serializable{
        /**
         * shopid : 1994
         * shop_name : 测试SL
         * shop_type : 2
         * shop_zh_type : 酒店
         * shop_logo : /Uploads/User/4/20171207/dbaf67a137d435b6b2a89fb181550878.jpg
         * shop_show : ["/Uploads/User/1994/6e6df0d1295f3113a989ba6c4c88effe.jpg","/Uploads/User/1994/845834ed1ba2766de1ba2b40acb035f2.jpg","/Uploads/User/1994/e9ecfa24e93dd09d47c43e30d7d37975.jpg"]
         * shop_telephone : 152-8106-3766
         * shop_address : 四川省成都市青羊区苏坡街道园首山庄
         * shop_lng : 103.986902
         * shop_lat : 30.670545
         * distance : 11354.45
         * shop_goodsmoney_min : 12
         * shop_starlevel : 4.5
         * shop_synopsis : 四川省成都市青羊区苏坡街道园首山庄
         * shop_service :
         * shop_point :
         * shop_sale : 0
         * shop_is_xiche : 0
         * shop_is_baoy : 0
         * shop_is_meir : 0
         * shop_is_weix : 0
         * shop_is_discount : 1
         * shop_discount : 9.5
         * shop_discount_explain :
         * shop_juan :
         * shop_tuan :
         * shop_type_labels_zh : 快捷
         * shop_people_avgmoney : 55
         * is_shop : 1
         * is_webshop : 1
         */

        private String shopid;
        private String shop_name;
        private String shop_type;
        private String shop_zh_type;
        private String shop_logo;
        private String shop_telephone;
        private String shop_address;
        private String shop_lng;
        private String shop_lat;
        private String distance;
        private String shop_goodsmoney_min;
        private String shop_starlevel;
        private String shop_synopsis;
        private String shop_service;
        private String shop_point;
        private String shop_sale;
        private String shop_is_xiche;
        private String shop_is_baoy;
        private String shop_is_meir;
        private String shop_is_weix;
        private String shop_is_discount;
        private String shop_discount;
        private String shop_discount_explain;
        private String shop_juan;
        private String shop_tuan;
        private String shop_type_labels_zh;
        private String shop_people_avgmoney;
        private String is_shop;
        private String is_webshop;
        private List<String> shop_show;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getShop_zh_type() {
            return shop_zh_type;
        }

        public void setShop_zh_type(String shop_zh_type) {
            this.shop_zh_type = shop_zh_type;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public String getShop_telephone() {
            return shop_telephone;
        }

        public void setShop_telephone(String shop_telephone) {
            this.shop_telephone = shop_telephone;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getShop_goodsmoney_min() {
            return shop_goodsmoney_min;
        }

        public void setShop_goodsmoney_min(String shop_goodsmoney_min) {
            this.shop_goodsmoney_min = shop_goodsmoney_min;
        }

        public String getShop_starlevel() {
            return shop_starlevel;
        }

        public void setShop_starlevel(String shop_starlevel) {
            this.shop_starlevel = shop_starlevel;
        }

        public String getShop_synopsis() {
            return shop_synopsis;
        }

        public void setShop_synopsis(String shop_synopsis) {
            this.shop_synopsis = shop_synopsis;
        }

        public String getShop_service() {
            return shop_service;
        }

        public void setShop_service(String shop_service) {
            this.shop_service = shop_service;
        }

        public String getShop_point() {
            return shop_point;
        }

        public void setShop_point(String shop_point) {
            this.shop_point = shop_point;
        }

        public String getShop_sale() {
            return shop_sale;
        }

        public void setShop_sale(String shop_sale) {
            this.shop_sale = shop_sale;
        }

        public String getShop_is_xiche() {
            return shop_is_xiche;
        }

        public void setShop_is_xiche(String shop_is_xiche) {
            this.shop_is_xiche = shop_is_xiche;
        }

        public String getShop_is_baoy() {
            return shop_is_baoy;
        }

        public void setShop_is_baoy(String shop_is_baoy) {
            this.shop_is_baoy = shop_is_baoy;
        }

        public String getShop_is_meir() {
            return shop_is_meir;
        }

        public void setShop_is_meir(String shop_is_meir) {
            this.shop_is_meir = shop_is_meir;
        }

        public String getShop_is_weix() {
            return shop_is_weix;
        }

        public void setShop_is_weix(String shop_is_weix) {
            this.shop_is_weix = shop_is_weix;
        }

        public String getShop_is_discount() {
            return shop_is_discount;
        }

        public void setShop_is_discount(String shop_is_discount) {
            this.shop_is_discount = shop_is_discount;
        }

        public String getShop_discount() {
            return shop_discount;
        }

        public void setShop_discount(String shop_discount) {
            this.shop_discount = shop_discount;
        }

        public String getShop_discount_explain() {
            return shop_discount_explain;
        }

        public void setShop_discount_explain(String shop_discount_explain) {
            this.shop_discount_explain = shop_discount_explain;
        }

        public String getShop_juan() {
            return shop_juan;
        }

        public void setShop_juan(String shop_juan) {
            this.shop_juan = shop_juan;
        }

        public String getShop_tuan() {
            return shop_tuan;
        }

        public void setShop_tuan(String shop_tuan) {
            this.shop_tuan = shop_tuan;
        }

        public String getShop_type_labels_zh() {
            return shop_type_labels_zh;
        }

        public void setShop_type_labels_zh(String shop_type_labels_zh) {
            this.shop_type_labels_zh = shop_type_labels_zh;
        }

        public String getShop_people_avgmoney() {
            return shop_people_avgmoney;
        }

        public void setShop_people_avgmoney(String shop_people_avgmoney) {
            this.shop_people_avgmoney = shop_people_avgmoney;
        }

        public String getIs_shop() {
            return is_shop;
        }

        public void setIs_shop(String is_shop) {
            this.is_shop = is_shop;
        }

        public String getIs_webshop() {
            return is_webshop;
        }

        public void setIs_webshop(String is_webshop) {
            this.is_webshop = is_webshop;
        }

        public List<String> getShop_show() {
            return shop_show;
        }

        public void setShop_show(List<String> shop_show) {
            this.shop_show = shop_show;
        }
    }

    public static class GoodsBrandListBean implements Serializable{
        /**
         * brand_id : 219
         * brand_name : 米其林
         * brand_logo : /Uploads/App/brand/goods/6b49ab5c9c463ec58330779f03adcb69.jpg
         */

        private String brand_id;
        private String brand_name;
        private String brand_logo;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }
    }

    public static class GoodsTypeListBean implements Serializable{
        /**
         * goods_type : 10010
         * goods_type_zh : 轮胎
         */

        private String goods_type;
        private String goods_type_zh;

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getGoods_type_zh() {
            return goods_type_zh;
        }

        public void setGoods_type_zh(String goods_type_zh) {
            this.goods_type_zh = goods_type_zh;
        }
    }

    public static class GoodsBean implements Serializable{
        /**
         * goods_id : 2305784880
         * shopid : 1994
         * brand_id : 0
         * brand_zh :
         * is_volume : 0
         * volume_description :
         * goods_type : 10050
         * goods_type_zh : 其它品类
         * goods_name : 其他品类
         * goods_subtitle : 其他品类002
         * default_image : /Uploads/User/1994/0d62a52e7e2f44c90999a46c908ff0e4.jpg
         * addition_image : ["/Uploads/User/1994/bbb3490e837b9aa4df292245099bf19c.jpg","/Uploads/User/1994/52fcc1bcc190674977e4f748800d0e13.jpg","/Uploads/User/1994/82aa356ebbd9e674dcacba2a96855169.jpg"]
         * goods_score : 0
         * goods_type_labels : []
         * goods_unit : 瓶
         * goods_market_price : 258
         * goods_price : 147
         * goods_number : 999
         * goods_description : 其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类其他品类
         * goods_description_img : ["/Uploads/User/1994/f70be827d797c4d974dadbf647703e06.jpg","/Uploads/User/1994/18a43dd20593dce7a39d5e90556c8d01.jpg"]
         * goods_status : 1
         * goods_attribute :
         * goods_is_wifi : 0
         * goods_is_window : 0
         * goods_is_breakfast : 0
         * goods_floor :
         * goods_live_people :
         * goods_bed_type :
         * goods_acreage :
         * goods_rule :
         * goods_scope_range :
         * goods_reminder :
         * goods_room :
         * goods_is_pack : 0
         * goods_pack :
         * goods_is_group : 0
         * goods_group_data : []
         * goods_validity_time :
         * goods_service_time :
         * goods_address :
         * goods_lng :
         * goods_lat :
         * sort : 0
         * createtime : 1514267471
         * createdate : 2017-12-26 13:51:11
         * updatetime :
         * is_delete : 0
         * is_mallgoods : 1
         * goods_sale : 0
         * goods_period :
         * goods_services :
         * goods_prompt :
         * goods_scope_people :
         * goods_labels_attr : ["原厂配件","品质保障"]
         * goods_is_freight : 0
         * goods_freight : 0.00
         * goods_type_labels_str :
         */

        private String goods_id;
        private String shopid;
        private String brand_id;
        private String brand_zh;
        private String is_volume;
        private String volume_description;
        private String goods_type;
        private String goods_type_zh;
        private String goods_name;
        private String goods_subtitle;
        private String default_image;
        private String goods_score;
        private String goods_unit;
        private String goods_market_price;
        private String goods_price;
        private String goods_number;
        private String goods_description;
        private String goods_status;
        private String goods_attribute;
        private String goods_is_wifi;
        private String goods_is_window;
        private String goods_is_breakfast;
        private String goods_floor;
        private String goods_live_people;
        private String goods_bed_type;
        private String goods_acreage;
        private String goods_rule;
        private String goods_scope_range;
        private String goods_reminder;
        private String goods_room;
        private String goods_is_pack;
        private String goods_pack;
        private String goods_is_group;
        private String goods_validity_time;
        private String goods_service_time;
        private String goods_address;
        private String goods_lng;
        private String goods_lat;
        private String sort;
        private String createtime;
        private String createdate;
        private String updatetime;
        private String is_delete;
        private String is_mallgoods;
        private String goods_sale;
        private String goods_period;
        private String goods_services;
        private String goods_prompt;
        private String goods_scope_people;
        private String goods_is_freight;
        private String goods_freight;
        private String goods_type_labels_str;
        private List<String> addition_image;
        private List<?> goods_type_labels;
        private List<String> goods_description_img;
        private List<?> goods_group_data;
        private List<String> goods_labels_attr;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_zh() {
            return brand_zh;
        }

        public void setBrand_zh(String brand_zh) {
            this.brand_zh = brand_zh;
        }

        public String getIs_volume() {
            return is_volume;
        }

        public void setIs_volume(String is_volume) {
            this.is_volume = is_volume;
        }

        public String getVolume_description() {
            return volume_description;
        }

        public void setVolume_description(String volume_description) {
            this.volume_description = volume_description;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getGoods_type_zh() {
            return goods_type_zh;
        }

        public void setGoods_type_zh(String goods_type_zh) {
            this.goods_type_zh = goods_type_zh;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_subtitle() {
            return goods_subtitle;
        }

        public void setGoods_subtitle(String goods_subtitle) {
            this.goods_subtitle = goods_subtitle;
        }

        public String getDefault_image() {
            return default_image;
        }

        public void setDefault_image(String default_image) {
            this.default_image = default_image;
        }

        public String getGoods_score() {
            return goods_score;
        }

        public void setGoods_score(String goods_score) {
            this.goods_score = goods_score;
        }

        public String getGoods_unit() {
            return goods_unit;
        }

        public void setGoods_unit(String goods_unit) {
            this.goods_unit = goods_unit;
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

        public String getGoods_description() {
            return goods_description;
        }

        public void setGoods_description(String goods_description) {
            this.goods_description = goods_description;
        }

        public String getGoods_status() {
            return goods_status;
        }

        public void setGoods_status(String goods_status) {
            this.goods_status = goods_status;
        }

        public String getGoods_attribute() {
            return goods_attribute;
        }

        public void setGoods_attribute(String goods_attribute) {
            this.goods_attribute = goods_attribute;
        }

        public String getGoods_is_wifi() {
            return goods_is_wifi;
        }

        public void setGoods_is_wifi(String goods_is_wifi) {
            this.goods_is_wifi = goods_is_wifi;
        }

        public String getGoods_is_window() {
            return goods_is_window;
        }

        public void setGoods_is_window(String goods_is_window) {
            this.goods_is_window = goods_is_window;
        }

        public String getGoods_is_breakfast() {
            return goods_is_breakfast;
        }

        public void setGoods_is_breakfast(String goods_is_breakfast) {
            this.goods_is_breakfast = goods_is_breakfast;
        }

        public String getGoods_floor() {
            return goods_floor;
        }

        public void setGoods_floor(String goods_floor) {
            this.goods_floor = goods_floor;
        }

        public String getGoods_live_people() {
            return goods_live_people;
        }

        public void setGoods_live_people(String goods_live_people) {
            this.goods_live_people = goods_live_people;
        }

        public String getGoods_bed_type() {
            return goods_bed_type;
        }

        public void setGoods_bed_type(String goods_bed_type) {
            this.goods_bed_type = goods_bed_type;
        }

        public String getGoods_acreage() {
            return goods_acreage;
        }

        public void setGoods_acreage(String goods_acreage) {
            this.goods_acreage = goods_acreage;
        }

        public String getGoods_rule() {
            return goods_rule;
        }

        public void setGoods_rule(String goods_rule) {
            this.goods_rule = goods_rule;
        }

        public String getGoods_scope_range() {
            return goods_scope_range;
        }

        public void setGoods_scope_range(String goods_scope_range) {
            this.goods_scope_range = goods_scope_range;
        }

        public String getGoods_reminder() {
            return goods_reminder;
        }

        public void setGoods_reminder(String goods_reminder) {
            this.goods_reminder = goods_reminder;
        }

        public String getGoods_room() {
            return goods_room;
        }

        public void setGoods_room(String goods_room) {
            this.goods_room = goods_room;
        }

        public String getGoods_is_pack() {
            return goods_is_pack;
        }

        public void setGoods_is_pack(String goods_is_pack) {
            this.goods_is_pack = goods_is_pack;
        }

        public String getGoods_pack() {
            return goods_pack;
        }

        public void setGoods_pack(String goods_pack) {
            this.goods_pack = goods_pack;
        }

        public String getGoods_is_group() {
            return goods_is_group;
        }

        public void setGoods_is_group(String goods_is_group) {
            this.goods_is_group = goods_is_group;
        }

        public String getGoods_validity_time() {
            return goods_validity_time;
        }

        public void setGoods_validity_time(String goods_validity_time) {
            this.goods_validity_time = goods_validity_time;
        }

        public String getGoods_service_time() {
            return goods_service_time;
        }

        public void setGoods_service_time(String goods_service_time) {
            this.goods_service_time = goods_service_time;
        }

        public String getGoods_address() {
            return goods_address;
        }

        public void setGoods_address(String goods_address) {
            this.goods_address = goods_address;
        }

        public String getGoods_lng() {
            return goods_lng;
        }

        public void setGoods_lng(String goods_lng) {
            this.goods_lng = goods_lng;
        }

        public String getGoods_lat() {
            return goods_lat;
        }

        public void setGoods_lat(String goods_lat) {
            this.goods_lat = goods_lat;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getIs_mallgoods() {
            return is_mallgoods;
        }

        public void setIs_mallgoods(String is_mallgoods) {
            this.is_mallgoods = is_mallgoods;
        }

        public String getGoods_sale() {
            return goods_sale;
        }

        public void setGoods_sale(String goods_sale) {
            this.goods_sale = goods_sale;
        }

        public String getGoods_period() {
            return goods_period;
        }

        public void setGoods_period(String goods_period) {
            this.goods_period = goods_period;
        }

        public String getGoods_services() {
            return goods_services;
        }

        public void setGoods_services(String goods_services) {
            this.goods_services = goods_services;
        }

        public String getGoods_prompt() {
            return goods_prompt;
        }

        public void setGoods_prompt(String goods_prompt) {
            this.goods_prompt = goods_prompt;
        }

        public String getGoods_scope_people() {
            return goods_scope_people;
        }

        public void setGoods_scope_people(String goods_scope_people) {
            this.goods_scope_people = goods_scope_people;
        }

        public String getGoods_is_freight() {
            return goods_is_freight;
        }

        public void setGoods_is_freight(String goods_is_freight) {
            this.goods_is_freight = goods_is_freight;
        }

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public String getGoods_type_labels_str() {
            return goods_type_labels_str;
        }

        public void setGoods_type_labels_str(String goods_type_labels_str) {
            this.goods_type_labels_str = goods_type_labels_str;
        }

        public List<String> getAddition_image() {
            return addition_image;
        }

        public void setAddition_image(List<String> addition_image) {
            this.addition_image = addition_image;
        }

        public List<?> getGoods_type_labels() {
            return goods_type_labels;
        }

        public void setGoods_type_labels(List<?> goods_type_labels) {
            this.goods_type_labels = goods_type_labels;
        }

        public List<String> getGoods_description_img() {
            return goods_description_img;
        }

        public void setGoods_description_img(List<String> goods_description_img) {
            this.goods_description_img = goods_description_img;
        }

        public List<?> getGoods_group_data() {
            return goods_group_data;
        }

        public void setGoods_group_data(List<?> goods_group_data) {
            this.goods_group_data = goods_group_data;
        }

        public List<String> getGoods_labels_attr() {
            return goods_labels_attr;
        }

        public void setGoods_labels_attr(List<String> goods_labels_attr) {
            this.goods_labels_attr = goods_labels_attr;
        }
    }
}
