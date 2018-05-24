package com.sinata.rwxchina.component_basic.car.entity;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/27
 * @describe 汽车服务分类实体类
 * @modifyRecord
 */

public class CarClassfiyInfo {
    private String title;
    private int count;
    private List<CarGoodsInfo> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CarGoodsInfo> getList() {
        return list;
    }

    public void setList(List<CarGoodsInfo> list) {
        this.list = list;
    }
}
