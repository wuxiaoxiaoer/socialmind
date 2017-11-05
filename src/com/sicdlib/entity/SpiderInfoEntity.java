package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class SpiderInfoEntity {
    private String spiderId;
    private String spiderName;
    private String addTime;
    private String spiderSourcePath;
    private String fileName;
    private String websiteId;
    private String fileId;
    private String spiderState;
    private String spiderConfigId;

    public String getSpiderId() {
        return spiderId;
    }

    public void setSpiderId(String spiderId) {
        this.spiderId = spiderId;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getSpiderSourcePath() {
        return spiderSourcePath;
    }

    public void setSpiderSourcePath(String spiderSourcePath) {
        this.spiderSourcePath = spiderSourcePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getSpiderState() {
        return spiderState;
    }

    public void setSpiderState(String spiderState) {
        this.spiderState = spiderState;
    }

    public String getSpiderConfigId() {
        return spiderConfigId;
    }

    public void setSpiderConfigId(String spiderConfigId) {
        this.spiderConfigId = spiderConfigId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpiderInfoEntity that = (SpiderInfoEntity) o;

        if (spiderId != null ? !spiderId.equals(that.spiderId) : that.spiderId != null) return false;
        if (spiderName != null ? !spiderName.equals(that.spiderName) : that.spiderName != null) return false;
        if (addTime != null ? !addTime.equals(that.addTime) : that.addTime != null) return false;
        if (spiderSourcePath != null ? !spiderSourcePath.equals(that.spiderSourcePath) : that.spiderSourcePath != null)
            return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (websiteId != null ? !websiteId.equals(that.websiteId) : that.websiteId != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        if (spiderState != null ? !spiderState.equals(that.spiderState) : that.spiderState != null) return false;
        if (spiderConfigId != null ? !spiderConfigId.equals(that.spiderConfigId) : that.spiderConfigId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spiderId != null ? spiderId.hashCode() : 0;
        result = 31 * result + (spiderName != null ? spiderName.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + (spiderSourcePath != null ? spiderSourcePath.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (websiteId != null ? websiteId.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (spiderState != null ? spiderState.hashCode() : 0);
        result = 31 * result + (spiderConfigId != null ? spiderConfigId.hashCode() : 0);
        return result;
    }
}
