package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "log", schema = "socialmind", catalog = "")
public class LogEntity {
    private String logId;
    private String userId;
    private String ip;
    private String logTime;
    private String methodLogicName;
    private String method;
    private String param;
    private String sourcePage;
    private String targetPage;
    private String stayTime;

    @Id
    @Column(name = "logID")
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "IP")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "logTime")
    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "methodLogicName")
    public String getMethodLogicName() {
        return methodLogicName;
    }

    public void setMethodLogicName(String methodLogicName) {
        this.methodLogicName = methodLogicName;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "param")
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Basic
    @Column(name = "sourcePage")
    public String getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(String sourcePage) {
        this.sourcePage = sourcePage;
    }

    @Basic
    @Column(name = "targetPage")
    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

    @Basic
    @Column(name = "stayTime")
    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntity logEntity = (LogEntity) o;

        if (logId != null ? !logId.equals(logEntity.logId) : logEntity.logId != null) return false;
        if (userId != null ? !userId.equals(logEntity.userId) : logEntity.userId != null) return false;
        if (ip != null ? !ip.equals(logEntity.ip) : logEntity.ip != null) return false;
        if (logTime != null ? !logTime.equals(logEntity.logTime) : logEntity.logTime != null) return false;
        if (methodLogicName != null ? !methodLogicName.equals(logEntity.methodLogicName) : logEntity.methodLogicName != null)
            return false;
        if (method != null ? !method.equals(logEntity.method) : logEntity.method != null) return false;
        if (param != null ? !param.equals(logEntity.param) : logEntity.param != null) return false;
        if (sourcePage != null ? !sourcePage.equals(logEntity.sourcePage) : logEntity.sourcePage != null) return false;
        if (targetPage != null ? !targetPage.equals(logEntity.targetPage) : logEntity.targetPage != null) return false;
        if (stayTime != null ? !stayTime.equals(logEntity.stayTime) : logEntity.stayTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (methodLogicName != null ? methodLogicName.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (param != null ? param.hashCode() : 0);
        result = 31 * result + (sourcePage != null ? sourcePage.hashCode() : 0);
        result = 31 * result + (targetPage != null ? targetPage.hashCode() : 0);
        result = 31 * result + (stayTime != null ? stayTime.hashCode() : 0);
        return result;
    }
}
