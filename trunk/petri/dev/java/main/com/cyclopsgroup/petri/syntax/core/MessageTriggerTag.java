/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.petri.definition.MessageTrigger;
import com.cyclopsgroup.petri.definition.MessageType;
import com.evavi.common.syntax.RichTagSupport;
import com.evavi.common.syntax.SyntaxUtils;

/**
 * message-trigger tag
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class MessageTriggerTag extends RichTagSupport implements MessageTrigger
{
	private static final MessageType DEFAULT_MESSAGE_TYPE = new MessageType()
	{
		/**
		 * Override method accept() in super class
		 *
		 * @see com.cyclopsgroup.petri.definition.MessageType#accept(java.lang.Object)
		 */
		public boolean accept(Object message)
		{
			return true;
		}
	};

	private String messageName;

	private MessageType messageType = DEFAULT_MESSAGE_TYPE;

	/**
	 * Override method doTag() in super class
	 *
	 * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
	 */
	public void doTag(XMLOutput output) throws MissingAttributeException,
			JellyTagException
	{
		messageName = getAttributeParser().getString("message-name", "message");
		SyntaxUtils.checkParent(this, TransitionTag.class);
		((TransitionTag) getParent()).getTransition().setTrigger(this);
		invokeBody(output);
	}

	/**
	 * Override method getMessageName() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.MessageTrigger#getMessageName()
	 */
	public String getMessageName()
	{
		return messageName;
	}

	/**
	 * Override method getMessageType() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.MessageTrigger#getMessageType()
	 */
	public MessageType getMessageType()
	{
		return messageType;
	}

	/**
	 * Setter method for property messageType
	 *
	 * @param type The messageType to set.
	 */
	public void setMessageType(MessageType type)
	{
		messageType = type;
	}
}