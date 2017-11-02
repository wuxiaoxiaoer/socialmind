package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "bulletin", schema = "socialmind", catalog = "")
public class BulletinEntity {
    private String bulletinId;
    private String name;
    private String introduction;
    private String bulletinUrl;
    private String bulletinType;
    private String generateTime;
    private String bulletinStyleId;

    @Id
    @Column(name = "bulletinID")
    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "bulletinURL")
    public String getBulletinUrl() {
        return bulletinUrl;
    }

    public void setBulletinUrl(String bulletinUrl) {
        this.bulletinUrl = bulletinUrl;
    }

    @Basic
    @Column(name = "bulletinType")
    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }

    @Basic
    @Column(name = "generateTime")
    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    @Basic
    @Column(name = "bulletinStyleID")
    public String getBulletinStyleId() {
        return bulletinStyleId;
    }

    public void setBulletinStyleId(String bulletinStyleId) {
        this.bulletinStyleId = bulletinStyleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulletinEntity that = (BulletinEntity) o;

        if (bulletinId != null ? !bulletinId.equals(that.bulletinId) : that.bulletinId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (bulletinUrl != null ? !bulletinUrl.equals(that.bulletinUrl) : that.bulletinUrl != null) return false;
        if (bulletinType != null ? !bulletinType.equals(that.bulletinType) : that.bulletinType != null) return false;
        if (generateTime != null ? !generateTime.equals(that.generateTime) : that.generateTime != null) return false;
        if (bulletinStyleId != null ? !bulletinStyleId.equals(that.bulletinStyleId) : that.bulletinStyleId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bulletinId != null ? bulletinId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (bulletinUrl != null ? bulletinUrl.hashCode() : 0);
        result = 31 * result + (bulletinType != null ? bulletinType.hashCode() : 0);
        result = 31 * result + (generateTime != null ? generateTime.hashCode() : 0);
        result = 31 * result + (bulletinStyleId != null ? bulletinStyleId.hashCode() : 0);
        return result;
    }
}
