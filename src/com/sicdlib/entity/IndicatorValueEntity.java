package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "indicator_value", schema = "socialmind", catalog = "")
public class IndicatorValueEntity {
    private String indicatorValueId;
    private String objectId;
    private String indicatorValue;
    private String indexName;
    private String dimensionName;
    private String timeSolt;

    @Id
    @Column(name = "indicatorValueID")
    public String getIndicatorValueId() {
        return indicatorValueId;
    }

    public void setIndicatorValueId(String indicatorValueId) {
        this.indicatorValueId = indicatorValueId;
    }

    @Basic
    @Column(name = "objectID")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "indicatorValue")
    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    @Basic
    @Column(name = "indexName")
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Basic
    @Column(name = "dimensionName")
    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    @Basic
    @Column(name = "timeSolt")
    public String getTimeSolt() {
        return timeSolt;
    }

    public void setTimeSolt(String timeSolt) {
        this.timeSolt = timeSolt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndicatorValueEntity that = (IndicatorValueEntity) o;

        if (indicatorValueId != null ? !indicatorValueId.equals(that.indicatorValueId) : that.indicatorValueId != null)
            return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (indicatorValue != null ? !indicatorValue.equals(that.indicatorValue) : that.indicatorValue != null)
            return false;
        if (indexName != null ? !indexName.equals(that.indexName) : that.indexName != null) return false;
        if (dimensionName != null ? !dimensionName.equals(that.dimensionName) : that.dimensionName != null)
            return false;
        if (timeSolt != null ? !timeSolt.equals(that.timeSolt) : that.timeSolt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indicatorValueId != null ? indicatorValueId.hashCode() : 0;
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (indicatorValue != null ? indicatorValue.hashCode() : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        result = 31 * result + (dimensionName != null ? dimensionName.hashCode() : 0);
        result = 31 * result + (timeSolt != null ? timeSolt.hashCode() : 0);
        return result;
    }
}
