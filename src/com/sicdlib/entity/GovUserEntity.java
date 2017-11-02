package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class GovUserEntity {
    private String userId;
    private String bossName;
    private String govName;
    private String type;
    private String govCode;
    private String idFront;
    private String idBack;
    private String provePicture;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getGovName() {
        return govName;
    }

    public void setGovName(String govName) {
        this.govName = govName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGovCode() {
        return govCode;
    }

    public void setGovCode(String govCode) {
        this.govCode = govCode;
    }

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdBack() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    public String getProvePicture() {
        return provePicture;
    }

    public void setProvePicture(String provePicture) {
        this.provePicture = provePicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GovUserEntity that = (GovUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bossName != null ? !bossName.equals(that.bossName) : that.bossName != null) return false;
        if (govName != null ? !govName.equals(that.govName) : that.govName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (govCode != null ? !govCode.equals(that.govCode) : that.govCode != null) return false;
        if (idFront != null ? !idFront.equals(that.idFront) : that.idFront != null) return false;
        if (idBack != null ? !idBack.equals(that.idBack) : that.idBack != null) return false;
        if (provePicture != null ? !provePicture.equals(that.provePicture) : that.provePicture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bossName != null ? bossName.hashCode() : 0);
        result = 31 * result + (govName != null ? govName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (govCode != null ? govCode.hashCode() : 0);
        result = 31 * result + (idFront != null ? idFront.hashCode() : 0);
        result = 31 * result + (idBack != null ? idBack.hashCode() : 0);
        result = 31 * result + (provePicture != null ? provePicture.hashCode() : 0);
        return result;
    }
}
