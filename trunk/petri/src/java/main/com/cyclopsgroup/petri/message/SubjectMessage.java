/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.message;

import java.io.Serializable;

/**
 * Subject message
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public abstract class SubjectMessage implements UserAware, SubjectAware,
        ActionAware, Serializable
{
    private static final String DEFAULT_USER = "default";

    private String action;

    private String subjectId;

    private String user;

    /**
     * Constructor of class SubjectMessage
     *
     * @param subjectId
     * @param actionCode
     */
    public SubjectMessage(String subjectId, String actionCode)
    {
        this(subjectId, actionCode, DEFAULT_USER);
    }

    /**
     * Constructor of class SubjectMessage
     *
     * @param subjectId
     * @param actionCode
     * @param userName
     */
    public SubjectMessage(String subjectId, String actionCode, String userName)
    {
        this.subjectId = subjectId;
        this.action = actionCode;
        this.user = userName;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.petri.message.ActionAware#getAction()
     */
    public String getAction()
    {
        return action;
    }

    /**
     * Override method getSubject() in super class
     *
     * @see com.cyclopsgroup.petri.message.SubjectAware#getSubject()
     */
    public abstract Object getSubject();

    /**
     * Override method getSubjectId() in super class
     *
     * @see com.cyclopsgroup.petri.message.SubjectAware#getSubjectId()
     */
    public String getSubjectId()
    {
        return subjectId;
    }

    /**
     * Getter method for property user
     *
     * @return Returns the user.
     */
    public String getUser()
    {
        return user;
    }
}