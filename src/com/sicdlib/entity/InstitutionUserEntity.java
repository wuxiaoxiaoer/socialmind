package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "institution_user", schema = "socialmind", catalog = "")
public class InstitutionUserEntity {
    private String bossName;
    private String idFront;
    private String idBack;
    private String institutionName;
    private String type;
    private String institudeCode;
    private String registerGraph;
    private String userId;

    @Basic
    @Column(name = "bossName")
    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    @Basic
    @Column(name = "IDFront")
    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    @Basic
    @Column(name = "IDBack")
    public String getIdBack() {
        return idBack;
    }

    public void setIdBack(String idBack) {
        this.idBack = idBack;
    }

    @Basic
    @Column(name = "institutionName")
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "institudeCode")
    public String getInstitudeCode() {
        return institudeCode;
    }

    public void setInstitudeCode(String institudeCode) {
        this.institudeCode = institudeCode;
    }

    @Basic
    @Column(name = "registerGraph")
    public String getRegisterGraph() {
        return registerGraph;
    }

    public void setRegisterGraph(String registerGraph) {
        this.registerGraph = registerGraph;
    }

    @Id
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionUserEntity that = (InstitutionUserEntity) o;

        if (bossName != null ? !bossName.equals(that.bossName) : that.bossName != null) return false;
        if (idFront != null ? !idFront.equals(that.idFront) : that.idFront != null) return false;
        if (idBack != null ? !idBack.equals(that.idBack) : that.idBack != null) return false;
        if (institutionName != null ? !institutionName.equals(that.institutionName) : that.institutionName != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (institudeCode != null ? !institudeCode.equals(that.institudeCode) : that.institudeCode != null)
            return false;
        if (registerGraph != null ? !registerGraph.equals(that.registerGraph) : that.registerGraph != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bossName != null ? bossName.hashCode() : 0;
        result = 31 * result + (idFront != null ? idFront.hashCode() : 0);
        result = 31 * result + (idBack != null ? idBack.hashCode() : 0);
        result = 31 * result + (institutionName != null ? institutionName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (institudeCode != null ? institudeCode.hashCode() : 0);
        result = 31 * result + (registerGraph != null ? registerGraph.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
