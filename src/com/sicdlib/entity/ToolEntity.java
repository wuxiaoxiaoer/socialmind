package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class ToolEntity {
    private String toolId;
    private String toolName;
    private String version;
    private String versionDescribe;
    private String jarUrl;
    private String toolkitId;
    private String state;
    private String uploadTime;
    private String userId;

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionDescribe() {
        return versionDescribe;
    }

    public void setVersionDescribe(String versionDescribe) {
        this.versionDescribe = versionDescribe;
    }

    public String getJarUrl() {
        return jarUrl;
    }

    public void setJarUrl(String jarUrl) {
        this.jarUrl = jarUrl;
    }

    public String getToolkitId() {
        return toolkitId;
    }

    public void setToolkitId(String toolkitId) {
        this.toolkitId = toolkitId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToolEntity that = (ToolEntity) o;

        if (toolId != null ? !toolId.equals(that.toolId) : that.toolId != null) return false;
        if (toolName != null ? !toolName.equals(that.toolName) : that.toolName != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (versionDescribe != null ? !versionDescribe.equals(that.versionDescribe) : that.versionDescribe != null)
            return false;
        if (jarUrl != null ? !jarUrl.equals(that.jarUrl) : that.jarUrl != null) return false;
        if (toolkitId != null ? !toolkitId.equals(that.toolkitId) : that.toolkitId != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = toolId != null ? toolId.hashCode() : 0;
        result = 31 * result + (toolName != null ? toolName.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (versionDescribe != null ? versionDescribe.hashCode() : 0);
        result = 31 * result + (jarUrl != null ? jarUrl.hashCode() : 0);
        result = 31 * result + (toolkitId != null ? toolkitId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
