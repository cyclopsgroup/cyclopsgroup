package com.cyclopsgroup.courselist.entity;

import com.cyclopsgroup.tornado.security.entity.User;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class TeacherBase implements Serializable {

    /** identifier field */
    private String id;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private User user;

    /** full constructor */
    public TeacherBase(String userId, User user) {
        this.userId = userId;
        this.user = user;
    }

    /** default constructor */
    public TeacherBase() {
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
