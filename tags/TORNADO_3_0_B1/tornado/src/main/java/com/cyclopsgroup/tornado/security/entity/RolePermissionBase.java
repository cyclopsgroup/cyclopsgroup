package com.cyclopsgroup.tornado.security.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class RolePermissionBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String roleId;

    /** persistent field */
    private String permissionType;

    /** persistent field */
    private String permission;

    /** full constructor */
    public RolePermissionBase(String roleId, String permissionType, String permission) {
        this.roleId = roleId;
        this.permissionType = permissionType;
        this.permission = permission;
    }

    /** default constructor */
    public RolePermissionBase() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionType() {
        return this.permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
