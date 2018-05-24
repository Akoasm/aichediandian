package com.sinata.rwxchina.basiclib.entity;

/**
 * @author HRR
 * @datetime 2017/12/8
 * @describe 首页动态图标实体类
 * @modifyRecord
 */

public class BaseIconEntity {


    /**
     * id : 8
     * title : 车辆维保
     * img : /Uploads/App/icon/clwb.png
     * is_url : 0
     * url :
     * describe :
     * shop_type : 1
     * shop_type_labels : 2,4
     * icon_label : qiche
     * is_must_login : 0
     * is_head : 0
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
    private String is_must_login;
    private String is_head;

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

    public String getIs_must_login() {
        return is_must_login;
    }

    public void setIs_must_login(String is_must_login) {
        this.is_must_login = is_must_login;
    }

    public String getIs_head() {
        return is_head;
    }

    public void setIs_head(String is_head) {
        this.is_head = is_head;
    }
}
