package com.cyclopsgroup.tornado.portal;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
abstract public class UserPreferenceBase implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String userId;

    /** persistent field */
    private String themeName;

    /** nullable persistent field */
    private String defaultLayout;

    /** nullable persistent field */
    private String iconset;

    /** nullable persistent field */
    private String stylesheet;

    /** nullable persistent field */
    private String windowStyle;

    /** full constructor */
    public UserPreferenceBase(String userId, String themeName, String defaultLayout, String iconset, String stylesheet, String windowStyle) {
        this.userId = userId;
        this.themeName = themeName;
        this.defaultLayout = defaultLayout;
        this.iconset = iconset;
        this.stylesheet = stylesheet;
        this.windowStyle = windowStyle;
    }

    /** default constructor */
    public UserPreferenceBase() {
    }

    /** minimal constructor */
    public UserPreferenceBase(String userId, String themeName) {
        this.userId = userId;
        this.themeName = themeName;
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

    public String getThemeName() {
        return this.themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getDefaultLayout() {
        return this.defaultLayout;
    }

    public void setDefaultLayout(String defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    public String getIconset() {
        return this.iconset;
    }

    public void setIconset(String iconset) {
        this.iconset = iconset;
    }

    public String getStylesheet() {
        return this.stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public String getWindowStyle() {
        return this.windowStyle;
    }

    public void setWindowStyle(String windowStyle) {
        this.windowStyle = windowStyle;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
