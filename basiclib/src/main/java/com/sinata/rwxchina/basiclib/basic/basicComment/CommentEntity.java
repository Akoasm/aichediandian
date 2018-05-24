package com.sinata.rwxchina.basiclib.basic.basicComment;

import java.util.List;

/**
 * @author HRR
 * @datetime 2017/12/18
 * @describe 所有分类评论实体类
 * @modifyRecord
 */

public class CommentEntity {

    /**
     * id : 1
     * createdate : 2017-12-06 16:05:40
     * evaluation_score : 5
     * evaluation_comment : 可以
     * evaluation_comment_pic : ["/Uploads/User/2000028/b73954738020d9e101e9e8487a8819f2.jpg","/Uploads/User/2000028/6095a24210f5ed6134396b855f20341f.jpg","/Uploads/User/2000028/655d0acf4ef371218ec749d771ae31f3.jpg","/Uploads/User/2000028/d65933f6c5bb8a2f7264619fdc4ccbf7.jpg"]
     * uid : 4
     * user_head :
     * user_name : 测试别删
     */

    private String id;
    private String createdate;
    private String evaluation_score;
    private String evaluation_comment;
    private String uid;
    private String user_head;
    private String user_name;
    private List<String> evaluation_comment_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getEvaluation_score() {
        return evaluation_score;
    }

    public void setEvaluation_score(String evaluation_score) {
        this.evaluation_score = evaluation_score;
    }

    public String getEvaluation_comment() {
        return evaluation_comment;
    }

    public void setEvaluation_comment(String evaluation_comment) {
        this.evaluation_comment = evaluation_comment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getEvaluation_comment_pic() {
        return evaluation_comment_pic;
    }

    public void setEvaluation_comment_pic(List<String> evaluation_comment_pic) {
        this.evaluation_comment_pic = evaluation_comment_pic;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id='" + id + '\'' +
                ", createdate='" + createdate + '\'' +
                ", evaluation_score='" + evaluation_score + '\'' +
                ", evaluation_comment='" + evaluation_comment + '\'' +
                ", uid='" + uid + '\'' +
                ", user_head='" + user_head + '\'' +
                ", user_name='" + user_name + '\'' +
                ", evaluation_comment_pic=" + evaluation_comment_pic +
                '}';
    }
}
