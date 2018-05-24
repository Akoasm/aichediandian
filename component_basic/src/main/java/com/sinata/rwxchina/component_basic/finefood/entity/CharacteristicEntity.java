package com.sinata.rwxchina.component_basic.finefood.entity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 特色推荐菜实体类
 * @modifyRecord
 */

public class CharacteristicEntity {

    /**
     * goods_id : 2330
     * shopid : 15940954511
     * brand_id : 0
     * brand_zh :
     * is_volume : 0
     * volume_description :
     * goods_type : 0
     * goods_type_zh :
     * goods_name : 4-6套餐02
     * goods_subtitle :
     * default_image : /Uploads/User/15940954507/88613aaa5fd680f37437ec9dea59e07d.jpg
     * addition_image : ["/Uploads/User/15940954507/a621fb885faba3076b52f9fc34f786aa.jpg","/Uploads/User/15940954507/f8938276e8121e50b275274181a92cd0.jpg","/Uploads/User/15940954507/b753351162950b77e8005c6f5d5e9ae6.jpg","/Uploads/User/15940954507/1742fb6472464d4820a428e2843c965a.jpg"]
     * goods_score : 0
     * goods_type_labels : ["随时退","过期退"]
     * goods_unit : 套
     * goods_market_price : 168
     * goods_price : 158
     * goods_number : 999
     * goods_description :
     * goods_description_img : []
     * goods_status : 1
     * goods_attribute :
     * goods_is_wifi : 1
     * goods_is_window : 0
     * goods_is_breakfast : 0
     * goods_floor :
     * goods_live_people :
     * goods_bed_type :
     * goods_acreage :
     * goods_rule : 可叠加使用、不兑换
     * goods_scope_range : 2017-01-12--2017-12-01
     * goods_reminder : 无需预约
     * goods_room : 可以提前电话预约
     * goods_is_pack : 0
     * goods_pack : 可以打包，加收打包盒费
     * goods_is_group : 1
     * goods_group_data : [{"m_meun_name":"荤菜","meun_list":[{"m_goods_name":"肥牛","m_goods_number":"1份","m_goods_money":"20"},{"m_goods_name":"五花肉","m_goods_number":"1份","m_goods_money":"20"},{"m_goods_name":"耗儿鱼","m_goods_number":"1份","m_goods_money":"20"}]},{"m_meun_name":"素菜","meun_list":[{"m_goods_name":"土豆","m_goods_number":"1份","m_goods_money":"10"},{"m_goods_name":"豆皮","m_goods_number":"1份","m_goods_money":"10"}]},{"m_meun_name":"锅底（二选一）","meun_list":[{"m_goods_name":"鸳鸯锅","m_goods_number":"1份","m_goods_money":"55"},{"m_goods_name":"红汤锅","m_goods_number":"1份","m_goods_money":"55"},{"m_goods_name":"清汤锅","m_goods_number":"1份","m_goods_money":"55"}]}]
     * goods_validity_time :
     * goods_service_time :
     * goods_address :
     * goods_lng :
     * goods_lat :
     * sort : 0
     * createtime : 1513048864
     * createdate : 2017-12-12 11:21:04
     * updatetime :
     * is_delete : 0
     * is_mallgoods : 0
     * goods_sale : 0
     * goods_period :
     * goods_services :
     * goods_prompt :
     * goods_scope_people :
     * goods_labels_attr : []
     * goods_is_freight : 1
     * goods_freight : 0.00
     * goods_type_labels_str : 随时退,过期退
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
    private List<String> goods_type_labels;
    private List<?> goods_description_img;
    private List<GoodsGroupDataBean> goods_group_data;
    private List<?> goods_labels_attr;

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

    public List<String> getGoods_type_labels() {
        return goods_type_labels;
    }

    public void setGoods_type_labels(List<String> goods_type_labels) {
        this.goods_type_labels = goods_type_labels;
    }

    public List<?> getGoods_description_img() {
        return goods_description_img;
    }

    public void setGoods_description_img(List<?> goods_description_img) {
        this.goods_description_img = goods_description_img;
    }

    public List<GoodsGroupDataBean> getGoods_group_data() {
        return goods_group_data;
    }

    public void setGoods_group_data(List<GoodsGroupDataBean> goods_group_data) {
        this.goods_group_data = goods_group_data;
    }

    public List<?> getGoods_labels_attr() {
        return goods_labels_attr;
    }

    public void setGoods_labels_attr(List<?> goods_labels_attr) {
        this.goods_labels_attr = goods_labels_attr;
    }

    public static class GoodsGroupDataBean {
        /**
         * m_meun_name : 荤菜
         * meun_list : [{"m_goods_name":"肥牛","m_goods_number":"1份","m_goods_money":"20"},{"m_goods_name":"五花肉","m_goods_number":"1份","m_goods_money":"20"},{"m_goods_name":"耗儿鱼","m_goods_number":"1份","m_goods_money":"20"}]
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
             * m_goods_name : 肥牛
             * m_goods_number : 1份
             * m_goods_money : 20
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

            @Override
            public String toString() {
                return "MeunListBean{" +
                        "m_goods_name='" + m_goods_name + '\'' +
                        ", m_goods_number='" + m_goods_number + '\'' +
                        ", m_goods_money='" + m_goods_money + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "CharacteristicEntity{" +
                "goods_id='" + goods_id + '\'' +
                ", shopid='" + shopid + '\'' +
                ", brand_id='" + brand_id + '\'' +
                ", brand_zh='" + brand_zh + '\'' +
                ", is_volume='" + is_volume + '\'' +
                ", volume_description='" + volume_description + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", goods_type_zh='" + goods_type_zh + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_subtitle='" + goods_subtitle + '\'' +
                ", default_image='" + default_image + '\'' +
                ", goods_score='" + goods_score + '\'' +
                ", goods_unit='" + goods_unit + '\'' +
                ", goods_market_price='" + goods_market_price + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_number='" + goods_number + '\'' +
                ", goods_description='" + goods_description + '\'' +
                ", goods_status='" + goods_status + '\'' +
                ", goods_attribute='" + goods_attribute + '\'' +
                ", goods_is_wifi='" + goods_is_wifi + '\'' +
                ", goods_is_window='" + goods_is_window + '\'' +
                ", goods_is_breakfast='" + goods_is_breakfast + '\'' +
                ", goods_floor='" + goods_floor + '\'' +
                ", goods_live_people='" + goods_live_people + '\'' +
                ", goods_bed_type='" + goods_bed_type + '\'' +
                ", goods_acreage='" + goods_acreage + '\'' +
                ", goods_rule='" + goods_rule + '\'' +
                ", goods_scope_range='" + goods_scope_range + '\'' +
                ", goods_reminder='" + goods_reminder + '\'' +
                ", goods_room='" + goods_room + '\'' +
                ", goods_is_pack='" + goods_is_pack + '\'' +
                ", goods_pack='" + goods_pack + '\'' +
                ", goods_is_group='" + goods_is_group + '\'' +
                ", goods_validity_time='" + goods_validity_time + '\'' +
                ", goods_service_time='" + goods_service_time + '\'' +
                ", goods_address='" + goods_address + '\'' +
                ", goods_lng='" + goods_lng + '\'' +
                ", goods_lat='" + goods_lat + '\'' +
                ", sort='" + sort + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createdate='" + createdate + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", is_delete='" + is_delete + '\'' +
                ", is_mallgoods='" + is_mallgoods + '\'' +
                ", goods_sale='" + goods_sale + '\'' +
                ", goods_period='" + goods_period + '\'' +
                ", goods_services='" + goods_services + '\'' +
                ", goods_prompt='" + goods_prompt + '\'' +
                ", goods_scope_people='" + goods_scope_people + '\'' +
                ", goods_is_freight='" + goods_is_freight + '\'' +
                ", goods_freight='" + goods_freight + '\'' +
                ", goods_type_labels_str='" + goods_type_labels_str + '\'' +
                ", addition_image=" + addition_image +
                ", goods_type_labels=" + goods_type_labels +
                ", goods_description_img=" + goods_description_img +
                ", goods_group_data=" + goods_group_data +
                ", goods_labels_attr=" + goods_labels_attr +
                '}';
    }
}
