package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class CompanyUserEntity {
    private String userId;
    private String bossName;
    private String idFrontUrl;
    private String companyName;
    private String type;
    private String idBackUrl;
    private String businessLicenceUrl;
    private String businessLicenceId;

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


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getBusinessLicenceUrl() {
        return businessLicenceUrl;
    }

    public void setBusinessLicenceUrl(String businessLicenceUrl) {
        this.businessLicenceUrl = businessLicenceUrl;
    }

    public String getBusinessLicenceId() {
        return businessLicenceId;
    }

    public void setBusinessLicenceId(String businessLicenceId) {
        this.businessLicenceId = businessLicenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyUserEntity that = (CompanyUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bossName != null ? !bossName.equals(that.bossName) : that.bossName != null) return false;
        if (idFrontUrl != null ? !idFrontUrl.equals(that.idFrontUrl) : that.idFrontUrl != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idBackUrl != null ? !idBackUrl.equals(that.idBackUrl) : that.idBackUrl != null) return false;
        if (businessLicenceUrl != null ? !businessLicenceUrl.equals(that.businessLicenceUrl) : that.businessLicenceUrl != null)
            return false;
        return businessLicenceId != null ? businessLicenceId.equals(that.businessLicenceId) : that.businessLicenceId == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bossName != null ? bossName.hashCode() : 0);
        result = 31 * result + (idFrontUrl != null ? idFrontUrl.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idBackUrl != null ? idBackUrl.hashCode() : 0);
        result = 31 * result + (businessLicenceUrl != null ? businessLicenceUrl.hashCode() : 0);
        result = 31 * result + (businessLicenceId != null ? businessLicenceId.hashCode() : 0);
        return result;
    }
}
