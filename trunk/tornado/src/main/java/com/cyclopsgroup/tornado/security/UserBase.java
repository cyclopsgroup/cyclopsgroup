package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class UserBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String name;

    /** persistent field */
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
    private String country;

    /** nullable persistent field */
    private String language;

    /** nullable persistent field */
    private Date birthday;

    /** nullable persistent field */
    private boolean isDisabled;

    /** nullable persistent field */
    private boolean isSystem;

    /** persistent field */
    private Set groups;

    /** full constructor */
    public UserBase(String name, String password, String firstName, String middleName, String lastName, String gender, String email, String country, String language, Date birthday, boolean isDisabled, boolean isSystem, Set groups) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
        this.isDisabled = isDisabled;
        this.isSystem = isSystem;
        this.groups = groups;
    }

    /** default constructor */
    public UserBase() {
    }

    /** minimal constructor */
    public UserBase(String name, String password, String email, Set groups) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.groups = groups;
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

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Set getGroups() {
        return this.groups;
    }

    public void setGroups(Set groups) {
        this.groups = groups;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
