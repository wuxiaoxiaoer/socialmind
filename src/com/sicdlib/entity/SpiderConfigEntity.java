package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class SpiderConfigEntity {
    private String spiderConfigId;
    private String configTime;
    private String spiderRunTime;
    private String logPath;
    private String filePath;
    private Integer spiderFrequency;

    public String getSpiderConfigId() {
        return spiderConfigId;
    }

    public void setSpiderConfigId(String spiderConfigId) {
        this.spiderConfigId = spiderConfigId;
    }

    public String getConfigTime() {
        return configTime;
    }

    public void setConfigTime(String configTime) {
        this.configTime = configTime;
    }

    public String getSpiderRunTime() {
        return spiderRunTime;
    }

    public void setSpiderRunTime(String spiderRunTime) {
        this.spiderRunTime = spiderRunTime;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSpiderFrequency() {
        return spiderFrequency;
    }

    public void setSpiderFrequency(Integer spiderFrequency) {
        this.spiderFrequency = spiderFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpiderConfigEntity that = (SpiderConfigEntity) o;

        if (spiderConfigId != null ? !spiderConfigId.equals(that.spiderConfigId) : that.spiderConfigId != null)
            return false;
        if (configTime != null ? !configTime.equals(that.configTime) : that.configTime != null) return false;
        if (spiderRunTime != null ? !spiderRunTime.equals(that.spiderRunTime) : that.spiderRunTime != null)
            return false;
        if (logPath != null ? !logPath.equals(that.logPath) : that.logPath != null) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        if (spiderFrequency != null ? !spiderFrequency.equals(that.spiderFrequency) : that.spiderFrequency != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spiderConfigId != null ? spiderConfigId.hashCode() : 0;
        result = 31 * result + (configTime != null ? configTime.hashCode() : 0);
        result = 31 * result + (spiderRunTime != null ? spiderRunTime.hashCode() : 0);
        result = 31 * result + (logPath != null ? logPath.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (spiderFrequency != null ? spiderFrequency.hashCode() : 0);
        return result;
    }
}
