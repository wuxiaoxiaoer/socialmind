package com.sicdlib.entity;

public class EventSeedsBaseEntity {
    private String eventId;
    private String eventName;
    private String eventIntroduction;
    private String startTime;
    private String isTrue;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventIntroduction() {
        return eventIntroduction;
    }

    public void setEventIntroduction(String eventIntroduction) {
        this.eventIntroduction = eventIntroduction;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventSeedsBaseEntity that = (EventSeedsBaseEntity) o;

        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (eventIntroduction != null ? !eventIntroduction.equals(that.eventIntroduction) : that.eventIntroduction != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (isTrue != null ? !isTrue.equals(that.isTrue) : that.isTrue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventIntroduction != null ? eventIntroduction.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (isTrue != null ? isTrue.hashCode() : 0);
        return result;
    }
}
