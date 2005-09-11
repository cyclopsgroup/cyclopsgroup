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

    /** full constructor */
    public UserGroupBase(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    /** default constructor */
    public UserGroupBase() {
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
