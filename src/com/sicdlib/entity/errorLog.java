package com.sicdlib.entity;

/**
 * Created by zcx on 2017/12/11.
 */
public class errorLog {

    private String errorLogId;
    private String website;
    private String url;
    private String logTime;
    private String errorItem;
    private String errorDetail;

    public String getErrorLogId() {
        return errorLogId;
    }

    public void setErrorLogId(String errorLogId) {
        this.errorLogId = errorLogId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(String errorItem) {
        this.errorItem = errorItem;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
