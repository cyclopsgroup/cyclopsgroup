/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.message;

/**
 * Message manager
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface MessageManager
{
	/** Role name in container */
	String ROLE = MessageManager.class.getName();

	/**
	 * This method is for workflow engine
	 * Workflow engine will register itself as a message listener to message manager
	 *
	 * @param listener Message listener
	 */
	void addMessageListener(MessageListener listener);

	/**
	 * Clear exsiting listeners
	 */
	void clearListeners();

	/**
	 * Get all listeners
	 *
	 * @return Array of message listeners
	 */
	MessageListener[] getListeners();

	/**
	 * Receive a message
	 *
	 * @param message Message object
	 */
	void receiveMessage(Object message);

	/**
	 * Method removeMessageListener() in class MessageManager
	 *
	 * @param listener
	 */
	void removeMessageListener(MessageListener listener);
}