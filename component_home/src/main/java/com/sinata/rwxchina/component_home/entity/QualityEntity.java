package com.sinata.rwxchina.component_home.entity;

/**
 * @author HRR
 * @datetime 2017/12/6
 * @describe 首页品质生活区实体类
 * @modifyRecord
 */

public class QualityEntity {

    /**
     * id : 30
     * title : 美食
     * img : /Uploads/App/icon/pinzhi_meishi.png
     * is_url : 0
     * url :
     * describe : 于闹市中寻静地小憩
     * shop_type : 3
     * shop_type_labels :
     * icon_label : meishi
     */

    private String id;
    private String title;
    private String img;
    private String is_url;
    private String url;
    private String describe;
    private String shop_type;
    private String shop_type_labels;
    private String icon_label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIs_url() {
        return is_url;
    }

    public void setIs_url(String is_url) {
        this.is_url = is_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_type_labels() {
        return shop_type_labels;
    }

    public void setShop_type_labels(String shop_type_labels) {
        this.shop_type_labels = shop_type_labels;
    }

    public String getIcon_label() {
        return icon_label;
    }

    public void setIcon_label(String icon_label) {
        this.icon_label = icon_label;
    }

    @Override
    public String toString() {
        return "QualityEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", is_url='" + is_url + '\'' +
                ", url='" + url + '\'' +
                ", describe='" + describe + '\'' +
                ", shop_type='" + shop_type + '\'' +
                ", shop_type_labels='" + shop_type_labels + '\'' +
                ", icon_label='" + icon_label + '\'' +
                '}';
    }
}
