package com.sinata.rwxchina.component_entertainment.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author:wj
 * @datetime：2017/12/15
 * @describe：美食实体类
 * @modifyRecord:
 */


public class NearFoodEntity  {

    private List<OtherBean> other;
    public List<OtherBean> getOther() {
        return other;
    }

    public void setOther(List<OtherBean> other) {
        this.other = other;
    }

    public static class OtherBean extends CommodityDetails {

    }
}
