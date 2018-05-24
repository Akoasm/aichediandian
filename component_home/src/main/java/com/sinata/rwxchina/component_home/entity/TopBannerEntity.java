package com.sinata.rwxchina.component_home.entity;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe banner轮播图实体类
 * @modifyRecord
 */

public class TopBannerEntity {

    /**
     * id : 1
     * app : acdd
     * cityid : 1
     * title : 任我行
     * urllarge :
     * urlsmall : /Uploads/_old/advs/a63d99b10353510822c9d5afd6f9475a.png
     * is_share : 0
     * url : http://u958964.viewer.maka.im/pcviewer/E19OWGOS
     * url_share :
     * sort : 0
     * is_show : 1
     * share_name :
     * share_content :
     * type : 1
     */

    private String id;
    private String app;
    private String cityid;
    private String title;
    private String urllarge;
    private String urlsmall;
    private String is_share;
    private String url;
    private String url_share;
    private String sort;
    private String is_show;
    private String share_name;
    private String share_content;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrllarge() {
        return urllarge;
    }

    public void setUrllarge(String urllarge) {
        this.urllarge = urllarge;
    }

    public String getUrlsmall() {
        return urlsmall;
    }

    public void setUrlsmall(String urlsmall) {
        this.urlsmall = urlsmall;
    }

    public String getIs_share() {
        return is_share;
    }

    public void setIs_share(String is_share) {
        this.is_share = is_share;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_share() {
        return url_share;
    }

    public void setUrl_share(String url_share) {
        this.url_share = url_share;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getShare_name() {
        return share_name;
    }

    public void setShare_name(String share_name) {
        this.share_name = share_name;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "TopBannerEntity{" +
                "id='" + id + '\'' +
                ", app='" + app + '\'' +
                ", cityid='" + cityid + '\'' +
                ", title='" + title + '\'' +
                ", urllarge='" + urllarge + '\'' +
                ", urlsmall='" + urlsmall + '\'' +
                ", is_share='" + is_share + '\'' +
                ", url='" + url + '\'' +
                ", url_share='" + url_share + '\'' +
                ", sort='" + sort + '\'' +
                ", is_show='" + is_show + '\'' +
                ", share_name='" + share_name + '\'' +
                ", share_content='" + share_content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
