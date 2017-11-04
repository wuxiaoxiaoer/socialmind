package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class PermissionEntity {
    private String permissionId;
    private String nameLogic;
    private String permissionPhysics;
    private String description;
    private String resourceId;
    private String operateId;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getNameLogic() {
        return nameLogic;
    }

    public void setNameLogic(String nameLogic) {
        this.nameLogic = nameLogic;
    }

    public String getPermissionPhysics() {
        return permissionPhysics;
    }

    public void setPermissionPhysics(String permissionPhysics) {
        this.permissionPhysics = permissionPhysics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionEntity that = (PermissionEntity) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (nameLogic != null ? !nameLogic.equals(that.nameLogic) : that.nameLogic != null) return false;
        if (permissionPhysics != null ? !permissionPhysics.equals(that.permissionPhysics) : that.permissionPhysics != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (operateId != null ? !operateId.equals(that.operateId) : that.operateId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId != null ? permissionId.hashCode() : 0;
        result = 31 * result + (nameLogic != null ? nameLogic.hashCode() : 0);
        result = 31 * result + (permissionPhysics != null ? permissionPhysics.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        result = 31 * result + (operateId != null ? operateId.hashCode() : 0);
        return result;
    }
}
