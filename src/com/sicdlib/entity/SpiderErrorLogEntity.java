package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class SpiderErrorLogEntity {
    private String logId;
    private String ip;
    private String errorItem;
    private String errorDetails;
    private String websiteId;
    private String spiderId;
    private String errorTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(String errorItem) {
        this.errorItem = errorItem;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getSpiderId() {
        return spiderId;
    }

    public void setSpiderId(String spiderId) {
        this.spiderId = spiderId;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpiderErrorLogEntity that = (SpiderErrorLogEntity) o;

        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (errorItem != null ? !errorItem.equals(that.errorItem) : that.errorItem != null) return false;
        if (errorDetails != null ? !errorDetails.equals(that.errorDetails) : that.errorDetails != null) return false;
        if (websiteId != null ? !websiteId.equals(that.websiteId) : that.websiteId != null) return false;
        if (spiderId != null ? !spiderId.equals(that.spiderId) : that.spiderId != null) return false;
        if (errorTime != null ? !errorTime.equals(that.errorTime) : that.errorTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (errorItem != null ? errorItem.hashCode() : 0);
        result = 31 * result + (errorDetails != null ? errorDetails.hashCode() : 0);
        result = 31 * result + (websiteId != null ? websiteId.hashCode() : 0);
        result = 31 * result + (spiderId != null ? spiderId.hashCode() : 0);
        result = 31 * result + (errorTime != null ? errorTime.hashCode() : 0);
        return result;
    }
}
