package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class NoticeRoleEntity {
    private String noticeRoleId;
    private String noticeId;
    private String roleId;

    public String getNoticeRoleId() {
        return noticeRoleId;
    }

    public void setNoticeRoleId(String noticeRoleId) {
        this.noticeRoleId = noticeRoleId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoticeRoleEntity that = (NoticeRoleEntity) o;

        if (noticeRoleId != null ? !noticeRoleId.equals(that.noticeRoleId) : that.noticeRoleId != null) return false;
        if (noticeId != null ? !noticeId.equals(that.noticeId) : that.noticeId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noticeRoleId != null ? noticeRoleId.hashCode() : 0;
        result = 31 * result + (noticeId != null ? noticeId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
