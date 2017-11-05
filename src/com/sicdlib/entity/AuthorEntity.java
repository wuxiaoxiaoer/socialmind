package com.sicdlib.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (focusNumber != null ? !focusNumber.equals(that.focusNumber) : that.focusNumber != null) return false;
        if (fansNumber != null ? !fansNumber.equals(that.fansNumber) : that.fansNumber != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (friendNumber != null ? !friendNumber.equals(that.friendNumber) : that.friendNumber != null) return false;
        if (replayNumber != null ? !replayNumber.equals(that.replayNumber) : that.replayNumber != null) return false;
        if (clickNumber != null ? !clickNumber.equals(that.clickNumber) : that.clickNumber != null) return false;
        if (loginNumber != null ? !loginNumber.equals(that.loginNumber) : that.loginNumber != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (registrationTime != null ? !registrationTime.equals(that.registrationTime) : that.registrationTime != null)
            return false;
        if (educationBackground != null ? !educationBackground.equals(that.educationBackground) : that.educationBackground != null)
            return false;
        if (career != null ? !career.equals(that.career) : that.career != null) return false;
        if (elitePostNumber != null ? !elitePostNumber.equals(that.elitePostNumber) : that.elitePostNumber != null)
            return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (onlineTime != null ? !onlineTime.equals(that.onlineTime) : that.onlineTime != null) return false;
        if (fameDegree != null ? !fameDegree.equals(that.fameDegree) : that.fameDegree != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(that.lastLoginTime) : that.lastLoginTime != null)
            return false;
        if (workUnit != null ? !workUnit.equals(that.workUnit) : that.workUnit != null) return false;
        if (updateArticalTime != null ? !updateArticalTime.equals(that.updateArticalTime) : that.updateArticalTime != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (reputation != null ? !reputation.equals(that.reputation) : that.reputation != null) return false;
        if (getReward != null ? !getReward.equals(that.getReward) : that.getReward != null) return false;
        if (giveReward != null ? !giveReward.equals(that.giveReward) : that.giveReward != null) return false;
        if (isCertification != null ? !isCertification.equals(that.isCertification) : that.isCertification != null)
            return false;
        if (portraitUrl != null ? !portraitUrl.equals(that.portraitUrl) : that.portraitUrl != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;
        if (sexualOrientation != null ? !sexualOrientation.equals(that.sexualOrientation) : that.sexualOrientation != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (bloodType != null ? !bloodType.equals(that.bloodType) : that.bloodType != null) return false;
        if (isBigV != null ? !isBigV.equals(that.isBigV) : that.isBigV != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (focusNumber != null ? focusNumber.hashCode() : 0);
        result = 31 * result + (fansNumber != null ? fansNumber.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (friendNumber != null ? friendNumber.hashCode() : 0);
        result = 31 * result + (replayNumber != null ? replayNumber.hashCode() : 0);
        result = 31 * result + (clickNumber != null ? clickNumber.hashCode() : 0);
        result = 31 * result + (loginNumber != null ? loginNumber.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        result = 31 * result + (educationBackground != null ? educationBackground.hashCode() : 0);
        result = 31 * result + (career != null ? career.hashCode() : 0);
        result = 31 * result + (elitePostNumber != null ? elitePostNumber.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (onlineTime != null ? onlineTime.hashCode() : 0);
        result = 31 * result + (fameDegree != null ? fameDegree.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (workUnit != null ? workUnit.hashCode() : 0);
        result = 31 * result + (updateArticalTime != null ? updateArticalTime.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (reputation != null ? reputation.hashCode() : 0);
        result = 31 * result + (getReward != null ? getReward.hashCode() : 0);
        result = 31 * result + (giveReward != null ? giveReward.hashCode() : 0);
        result = 31 * result + (isCertification != null ? isCertification.hashCode() : 0);
        result = 31 * result + (portraitUrl != null ? portraitUrl.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (sexualOrientation != null ? sexualOrientation.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (bloodType != null ? bloodType.hashCode() : 0);
        result = 31 * result + (isBigV != null ? isBigV.hashCode() : 0);
        return result;
    }
}
