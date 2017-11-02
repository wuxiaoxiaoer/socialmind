package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "spider_config_item", schema = "socialmind", catalog = "")
public class SpiderConfigItemEntity {
    private String spiderConfigItemId;
    private String spiderConfigId;
    private String configItemName;
    private String configItemValue;
    private String note;

    @Id
    @Column(name = "spiderConfigItemID")
    public String getSpiderConfigItemId() {
        return spiderConfigItemId;
    }

    public void setSpiderConfigItemId(String spiderConfigItemId) {
        this.spiderConfigItemId = spiderConfigItemId;
    }

    @Basic
    @Column(name = "spiderConfigID")
    public String getSpiderConfigId() {
        return spiderConfigId;
    }

    public void setSpiderConfigId(String spiderConfigId) {
        this.spiderConfigId = spiderConfigId;
    }

    @Basic
    @Column(name = "configItemName")
    public String getConfigItemName() {
        return configItemName;
    }

    public void setConfigItemName(String configItemName) {
        this.configItemName = configItemName;
    }

    @Basic
    @Column(name = "configItemValue")
    public String getConfigItemValue() {
        return configItemValue;
    }

    public void setConfigItemValue(String configItemValue) {
        this.configItemValue = configItemValue;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpiderConfigItemEntity that = (SpiderConfigItemEntity) o;

        if (spiderConfigItemId != null ? !spiderConfigItemId.equals(that.spiderConfigItemId) : that.spiderConfigItemId != null)
            return false;
        if (spiderConfigId != null ? !spiderConfigId.equals(that.spiderConfigId) : that.spiderConfigId != null)
            return false;
        if (configItemName != null ? !configItemName.equals(that.configItemName) : that.configItemName != null)
            return false;
        if (configItemValue != null ? !configItemValue.equals(that.configItemValue) : that.configItemValue != null)
            return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spiderConfigItemId != null ? spiderConfigItemId.hashCode() : 0;
        result = 31 * result + (spiderConfigId != null ? spiderConfigId.hashCode() : 0);
        result = 31 * result + (configItemName != null ? configItemName.hashCode() : 0);
        result = 31 * result + (configItemValue != null ? configItemValue.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
