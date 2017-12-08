package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class CleanLogManagerEntity {
    private String cleanLogId;
    private String userId;
    private String userDefineOldValue;
    private String userDefineNewValue;
    private String cleanTime;
    private String cleanStategyId;
    private String tableName;
    private String culumnName;
    private String responseTime;
    private String resultState;
    private String errorMessage;
    private String IP;
    private String errorCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanLogManagerEntity that = (CleanLogManagerEntity) o;

        if (cleanLogId != null ? !cleanLogId.equals(that.cleanLogId) : that.cleanLogId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userDefineOldValue != null ? !userDefineOldValue.equals(that.userDefineOldValue) : that.userDefineOldValue != null)
            return false;
        if (userDefineNewValue != null ? !userDefineNewValue.equals(that.userDefineNewValue) : that.userDefineNewValue != null)
            return false;
        if (cleanTime != null ? !cleanTime.equals(that.cleanTime) : that.cleanTime != null) return false;
        if (cleanStategyId != null ? !cleanStategyId.equals(that.cleanStategyId) : that.cleanStategyId != null)
            return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (culumnName != null ? !culumnName.equals(that.culumnName) : that.culumnName != null) return false;
        if (responseTime != null ? !responseTime.equals(that.responseTime) : that.responseTime != null) return false;
        if (resultState != null ? !resultState.equals(that.resultState) : that.resultState != null) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (IP != null ? !IP.equals(that.IP) : that.IP != null) return false;
        return errorCode != null ? errorCode.equals(that.errorCode) : that.errorCode == null;
    }

    @Override
    public int hashCode() {
        int result = cleanLogId != null ? cleanLogId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userDefineOldValue != null ? userDefineOldValue.hashCode() : 0);
        result = 31 * result + (userDefineNewValue != null ? userDefineNewValue.hashCode() : 0);
        result = 31 * result + (cleanTime != null ? cleanTime.hashCode() : 0);
        result = 31 * result + (cleanStategyId != null ? cleanStategyId.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (culumnName != null ? culumnName.hashCode() : 0);
        result = 31 * result + (responseTime != null ? responseTime.hashCode() : 0);
        result = 31 * result + (resultState != null ? resultState.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (IP != null ? IP.hashCode() : 0);
        result = 31 * result + (errorCode != null ? errorCode.hashCode() : 0);
        return result;
    }



    public String getCleanLogId() {
        return cleanLogId;
    }

    public void setCleanLogId(String cleanLogId) {
        this.cleanLogId = cleanLogId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDefineNewValue() {
        return userDefineNewValue;
    }

    public void setUserDefineNewValue(String userDefineNewValue) {
        this.userDefineNewValue = userDefineNewValue;
    }

    public String getUserDefineOldValue() {
        return userDefineOldValue;
    }

    public void setUserDefineOldValue(String selfDefiningValue) {
        this.userDefineOldValue = selfDefiningValue;
    }

    public String getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(String cleanTime) {
        this.cleanTime = cleanTime;
    }

    public String getCleanStategyId() {
        return cleanStategyId;
    }

    public void setCleanStategyId(String cleanStategyId) {
        this.cleanStategyId = cleanStategyId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String cleanLocationId) {
        this.tableName = cleanLocationId;
    }
    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getCulumnName() {
        return culumnName;
    }

    public void setCulumnName(String culumnName) {
        this.culumnName = culumnName;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String userIP) {
        this.IP = userIP;
    }
}
