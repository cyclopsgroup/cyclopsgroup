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

import com.cyclopsgroup.petri.definition.TimerTrigger;
import com.evavi.common.syntax.RichTagSupport;
import com.evavi.common.syntax.SyntaxUtils;

/**
 * TODO Add java doc for this class
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class TimerTriggerTag extends RichTagSupport implements TimerTrigger
{
	/**
	 * Override method doTag() in super class
	 *
	 * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
	 */
	public void doTag(XMLOutput output) throws MissingAttributeException,
			JellyTagException
	{
		SyntaxUtils.checkParent(this, TransitionTag.class);
		((TransitionTag) getParent()).getTransition().setTrigger(this);
		invokeBody(output);
	}
}