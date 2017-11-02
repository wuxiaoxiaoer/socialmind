package com.sicdlib.entity;

import javax.persistence.*;

/**
 * Created by DeMH on 2017/11/2.
 */
@Entity
@Table(name = "notice", schema = "socialmind", catalog = "")
public class NoticeEntity {
    private String noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticTime;
    private String adminId;

    @Id
    @Column(name = "noticeID")
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "noticeTitle")
    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    @Basic
    @Column(name = "noticeContent")
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Basic
    @Column(name = "noticTime")
    public String getNoticTime() {
        return noticTime;
    }

    public void setNoticTime(String noticTime) {
        this.noticTime = noticTime;
    }

    @Basic
    @Column(name = "adminID")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoticeEntity that = (NoticeEntity) o;

        if (noticeId != null ? !noticeId.equals(that.noticeId) : that.noticeId != null) return false;
        if (noticeTitle != null ? !noticeTitle.equals(that.noticeTitle) : that.noticeTitle != null) return false;
        if (noticeContent != null ? !noticeContent.equals(that.noticeContent) : that.noticeContent != null)
            return false;
        if (noticTime != null ? !noticTime.equals(that.noticTime) : that.noticTime != null) return false;
        if (adminId != null ? !adminId.equals(that.adminId) : that.adminId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noticeId != null ? noticeId.hashCode() : 0;
        result = 31 * result + (noticeTitle != null ? noticeTitle.hashCode() : 0);
        result = 31 * result + (noticeContent != null ? noticeContent.hashCode() : 0);
        result = 31 * result + (noticTime != null ? noticTime.hashCode() : 0);
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        return result;
    }
}
