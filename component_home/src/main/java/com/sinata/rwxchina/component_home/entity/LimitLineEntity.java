package com.sinata.rwxchina.component_home.entity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/28
 * @describe：
 * @modifyRecord:
 */


public class LimitLineEntity {

    /**
     * city : chengdu
     * cityname : 成都
     * date : 2017-12-28
     * week : 星期四
     * time : ["07:30-20:00"]
     * area : 二环路（含二环高架路和底层道路）与三环路（含）之间区域内所有道路。
     * summary : 所有号牌尾号限行。法定上班的周六周日不限行。
     * numberrule : 最后一位数字
     * number : 4和9
     */

    private String city;
    private String cityname;
    private String date;
    private String week;
    private String area;
    private String summary;
    private String numberrule;
    private String number;
    private List<String> time;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNumberrule() {
        return numberrule;
    }

    public void setNumberrule(String numberrule) {
        this.numberrule = numberrule;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
