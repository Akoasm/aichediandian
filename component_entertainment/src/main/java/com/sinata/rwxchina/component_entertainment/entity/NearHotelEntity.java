package com.sinata.rwxchina.component_entertainment.entity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/15
 * @describe：酒店实体类
 * @modifyRecord:
 */


public class NearHotelEntity {


    private List<TopBean> top;
    private List<BodyBean> other;

    public List<TopBean> getTop() {
        return top;
    }

    public void setTop(List<TopBean> top) {
        this.top = top;
    }

    public List<BodyBean> getOther() {
        return other;
    }

    public void setOther(List<BodyBean> other) {
        this.other = other;
    }

    public static class TopBean extends CommodityDetails{

    }

    public static class BodyBean extends CommodityDetails{
    }
}
