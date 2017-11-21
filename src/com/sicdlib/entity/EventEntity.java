package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class EventEntity {
    private String objectId;
    private String eventSummary;
    private String eventBeginTime;
    private String eventEndTime;
    private String introduction;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEventBeginTime() {
        return eventBeginTime;
    }

    public void setEventBeginTime(String eventBeginTime) {
        this.eventBeginTime = eventBeginTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
