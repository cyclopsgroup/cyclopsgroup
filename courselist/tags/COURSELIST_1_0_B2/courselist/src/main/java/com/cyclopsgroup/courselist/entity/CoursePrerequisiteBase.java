package com.cyclopsgroup.courselist.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class CoursePrerequisiteBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String courseId;

    /** persistent field */
    private String prerequisiteId;

    /** full constructor */
    public CoursePrerequisiteBase(String courseId, String prerequisiteId) {
        this.courseId = courseId;
        this.prerequisiteId = prerequisiteId;
    }

    /** default constructor */
    public CoursePrerequisiteBase() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPrerequisiteId() {
        return this.prerequisiteId;
    }

    public void setPrerequisiteId(String prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
