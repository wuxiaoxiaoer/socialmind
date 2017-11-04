package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class TbUserEntity {
    private int uId;
    private String uName;
    private String uPwd;
    private String uTelphone;
    private String uMail;
    private Boolean uSex;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuTelphone() {
        return uTelphone;
    }

    public void setuTelphone(String uTelphone) {
        this.uTelphone = uTelphone;
    }

    public String getuMail() {
        return uMail;
    }

    public void setuMail(String uMail) {
        this.uMail = uMail;
    }

    public Boolean getuSex() {
        return uSex;
    }

    public void setuSex(Boolean uSex) {
        this.uSex = uSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUserEntity that = (TbUserEntity) o;

        if (uId != that.uId) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uPwd != null ? !uPwd.equals(that.uPwd) : that.uPwd != null) return false;
        if (uTelphone != null ? !uTelphone.equals(that.uTelphone) : that.uTelphone != null) return false;
        if (uMail != null ? !uMail.equals(that.uMail) : that.uMail != null) return false;
        if (uSex != null ? !uSex.equals(that.uSex) : that.uSex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uPwd != null ? uPwd.hashCode() : 0);
        result = 31 * result + (uTelphone != null ? uTelphone.hashCode() : 0);
        result = 31 * result + (uMail != null ? uMail.hashCode() : 0);
        result = 31 * result + (uSex != null ? uSex.hashCode() : 0);
        return result;
    }
}
