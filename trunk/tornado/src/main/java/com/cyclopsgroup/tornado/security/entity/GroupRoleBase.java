package com.cyclopsgroup.tornado.security.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class GroupRoleBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String groupId;

    /** persistent field */
    private String roleId;

    /** nullable persistent field */
    private com.cyclopsgroup.tornado.security.entity.Role role;

    /** full constructor */
    public GroupRoleBase(String groupId, String roleId, com.cyclopsgroup.tornado.security.entity.Role role) {
        this.groupId = groupId;
        this.roleId = roleId;
        this.role = role;
    }

    /** default constructor */
    public GroupRoleBase() {
    }

    /** minimal constructor */
    public GroupRoleBase(String groupId, String roleId) {
        this.groupId = groupId;
        this.roleId = roleId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public com.cyclopsgroup.tornado.security.entity.Role getRole() {
        return this.role;
    }

    public void setRole(com.cyclopsgroup.tornado.security.entity.Role role) {
        this.role = role;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
