package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class CommentEntity {
    private String commentId;
    private String commentContent;
    private String fathercommentId;
    private String commentTime;
    /** 多个系统评论对应一个舆情对象 */
    private ObjectEntity objectEntity;
    /** 多个系统评论对应一个用户 */
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getFathercommentId() {
        return fathercommentId;
    }

    public void setFathercommentId(String fathercommentId) {
        this.fathercommentId = fathercommentId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

}
