package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class UserBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String password;

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String middleName;

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private String gender;

    /** persistent field */
    private String email;

    /** nullable persistent field */
    private boolean isDisabled;

    /** nullable persistent field */
    private boolean isSystem;

    /** full constructor */
    public UserBase(String name, String password, String firstName, String middleName, String lastName, String gender, String email, boolean isDisabled, boolean isSystem) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.isDisabled = isDisabled;
        this.isSystem = isSystem;
    }

    /** default constructor */
    public UserBase() {
    }

    /** minimal constructor */
    public UserBase(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /** 
     * User name of this user
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsDisabled() {
        return this.isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public boolean getIsSystem() {
        return this.isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
