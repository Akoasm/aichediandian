package com.sinata.rwxchina.component_basic.ktv.entity;

/**
 * @author HRR
 * @datetime 2017/12/19
 * @describe KTV预定时间实体类
 * @modifyRecord
 */

public class DateEntity {

    /**
     * date_title : 今天
     * date_content : 12-19
     * param_date : 2017-12-19
     * param_time : 1513612800
     */

    private String date_title;
    private String date_content;
    private String param_date;
    private int param_time;

    public String getDate_title() {
        return date_title;
    }

    public void setDate_title(String date_title) {
        this.date_title = date_title;
    }

    public String getDate_content() {
        return date_content;
    }

    public void setDate_content(String date_content) {
        this.date_content = date_content;
    }

    public String getParam_date() {
        return param_date;
    }

    public void setParam_date(String param_date) {
        this.param_date = param_date;
    }

    public int getParam_time() {
        return param_time;
    }

    public void setParam_time(int param_time) {
        this.param_time = param_time;
    }

    @Override
    public String toString() {
        return "DateEntity{" +
                "date_title='" + date_title + '\'' +
                ", date_content='" + date_content + '\'' +
                ", param_date='" + param_date + '\'' +
                ", param_time=" + param_time +
                '}';
    }
}
