package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class UserGroupBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String userId;

    /** persistent field */
    private String groupId;

    /** nullable persistent field */
    private com.cyclopsgroup.tornado.security.User user;

    /** nullable persistent field */
    private com.cyclopsgroup.tornado.security.Group group;

    /** full constructor */
    public UserGroupBase(String userId, String groupId, com.cyclopsgroup.tornado.security.User user, com.cyclopsgroup.tornado.security.Group group) {
        this.userId = userId;
        this.groupId = groupId;
        this.user = user;
        this.group = group;
    }

    /** default constructor */
    public UserGroupBase() {
    }

    /** minimal constructor */
    public UserGroupBase(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
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

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public com.cyclopsgroup.tornado.security.User getUser() {
        return this.user;
    }

    public void setUser(com.cyclopsgroup.tornado.security.User user) {
        this.user = user;
    }

    public com.cyclopsgroup.tornado.security.Group getGroup() {
        return this.group;
    }

    public void setGroup(com.cyclopsgroup.tornado.security.Group group) {
        this.group = group;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
