package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class UserOperaEntity {
    private String operaId;
    private String userId;
    private String objectId;
    private String operaType;

    public String getOperaId() {
        return operaId;
    }

    public void setOperaId(String operaId) {
        this.operaId = operaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

        UserOperaEntity that = (UserOperaEntity) o;

        if (operaId != null ? !operaId.equals(that.operaId) : that.operaId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (operaType != null ? !operaType.equals(that.operaType) : that.operaType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operaId != null ? operaId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (operaType != null ? operaType.hashCode() : 0);
        return result;
    }
}
