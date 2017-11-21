package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/7.
 */
public class DynamicObjectIndexIndicator {
    private ObjectEntity object;
    private Double hotValue;

    public ObjectEntity getObject() {
        return object;
    }

    public void setObject(ObjectEntity object) {
        this.object = object;
    }

    public Double getHotValue() {
        return hotValue;
    }

    public void setHotValue(Double hotValue) {
        this.hotValue = hotValue;
    }
}
