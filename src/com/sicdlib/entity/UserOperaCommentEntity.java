package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class UserOperaCommentEntity {
    private String userOperaCommentId;
    private String userId;
    private String commentId;
    private String operaType;

    public String getUserOperaCommentId() {
        return userOperaCommentId;
    }

    public void setUserOperaCommentId(String userOperaCommentId) {
        this.userOperaCommentId = userOperaCommentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOperaCommentEntity that = (UserOperaCommentEntity) o;

        if (userOperaCommentId != null ? !userOperaCommentId.equals(that.userOperaCommentId) : that.userOperaCommentId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (operaType != null ? !operaType.equals(that.operaType) : that.operaType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userOperaCommentId != null ? userOperaCommentId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        result = 31 * result + (operaType != null ? operaType.hashCode() : 0);
        return result;
    }
}
