package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class CompanyUserEntity {
    private String userId;
    private String bossName;
    private String idFront;
    private String companyName;
    private String type;
    private String idBack;
    private String businessLicence;

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

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
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

    public String getIdBack() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyUserEntity that = (CompanyUserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bossName != null ? !bossName.equals(that.bossName) : that.bossName != null) return false;
        if (idFront != null ? !idFront.equals(that.idFront) : that.idFront != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idBack != null ? !idBack.equals(that.idBack) : that.idBack != null) return false;
        if (businessLicence != null ? !businessLicence.equals(that.businessLicence) : that.businessLicence != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bossName != null ? bossName.hashCode() : 0);
        result = 31 * result + (idFront != null ? idFront.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idBack != null ? idBack.hashCode() : 0);
        result = 31 * result + (businessLicence != null ? businessLicence.hashCode() : 0);
        return result;
    }
}
