package com.cyclopsgroup.tornado.security;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class RoleBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private boolean isDisabled;

    /** nullable persistent field */
    private boolean isSystem;

    /** persistent field */
    private Set dependencies;

    /** full constructor */
    public RoleBase(String name, String description, boolean isDisabled, boolean isSystem, Set dependencies) {
        this.name = name;
        this.description = description;
        this.isDisabled = isDisabled;
        this.isSystem = isSystem;
        this.dependencies = dependencies;
    }

    /** default constructor */
    public RoleBase() {
    }

    /** minimal constructor */
    public RoleBase(String name, Set dependencies) {
        this.name = name;
        this.dependencies = dependencies;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(Set dependencies) {
        this.dependencies = dependencies;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
