package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "comment", schema = "socialmind", catalog = "")
public class CommentEntity {
    private String commentId;
    private String userId;
    private String commentContent;
    private String fathercommentId;
    private String commentTime;
    private String objectId;

    @Id
    @Column(name = "commentID")
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "commentContent")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "fathercommentID")
    public String getFathercommentId() {
        return fathercommentId;
    }

    public void setFathercommentId(String fathercommentId) {
        this.fathercommentId = fathercommentId;
    }

    @Basic
    @Column(name = "commentTime")
    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    @Basic
    @Column(name = "objectID")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (commentContent != null ? !commentContent.equals(that.commentContent) : that.commentContent != null)
            return false;
        if (fathercommentId != null ? !fathercommentId.equals(that.fathercommentId) : that.fathercommentId != null)
            return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (commentContent != null ? commentContent.hashCode() : 0);
        result = 31 * result + (fathercommentId != null ? fathercommentId.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        return result;
    }
}
