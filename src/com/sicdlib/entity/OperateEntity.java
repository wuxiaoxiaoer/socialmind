package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "operate", schema = "socialmind", catalog = "")
public class OperateEntity {
    private String operateId;
    private String operateName;

    @Id
    @Column(name = "operateID")
    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    @Basic
    @Column(name = "operateName")
    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperateEntity that = (OperateEntity) o;

        if (operateId != null ? !operateId.equals(that.operateId) : that.operateId != null) return false;
        if (operateName != null ? !operateName.equals(that.operateName) : that.operateName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operateId != null ? operateId.hashCode() : 0;
        result = 31 * result + (operateName != null ? operateName.hashCode() : 0);
        return result;
    }
}
