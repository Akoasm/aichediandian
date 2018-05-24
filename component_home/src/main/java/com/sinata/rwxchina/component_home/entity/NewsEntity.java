package com.sinata.rwxchina.component_home.entity;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 首页新闻实体类
 * @modifyRecord
 */

public class NewsEntity {

    /**
     * id : 5
     * app : acdd
     * title :
     * content : 商家1354****016发起提现870元，已到账！
     * createtime : 1509673874
     * url : http://m.cdrwx.cn/app.php/Spread/index/ESEEShare.html
     */

    private String id;
    private String app;
    private String title;
    private String content;
    private String createtime;
    private String url;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id='" + id + '\'' +
                ", app='" + app + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
