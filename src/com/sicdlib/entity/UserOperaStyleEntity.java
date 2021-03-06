package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class UserOperaStyleEntity {
    private String userStyleId;
    private String userId;
    private String bulletinStyleId;
    private String operaId;
    private String operaType;

    public String getUserStyleId() {
        return userStyleId;
    }

    public void setUserStyleId(String userStyleId) {
        this.userStyleId = userStyleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBulletinStyleId() {
        return bulletinStyleId;
    }

    public void setBulletinStyleId(String bulletinStyleId) {
        this.bulletinStyleId = bulletinStyleId;
    }

    public String getOperaId() {
        return operaId;
    }

    public void setOperaId(String operaId) {
        this.operaId = operaId;
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

        UserOperaStyleEntity that = (UserOperaStyleEntity) o;

        if (userStyleId != null ? !userStyleId.equals(that.userStyleId) : that.userStyleId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bulletinStyleId != null ? !bulletinStyleId.equals(that.bulletinStyleId) : that.bulletinStyleId != null)
            return false;
        if (operaId != null ? !operaId.equals(that.operaId) : that.operaId != null) return false;
        if (operaType != null ? !operaType.equals(that.operaType) : that.operaType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userStyleId != null ? userStyleId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (bulletinStyleId != null ? bulletinStyleId.hashCode() : 0);
        result = 31 * result + (operaId != null ? operaId.hashCode() : 0);
        result = 31 * result + (operaType != null ? operaType.hashCode() : 0);
        return result;
    }
}
