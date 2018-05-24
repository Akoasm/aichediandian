package com.sinata.rwxchina.component_home.entity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/11
 * @describe 首页猜你喜欢其他分类实体类
 * @modifyRecord
 */

public class OtherLovelyEntity {

    /**
     * shopid : 2000009
     * shop_name : 演示汽车商铺A
     * shop_type : 1
     * shop_logo : /Uploads/App/brand/car/9.png
     * shop_telephone : 158-8999-9999
     * shop_starlevel : 3
     * shop_address : 成都市成华区
     * shop_lng : 104.005895
     * shop_lat : 30.100538
     * shop_goodsmoney_min : 16
     * shop_point : 推荐理由
     * shop_show : ["/Uploads/User/4/afdd2d241bc5e8a3c07317722d38b2d7.jpg","/Uploads/User/4/422bfa7c63142b88dd7245a18827825f.jpg","/Uploads/User/4/9ac80f30511edad84015e27528b737b2.jpg","/Uploads/User/4/d08803db34ed159a15561d93d0f58b87.jpg"]
     * shop_juan : 58代100元
     * shop_tuan : 38元商务套餐A,68元商务套餐B
     * shop_type_labels_zh :
     * shop_people_avgmoney :
     * distance : 11364.19
     */

    private String shopid;
    private String shop_name;
    private String shop_type;
    private String shop_logo;
    private String shop_telephone;
    private String shop_starlevel;
    private String shop_address;
    private String shop_lng;
    private String shop_lat;
    private String shop_goodsmoney_min;
    private String shop_point;
    private String shop_juan;
    private String shop_tuan;
    private String shop_type_labels_zh;
    private String shop_people_avgmoney;
    private String distance;
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

    public String getShop_starlevel() {
        return shop_starlevel;
    }

    public void setShop_starlevel(String shop_starlevel) {
        this.shop_starlevel = shop_starlevel;
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

    public String getShop_goodsmoney_min() {
        return shop_goodsmoney_min;
    }

    public void setShop_goodsmoney_min(String shop_goodsmoney_min) {
        this.shop_goodsmoney_min = shop_goodsmoney_min;
    }

    public String getShop_point() {
        return shop_point;
    }

    public void setShop_point(String shop_point) {
        this.shop_point = shop_point;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<String> getShop_show() {
        return shop_show;
    }

    public void setShop_show(List<String> shop_show) {
        this.shop_show = shop_show;
    }

    @Override
    public String toString() {
        return "OtherLovelyEntity{" +
                "shopid='" + shopid + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_type='" + shop_type + '\'' +
                ", shop_logo='" + shop_logo + '\'' +
                ", shop_telephone='" + shop_telephone + '\'' +
                ", shop_starlevel='" + shop_starlevel + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_lng='" + shop_lng + '\'' +
                ", shop_lat='" + shop_lat + '\'' +
                ", shop_goodsmoney_min='" + shop_goodsmoney_min + '\'' +
                ", shop_point='" + shop_point + '\'' +
                ", shop_juan='" + shop_juan + '\'' +
                ", shop_tuan='" + shop_tuan + '\'' +
                ", shop_type_labels_zh='" + shop_type_labels_zh + '\'' +
                ", shop_people_avgmoney='" + shop_people_avgmoney + '\'' +
                ", distance='" + distance + '\'' +
                ", shop_show=" + shop_show +
                '}';
    }
}
