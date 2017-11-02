package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "clean_log_manager", schema = "socialmind", catalog = "")
public class CleanLogManagerEntity {
    private String cleanLogId;
    private String userId;
    private String selfDefiningValue;
    private String cleanTime;
    private String cleanStategyId;
    private String cleanLocationId;

    @Id
    @Column(name = "cleanLogID")
    public String getCleanLogId() {
        return cleanLogId;
    }

    public void setCleanLogId(String cleanLogId) {
        this.cleanLogId = cleanLogId;
    }

    @Basic
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "self_defining_value")
    public String getSelfDefiningValue() {
        return selfDefiningValue;
    }

    public void setSelfDefiningValue(String selfDefiningValue) {
        this.selfDefiningValue = selfDefiningValue;
    }

    @Basic
    @Column(name = "cleanTime")
    public String getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(String cleanTime) {
        this.cleanTime = cleanTime;
    }

    @Basic
    @Column(name = "cleanStategyID")
    public String getCleanStategyId() {
        return cleanStategyId;
    }

    public void setCleanStategyId(String cleanStategyId) {
        this.cleanStategyId = cleanStategyId;
    }

    @Basic
    @Column(name = "cleanLocationID")
    public String getCleanLocationId() {
        return cleanLocationId;
    }

    public void setCleanLocationId(String cleanLocationId) {
        this.cleanLocationId = cleanLocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanLogManagerEntity that = (CleanLogManagerEntity) o;

        if (cleanLogId != null ? !cleanLogId.equals(that.cleanLogId) : that.cleanLogId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (selfDefiningValue != null ? !selfDefiningValue.equals(that.selfDefiningValue) : that.selfDefiningValue != null)
            return false;
        if (cleanTime != null ? !cleanTime.equals(that.cleanTime) : that.cleanTime != null) return false;
        if (cleanStategyId != null ? !cleanStategyId.equals(that.cleanStategyId) : that.cleanStategyId != null)
            return false;
        if (cleanLocationId != null ? !cleanLocationId.equals(that.cleanLocationId) : that.cleanLocationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cleanLogId != null ? cleanLogId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (selfDefiningValue != null ? selfDefiningValue.hashCode() : 0);
        result = 31 * result + (cleanTime != null ? cleanTime.hashCode() : 0);
        result = 31 * result + (cleanStategyId != null ? cleanStategyId.hashCode() : 0);
        result = 31 * result + (cleanLocationId != null ? cleanLocationId.hashCode() : 0);
        return result;
    }
}
