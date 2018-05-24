package com.sinata.rwxchina.component_basic.basic.basicnotice;

/**
 * @author HRR
 * @datetime 2017/12/25
 * @describe 用户须知实体类
 * @modifyRecord
 */

public class NoticeInfo {
    /**须知标题*/
    private String title;
    /**须知内容*/
    private String content;

    public NoticeInfo(String title, String content) {
        this.title = title;
        this.content = content;
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

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
