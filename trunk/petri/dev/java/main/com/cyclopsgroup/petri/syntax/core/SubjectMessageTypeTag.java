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
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.gearset.xml.RichTagSupport;
import com.cyclopsgroup.gearset.xml.SyntaxUtils;
import com.cyclopsgroup.petri.definition.MessageType;
import com.cyclopsgroup.petri.message.SubjectMessage;

/**
 * subject-message-type
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class SubjectMessageTypeTag extends RichTagSupport implements
		MessageType
{
	private String action;

	private String subjectType;

	private String user;

	/**
	 * Override method accept() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.MessageType#accept(java.lang.Object)
	 */
	public boolean accept(Object message)
	{
		if (!(message instanceof SubjectMessage))
		{
			return false;
		}
		SubjectMessage sm = (SubjectMessage) message;
		try
		{
			Class type = Thread.currentThread().getContextClassLoader()
					.loadClass(subjectType);
			if (!type.isAssignableFrom(sm.getSubject().getClass()))
			{
				return false;
			}
			else if (!StringUtils.equals(sm.getAction(), action))
			{
				return false;
			}
			else if (StringUtils.isNotEmpty(user)
					&& !StringUtils.equals(user, sm.getUser()))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch (Exception e)
		{
			getLogger().warn("Message type error", e);
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
		action = getAttributeParser().getString("action");
		SyntaxUtils.checkAttribute("action", action);
		subjectType = getAttributeParser().getString("subject-type");
		SyntaxUtils.checkAttribute("subject-type", subjectType);
		user = getAttributeParser().getString("user");
		SyntaxUtils.checkParent(this, MessageTriggerTag.class);
		((MessageTriggerTag) getParent()).setMessageType(this);
		invokeBody(output);
	}
}