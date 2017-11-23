package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/7.
 */
public class DynamicSensitiveComment {
    private CommentEntity comment;
    private String sensitiveWord;
    private String sensitiveType;

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    public String getSensitiveType() {
        return sensitiveType;
    }

    public void setSensitiveType(String sensitiveType) {
        this.sensitiveType = sensitiveType;
    }
}
