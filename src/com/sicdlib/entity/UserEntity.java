package com.sicdlib.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DeMH on 2017/11/2.
 */
public class UserEntity {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String registrantId;
    private String regTime;
    private String userType;
    private String isAuthenticated;
    private String address;
    /** 表之间的映射：多个用户属于一个角色 */
    private RoleEntity role;
    /** 一个用户包含多个操作*/
    private Set<UserOperaEntity> userOperas = new HashSet<>();
    /** 一个用户包含对个系统评论 */
    private Set<CommentEntity> comments = new HashSet<>();
    /** 一个用户包含多个日志 */
    private Set<LogEntity> logs = new HashSet<>();
    /** 一个用户包含多个对象用户中间表 */
    private Set<ObjectUserEntity> objectUsers = new HashSet<>();

    public Set<ObjectUserEntity> getObjectUsers() {
        return objectUsers;
    }

    public void setObjectUsers(Set<ObjectUserEntity> objectUsers) {
        this.objectUsers = objectUsers;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Set<LogEntity> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogEntity> logs) {
        this.logs = logs;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<UserOperaEntity> getUserOperas() {
        return userOperas;
    }

    public void setUserOperas(Set<UserOperaEntity> userOperas) {
        this.userOperas = userOperas;
    }

    public String getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(String isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getRegistrantId() {
        return registrantId;
    }

    public void setRegistrantId(String registrantId) {
        this.registrantId = registrantId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null){
            return false;
        }
        if (userName != null ? !userName.equals(that.userName) : that.userName != null){
            return false;
        }
        if (password != null ? !password.equals(that.password) : that.password != null){
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null){
            return false;
        }
        if (registrantId != null ? !registrantId.equals(that.registrantId) : that.registrantId != null){
            return false;
        }
        if (userType != null ? !userType.equals(that.userType) : that.userType != null){
            return false;
        }
        if (isAuthenticated != null ? !isAuthenticated.equals(that.isAuthenticated) : that.isAuthenticated != null){
            return false;
        }
        if (address != null ? !address.equals(that.address) : that.address != null){
            return false;
        }
        return role != null ? role.equals(that.role) : that.role == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (registrantId != null ? registrantId.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (isAuthenticated != null ? isAuthenticated.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
