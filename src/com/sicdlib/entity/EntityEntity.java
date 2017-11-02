package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "entity", schema = "socialmind", catalog = "")
public class EntityEntity {
    private String objectId;
    private String objectTypeId;
    private String introduction;

    @Id
    @Column(name = "objectID")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "objectTypeID")
    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityEntity that = (EntityEntity) o;

        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (objectTypeId != null ? !objectTypeId.equals(that.objectTypeId) : that.objectTypeId != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId != null ? objectId.hashCode() : 0;
        result = 31 * result + (objectTypeId != null ? objectTypeId.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }
}
