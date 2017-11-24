package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class UserOperaEntity {
    private String operaId;
    private String operaType;
    private String operaTime;
    /**多个用户操作属于一个用户 */
    private UserEntity userEntity;
    /**多个用户操作属于一个舆情对象*/
    private ObjectEntity objectEntity;

    public String getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(String operaTime) {
        this.operaTime = operaTime;
    }

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

        UserOperaEntity that = (UserOperaEntity) o;

        if (operaId != null ? !operaId.equals(that.operaId) : that.operaId != null) return false;
        if (operaType != null ? !operaType.equals(that.operaType) : that.operaType != null) return false;
        if (userEntity != null ? !userEntity.equals(that.userEntity) : that.userEntity != null) return false;
        return objectEntity != null ? objectEntity.equals(that.objectEntity) : that.objectEntity == null;

    }

    @Override
    public int hashCode() {
        int result = operaId != null ? operaId.hashCode() : 0;
        result = 31 * result + (operaType != null ? operaType.hashCode() : 0);
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        result = 31 * result + (objectEntity != null ? objectEntity.hashCode() : 0);
        return result;
    }
}
