package com.sinata.rwxchina.component_basic.basic.basiclist.entity;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe 排序工具类
 * @modifyRecord
 */

public class SortEntity {
    private String id;
    private String name;

    public SortEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SortEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
