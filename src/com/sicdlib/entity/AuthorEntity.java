package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DeMH on 2017/11/2.
 */
public class AuthorEntity {
    private String authorId;
    private String name;
    private String sex;
    private String birthday;
    private String address;
    private Integer focusNumber;
    private Integer fansNumber;
    private Integer age;
    private Integer friendNumber;
    private Integer replayNumber;
    private Integer clickNumber;
    private Integer loginNumber;
    private String introduction;
    private String registrationTime;
    private String educationBackground;
    private String career;
    private Integer elitePostNumber;
    private Integer score;
    private String onlineTime;
    private Integer fameDegree;
    private String lastLoginTime;
    private String workUnit;
    private String updateArticalTime;
    private String phoneNumber;
    private Integer reputation;
    private Integer getReward;
    private Integer giveReward;
    private Byte isCertification;
    private String portraitUrl;
    private String realName;
    private String sexualOrientation;
    private String email;
    private String qq;
    private String bloodType;
    private Byte isBigV;
    /** 一个作者对应多个文章 */
    private Set<ArticleEntity> articles = new HashSet<>();
    /** 一个作者包含多个评论*/
    private Set<ArticleCommentEntity> articleComments = new HashSet<>();

    public Set<ArticleCommentEntity> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Set<ArticleCommentEntity> articleComments) {
        this.articleComments = articleComments;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFocusNumber() {
        return focusNumber;
    }

    public void setFocusNumber(Integer focusNumber) {
        this.focusNumber = focusNumber;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getFriendNumber() {
        return friendNumber;
    }

    public void setFriendNumber(Integer friendNumber) {
        this.friendNumber = friendNumber;
    }

    public Integer getReplayNumber() {
        return replayNumber;
    }

    public void setReplayNumber(Integer replayNumber) {
        this.replayNumber = replayNumber;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Integer getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(Integer loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getElitePostNumber() {
        return elitePostNumber;
    }

    public void setElitePostNumber(Integer elitePostNumber) {
        this.elitePostNumber = elitePostNumber;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getFameDegree() {
        return fameDegree;
    }

    public void setFameDegree(Integer fameDegree) {
        this.fameDegree = fameDegree;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getUpdateArticalTime() {
        return updateArticalTime;
    }

    public void setUpdateArticalTime(String updateArticalTime) {
        this.updateArticalTime = updateArticalTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getGetReward() {
        return getReward;
    }

    public void setGetReward(Integer getReward) {
        this.getReward = getReward;
    }

    public Integer getGiveReward() {
        return giveReward;
    }

    public void setGiveReward(Integer giveReward) {
        this.giveReward = giveReward;
    }

    public Byte getIsCertification() {
        return isCertification;
    }

    public void setIsCertification(Byte isCertification) {
        this.isCertification = isCertification;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Byte getIsBigV() {
        return isBigV;
    }

    public void setIsBigV(Byte isBigV) {
        this.isBigV = isBigV;
    }

}
