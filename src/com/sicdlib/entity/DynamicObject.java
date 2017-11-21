package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/7.
 */
public class DynamicObject {
    private ObjectEntity object;
    private EventEntity event;
    private EntityEntity entity;

    public ObjectEntity getObject() {
        return object;
    }

    public void setObject(ObjectEntity object) {
        this.object = object;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public EntityEntity getEntity() {
        return entity;
    }

    public void setEntity(EntityEntity entity) {
        this.entity = entity;
    }
}
