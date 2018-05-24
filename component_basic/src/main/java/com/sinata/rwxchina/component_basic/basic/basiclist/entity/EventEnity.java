package com.sinata.rwxchina.component_basic.basic.basiclist.entity;

/**
 * @author HRR
 * @datetime 2017/12/26
 * @describe eventbus传递数据的实体类
 * @modifyRecord
 */

public class EventEnity {
    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EventEnity{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
