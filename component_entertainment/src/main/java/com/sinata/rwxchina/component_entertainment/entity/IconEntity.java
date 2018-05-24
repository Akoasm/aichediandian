package com.sinata.rwxchina.component_entertainment.entity;

/**
 * @author:wj
 * @datetime：2017/12/14
 * @describe：娱乐Icon实体类
 * @modifyRecord:
 */


public class IconEntity {

    /**
     * id : 20
     * title : 酒店
     * img : /Uploads/App/icon/jd.png
     * is_url : 0
     * url :
     * describe : 娱乐消费
     * shop_type : 2
     * shop_type_labels :
     * icon_label : jiudian
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
}
