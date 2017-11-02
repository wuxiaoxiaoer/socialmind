package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "bulletin_style_id", schema = "socialmind", catalog = "")
public class BulletinStyleIdEntity {
    private String bulletinStyleId;
    private String name;
    private String introduction;
    private String coverGraph;
    private String url;
    private String uploadTime;
    private Integer downloadNumber;
    private Integer likeNumber;
    private Integer collectNumber;

    @Id
    @Column(name = "bulletinStyleID")
    public String getBulletinStyleId() {
        return bulletinStyleId;
    }

    public void setBulletinStyleId(String bulletinStyleId) {
        this.bulletinStyleId = bulletinStyleId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "coverGraph")
    public String getCoverGraph() {
        return coverGraph;
    }

    public void setCoverGraph(String coverGraph) {
        this.coverGraph = coverGraph;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "uploadTime")
    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "downloadNumber")
    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    @Basic
    @Column(name = "likeNumber")
    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Basic
    @Column(name = "collectNumber")
    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulletinStyleIdEntity that = (BulletinStyleIdEntity) o;

        if (bulletinStyleId != null ? !bulletinStyleId.equals(that.bulletinStyleId) : that.bulletinStyleId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (coverGraph != null ? !coverGraph.equals(that.coverGraph) : that.coverGraph != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (downloadNumber != null ? !downloadNumber.equals(that.downloadNumber) : that.downloadNumber != null)
            return false;
        if (likeNumber != null ? !likeNumber.equals(that.likeNumber) : that.likeNumber != null) return false;
        if (collectNumber != null ? !collectNumber.equals(that.collectNumber) : that.collectNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bulletinStyleId != null ? bulletinStyleId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (coverGraph != null ? coverGraph.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (downloadNumber != null ? downloadNumber.hashCode() : 0);
        result = 31 * result + (likeNumber != null ? likeNumber.hashCode() : 0);
        result = 31 * result + (collectNumber != null ? collectNumber.hashCode() : 0);
        return result;
    }
}
