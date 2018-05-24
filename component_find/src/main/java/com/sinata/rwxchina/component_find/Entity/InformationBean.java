package com.sinata.rwxchina.component_find.Entity;

/**
 * @author:wj
 * @datetime：2017/11/14
 * @describe：
 * @modifyRecord:
 */


public class InformationBean {


    /**
     * news_id : 853
     * title : 问、买、查、赔一体化 百川汽车保险销售公司成立
     * pic : http://api.jisuapi.com/news/upload/201711/13090513_30766.jpg
     * content : 青岛财经日报讯(记者 宋大伟) 今日上午，交运集团青岛百川汽车保险销售有限公司成立仪式在交运集团举行...
     * time : 2017-11-12 17:52:00
     * src : 青岛财经网
     * filename : 97600b236289197aa3f52a8e2a14a4a7
     */

    private String news_id;
    private String title;
    private String pic;
    private String content;
    private String time;
    private String src;
    private String filename;

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
