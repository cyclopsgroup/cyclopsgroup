/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.persistence;

/**
 * Schedule persistent model
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Schedule
{
	/** Empty schedule array */
	Schedule[] EMPTY_ARRAY = new Schedule[0];

	/**
	 * Get case id
	 *
	 * @return Case id
	 */
	String getCaseId();

	/**
	 * Get cron expression
	 *
	 * @return Cron expression
	 */
	String getCronExpression();

	/**
	 * Get occur time
	 *
	 * @return Occur time as long
	 */
	long getOccurTime();

	/**
	 * Method getTransitionId() in class Schedule
	 *
	 * @return Id of transition
	 */
	String getTransitionId();

	/**
	 * Whether it is a scheduled trigger or timer trigger
	 *
	 * @return Is it recurrent, meaning scheduled trigger
	 */
	boolean isRecurrent();
}