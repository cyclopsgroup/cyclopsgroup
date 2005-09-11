package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class UserRoleBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String userId;

    /** persistent field */
    private String roleId;

    /** nullable persistent field */
    private com.cyclopsgroup.tornado.security.Role role;

    /** full constructor */
    public UserRoleBase(String userId, String roleId, com.cyclopsgroup.tornado.security.Role role) {
        this.userId = userId;
        this.roleId = roleId;
        this.role = role;
    }

    /** default constructor */
    public UserRoleBase() {
    }

    /** minimal constructor */
    public UserRoleBase(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public com.cyclopsgroup.tornado.security.Role getRole() {
        return this.role;
    }

    public void setRole(com.cyclopsgroup.tornado.security.Role role) {
        this.role = role;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
