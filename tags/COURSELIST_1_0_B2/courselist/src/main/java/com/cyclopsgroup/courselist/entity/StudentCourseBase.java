package com.cyclopsgroup.courselist.entity;

import com.cyclopsgroup.tornado.security.entity.User;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class StudentCourseBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String courseId;

    /** persistent field */
    private String studentId;

    /** nullable persistent field */
    private float score;

    /** persistent field */
    private String relationType;

    /** nullable persistent field */
    private com.cyclopsgroup.courselist.entity.Course course;

    /** nullable persistent field */
    private User student;

    /** full constructor */
    public StudentCourseBase(String courseId, String studentId, float score, String relationType, com.cyclopsgroup.courselist.entity.Course course, User student) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
        this.relationType = relationType;
        this.course = course;
        this.student = student;
    }

    /** default constructor */
    public StudentCourseBase() {
    }

    /** minimal constructor */
    public StudentCourseBase(String courseId, String studentId, String relationType) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.relationType = relationType;
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

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public float getScore() {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRelationType() {
        return this.relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public com.cyclopsgroup.courselist.entity.Course getCourse() {
        return this.course;
    }

    public void setCourse(com.cyclopsgroup.courselist.entity.Course course) {
        this.course = course;
    }

    public User getStudent() {
        return this.student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
