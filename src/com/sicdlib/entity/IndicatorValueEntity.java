package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class IndicatorValueEntity {
    private String indicatorValueId;
    private String indicatorValue;
    private String indexName;
    private String dimensionName;
    private String dimensionValue;
    private String startTime;



    private String endTime;
    /** 多个指标对应一个舆情对象 */
    private ObjectEntity objectEntity;

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public String getIndicatorValueId() {
        return indicatorValueId;
    }

    public void setIndicatorValueId(String indicatorValueId) {
        this.indicatorValueId = indicatorValueId;
    }

    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionValue() {
        return dimensionValue;
    }

    public void setDimensionValue(String dimensionValue) {
        this.dimensionValue = dimensionValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
