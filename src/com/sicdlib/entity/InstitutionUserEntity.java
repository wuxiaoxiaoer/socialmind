package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class InstitutionUserEntity {
    private String bossName;
    private String idFrontUrl;
    private String idBackUrl;
    private String institutionName;
    private String type;
    private String institudeCode;
    private String institudeCodeUrl;
    private String userId;

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstitudeCode() {
        return institudeCode;
    }

    public void setInstitudeCode(String institudeCode) {
        this.institudeCode = institudeCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getInstitudeCodeUrl() {
        return institudeCodeUrl;
    }

    public void setInstitudeCodeUrl(String institudeCodeUrl) {
        this.institudeCodeUrl = institudeCodeUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionUserEntity that = (InstitutionUserEntity) o;

        if (bossName != null ? !bossName.equals(that.bossName) : that.bossName != null) return false;
        if (idFrontUrl != null ? !idFrontUrl.equals(that.idFrontUrl) : that.idFrontUrl != null) return false;
        if (idBackUrl != null ? !idBackUrl.equals(that.idBackUrl) : that.idBackUrl != null) return false;
        if (institutionName != null ? !institutionName.equals(that.institutionName) : that.institutionName != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (institudeCode != null ? !institudeCode.equals(that.institudeCode) : that.institudeCode != null)
            return false;
        if (institudeCodeUrl != null ? !institudeCodeUrl.equals(that.institudeCodeUrl) : that.institudeCodeUrl != null)
            return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    @Override
    public int hashCode() {
        int result = bossName != null ? bossName.hashCode() : 0;
        result = 31 * result + (idFrontUrl != null ? idFrontUrl.hashCode() : 0);
        result = 31 * result + (idBackUrl != null ? idBackUrl.hashCode() : 0);
        result = 31 * result + (institutionName != null ? institutionName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (institudeCode != null ? institudeCode.hashCode() : 0);
        result = 31 * result + (institudeCodeUrl != null ? institudeCodeUrl.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
