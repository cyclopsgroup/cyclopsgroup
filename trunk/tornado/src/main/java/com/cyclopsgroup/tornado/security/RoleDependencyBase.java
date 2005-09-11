package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class RoleDependencyBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String roleId;

    /** persistent field */
    private String dependencyId;

    /** nullable persistent field */
    private com.cyclopsgroup.tornado.security.Role dependency;

    /** full constructor */
    public RoleDependencyBase(String roleId, String dependencyId, com.cyclopsgroup.tornado.security.Role dependency) {
        this.roleId = roleId;
        this.dependencyId = dependencyId;
        this.dependency = dependency;
    }

    /** default constructor */
    public RoleDependencyBase() {
    }

    /** minimal constructor */
    public RoleDependencyBase(String roleId, String dependencyId) {
        this.roleId = roleId;
        this.dependencyId = dependencyId;
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

    public String getDependencyId() {
        return this.dependencyId;
    }

    public void setDependencyId(String dependencyId) {
        this.dependencyId = dependencyId;
    }

    public com.cyclopsgroup.tornado.security.Role getDependency() {
        return this.dependency;
    }

    public void setDependency(com.cyclopsgroup.tornado.security.Role dependency) {
        this.dependency = dependency;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
