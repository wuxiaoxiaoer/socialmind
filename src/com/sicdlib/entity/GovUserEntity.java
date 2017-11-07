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
    private String idFrontUrl;
    private String idBackUrl;
    private String govCodeUrl;

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

    public String getIdFrontUrl() {
        return idFrontUrl;
    }

    public void setIdFrontUrl(String idFrontUrl) {
        this.idFrontUrl = idFrontUrl;
    }

    public String getIdBackUrl() {
        return idBackUrl;
    }

    public void setIdBackUrl(String idBackUrl) {
        this.idBackUrl = idBackUrl;
    }

    public String getGovCodeUrl() {
        return govCodeUrl;
    }

    public void setGovCodeUrl(String govCodeUrl) {
        this.govCodeUrl = govCodeUrl;
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
        if (idFrontUrl != null ? !idFrontUrl.equals(that.idFrontUrl) : that.idFrontUrl != null) return false;
        if (idBackUrl != null ? !idBackUrl.equals(that.idBackUrl) : that.idBackUrl != null) return false;
        return govCodeUrl != null ? govCodeUrl.equals(that.govCodeUrl) : that.govCodeUrl == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bossName != null ? bossName.hashCode() : 0);
        result = 31 * result + (govName != null ? govName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (govCode != null ? govCode.hashCode() : 0);
        result = 31 * result + (idFrontUrl != null ? idFrontUrl.hashCode() : 0);
        result = 31 * result + (idBackUrl != null ? idBackUrl.hashCode() : 0);
        result = 31 * result + (govCodeUrl != null ? govCodeUrl.hashCode() : 0);
        return result;
    }
}
