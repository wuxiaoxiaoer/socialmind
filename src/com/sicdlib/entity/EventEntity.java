package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class EventEntity {
    private String objectId;
    private String eventSummary;
    private String eventBeainTime;
    private String eventOverTime;
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

    public String getEventBeainTime() {
        return eventBeainTime;
    }

    public void setEventBeainTime(String eventBeainTime) {
        this.eventBeainTime = eventBeainTime;
    }

    public String getEventOverTime() {
        return eventOverTime;
    }

    public void setEventOverTime(String eventOverTime) {
        this.eventOverTime = eventOverTime;
    }

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

        EventEntity that = (EventEntity) o;

        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (eventSummary != null ? !eventSummary.equals(that.eventSummary) : that.eventSummary != null) return false;
        if (eventBeainTime != null ? !eventBeainTime.equals(that.eventBeainTime) : that.eventBeainTime != null)
            return false;
        if (eventOverTime != null ? !eventOverTime.equals(that.eventOverTime) : that.eventOverTime != null)
            return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId != null ? objectId.hashCode() : 0;
        result = 31 * result + (eventSummary != null ? eventSummary.hashCode() : 0);
        result = 31 * result + (eventBeainTime != null ? eventBeainTime.hashCode() : 0);
        result = 31 * result + (eventOverTime != null ? eventOverTime.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }
}
