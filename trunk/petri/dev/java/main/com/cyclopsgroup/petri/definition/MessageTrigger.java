/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * Message transition trigger
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface MessageTrigger extends Trigger
{
	/**
	 * Get name of the message in context
	 *
	 * @return Name of the message
	 */
	String getMessageName();

	/**
	 * Get type of the message
	 *
	 * @return MessageType instance
	 */
	MessageType getMessageType();
}