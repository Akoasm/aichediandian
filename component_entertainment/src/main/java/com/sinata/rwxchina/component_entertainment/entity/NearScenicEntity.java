package com.sinata.rwxchina.component_entertainment.entity;

import com.google.gson.annotations.SerializedName;
import com.sinata.rwxchina.basiclib.commonclass.entity.ScenicEntity;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/15
 * @describe：景区实体类
 * @modifyRecord:
 */


public class NearScenicEntity {


    private List<TopBean> top;
    @SerializedName("other")
    private List<BodyBean> body;

    public List<TopBean> getTop() {
        return top;
    }

    public void setTop(List<TopBean> top) {
        this.top = top;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class TopBean extends ScenicEntity{

    }

    public static class BodyBean extends ScenicEntity{

    }
}
