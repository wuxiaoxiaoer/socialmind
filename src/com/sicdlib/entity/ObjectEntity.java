package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DeMH on 2017/11/2.
 */
public class ObjectEntity {
    private String objectId;
    private Integer scanNumber;
    private Integer collectNumber;
    private Integer likeNumber;
    private Integer trueNumber;
    private Integer falseNumber;
    private String name;
    private String introduction;
    private String credibility;
    private String addTime;
    private String place;
    private String objectType;
    private String objectFatherId;
    private Integer commentNumber;
    private String startTime;
    private String endTime;
    /** 一个舆情对象对应着多个指标值 */
    private Set<IndicatorValueEntity> indicatorValues = new HashSet<>();
    /** 一个对象对应着多个文章 */
    private Set<ArticleEntity> articles = new HashSet<>();
    /** 一个对象包含对个用户操作 */
    private Set<UserOperaEntity> userOperas = new HashSet<>();
    /** 一个对象包含多个评论 */
    private Set<CommentEntity> comments = new HashSet<>();
    /** 一个对象包含多个对象用户中间表 */
    private Set<ObjectUserEntity> objectUsers = new HashSet<>();

    public String getCredibility() {
        return credibility;
    }

    public void setCredibility(String credibility) {
        this.credibility = credibility;
    }

    public Set<ObjectUserEntity> getObjectUsers() {
        return objectUsers;
    }

    public void setObjectUsers(Set<ObjectUserEntity> objectUsers) {
        this.objectUsers = objectUsers;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<UserOperaEntity> getUserOperas() {
        return userOperas;
    }

    public void setUserOperas(Set<UserOperaEntity> userOperas) {
        this.userOperas = userOperas;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public Set<IndicatorValueEntity> getIndicatorValues() {
        return indicatorValues;
    }

    public void setIndicatorValues(Set<IndicatorValueEntity> indicatorValues) {
        this.indicatorValues = indicatorValues;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getScanNumber() {
        return scanNumber;
    }

    public void setScanNumber(Integer scanNumber) {
        this.scanNumber = scanNumber;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber(Integer trueNumber) {
        this.trueNumber = trueNumber;
    }

    public Integer getFalseNumber() {
        return falseNumber;
    }

    public void setFalseNumber(Integer falseNumber) {
        this.falseNumber = falseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectFatherId() {
        return objectFatherId;
    }

    public void setObjectFatherId(String objectFatherId) {
        this.objectFatherId = objectFatherId;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObjectEntity that = (ObjectEntity) o;

        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) {
            return false;
        }
        if (scanNumber != null ? !scanNumber.equals(that.scanNumber) : that.scanNumber != null) {
            return false;
        }
        if (collectNumber != null ? !collectNumber.equals(that.collectNumber) : that.collectNumber != null){
            return false;
        }
        if (likeNumber != null ? !likeNumber.equals(that.likeNumber) : that.likeNumber != null) {
            return false;
        }
        if (trueNumber != null ? !trueNumber.equals(that.trueNumber) : that.trueNumber != null){
            return false;
        }
        if (falseNumber != null ? !falseNumber.equals(that.falseNumber) : that.falseNumber != null){
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null){
            return false;
        }
        if (addTime != null ? !addTime.equals(that.addTime) : that.addTime != null){
            return false;
        }
        if (place != null ? !place.equals(that.place) : that.place != null){
            return false;
        }
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null){
            return false;
        }
        if (objectFatherId != null ? !objectFatherId.equals(that.objectFatherId) : that.objectFatherId != null){
            return false;
        }

        if (commentNumber != null ? !commentNumber.equals(that.commentNumber) : that.commentNumber != null){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId != null ? objectId.hashCode() : 0;
        result = 31 * result + (scanNumber != null ? scanNumber.hashCode() : 0);
        result = 31 * result + (collectNumber != null ? collectNumber.hashCode() : 0);
        result = 31 * result + (likeNumber != null ? likeNumber.hashCode() : 0);
        result = 31 * result + (trueNumber != null ? trueNumber.hashCode() : 0);
        result = 31 * result + (falseNumber != null ? falseNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (objectFatherId != null ? objectFatherId.hashCode() : 0);
        result = 31 * result + (commentNumber != null ? commentNumber.hashCode() : 0);
        return result;
    }
}
