package com.sinata.rwxchina.basiclib.commonclass.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author HRR
 * @datetime 2017/11/30
 * @describe 景区实体类
 * @modifyRecord
 */

public class ScenicEntity implements Serializable{

    /**
     * goods_id : 4
     * goods_name : 毕棚沟
     * goods_subtitle : 谁识卧龙客,长岭
     * default_image : /Uploads/_old/storepriture/b95030cd24f0bcae9c8da9076abf358a.jpg
     * addition_image : ["/Uploads/_old/storepriture/9b83ce768c9d45a2f1b33bb5332ed6c4.jpg","/Uploads/_old/storepriture/45cbeda578c5d5d4f512dd8ca16b5735.jpg"]
     * goods_address : 四川省阿坝州理县朴头乡朴头村
     * goods_lng : 102.986327
     * goods_lat : 31.40643
     * goods_description : 毕棚沟位于四川省阿坝藏族羌族自治州理县朴头乡梭罗沟境内，是国内非常知名的红叶观赏圣地。毕棚沟以其优美的自然风光、完美的自然生态景观、优良的生态环境著称。景区内红叶、杜鹃花种类繁多，森林原始、瀑布飞挂、冰川奇特。毕棚沟被比作四姑娘山的美丽背影。这是个五彩斑斓的世界，浓绿的树，深红的叶，青青的湖，湛蓝的天，纯白的雪；这是个多姿多彩世界，险峻奇峰，一道冰川，人间寺庙。
     * goods_description_img : ["/Uploads/_old/storepriture/ad21c3e0e1a5e836951d946fe90248e7.jpg"]
     * distance : 132.11
     */

    private String goods_id;
    private String goods_name;
    private String goods_subtitle;
    private String default_image;
    private String goods_address;
    private String goods_lng;
    private String goods_lat;
    private String goods_description;
    private String distance;
    private List<String> addition_image;
    private List<String> goods_description_img;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public String getGoods_description() {
        return goods_description;
    }

    public void setGoods_description(String goods_description) {
        this.goods_description = goods_description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<String> getAddition_image() {
        return addition_image;
    }

    public void setAddition_image(List<String> addition_image) {
        this.addition_image = addition_image;
    }

    public List<String> getGoods_description_img() {
        return goods_description_img;
    }

    public void setGoods_description_img(List<String> goods_description_img) {
        this.goods_description_img = goods_description_img;
    }

    @Override
    public String toString() {
        return "ScenicEntity{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_subtitle='" + goods_subtitle + '\'' +
                ", default_image='" + default_image + '\'' +
                ", goods_address='" + goods_address + '\'' +
                ", goods_lng='" + goods_lng + '\'' +
                ", goods_lat='" + goods_lat + '\'' +
                ", goods_description='" + goods_description + '\'' +
                ", distance='" + distance + '\'' +
                ", addition_image=" + addition_image +
                ", goods_description_img=" + goods_description_img +
                '}';
    }
}
