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

import com.cyclopsgroup.petri.definition.MessageType;
import com.evavi.common.syntax.RichTagSupport;
import com.evavi.common.syntax.SyntaxUtils;

/**
 * Simple message type tag
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class SimpleMessageTypeTag extends RichTagSupport implements MessageType
{
	private String className;

	/**
	 * Override method accept() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.MessageType#accept(java.lang.Object)
	 */
	public boolean accept(Object message)
	{
		try
		{
			Class clazz = Class.forName(className);
			return clazz.isAssignableFrom(message.getClass());
		}
		catch (Exception ignored)
		{
			return false;
		}
	}

	/**
	 * Override method doTag() in super class
	 *
	 * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
	 */
	public void doTag(XMLOutput output) throws MissingAttributeException,
			JellyTagException
	{
		className = getAttributeParser().getString("class");
		SyntaxUtils.checkAttribute("class", className);
		SyntaxUtils.checkParent(this, MessageTriggerTag.class);
		((MessageTriggerTag) getParent()).setMessageType(this);
		invokeBody(output);
	}
}