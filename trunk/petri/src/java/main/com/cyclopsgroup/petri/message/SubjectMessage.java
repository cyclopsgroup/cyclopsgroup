/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.message;

/**
 * Subject message
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public abstract class SubjectMessage implements UserAware, SubjectAware
{
	private static final String DEFAULT_USER = "default";

	private String action;

	private Object subject;

	private String user;

	/**
	 * Constructor of class SubjectMessage
	 *
	 * @param subjectObject
	 * @param actionCode
	 */
	public SubjectMessage(Object subjectObject, String actionCode)
	{
		this(subjectObject, actionCode, DEFAULT_USER);
	}

	/**
	 * Constructor of class SubjectMessage
	 *
	 * @param subjectObject
	 * @param actionCode
	 * @param userName
	 */
	public SubjectMessage(Object subjectObject, String actionCode,
			String userName)
	{
		subject = subjectObject;
		action = actionCode;
		user = userName;
	}

	/**
	 * Getter method for property action
	 *
	 * @return Returns the action.
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Getter method for property subject
	 *
	 * @return Returns the subject.
	 */
	public Object getSubject()
	{
		return subject;
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