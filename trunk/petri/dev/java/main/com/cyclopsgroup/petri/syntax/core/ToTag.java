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
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;

import com.evavi.common.syntax.SyntaxUtils;

/**
 * to tag
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class ToTag extends TagSupport
{

	private String state;

	/**
	 * Override method doTag() in super class
	 *
	 * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
	 */
	public void doTag(XMLOutput output) throws MissingAttributeException,
			JellyTagException
	{
		SyntaxUtils.checkAttribute("state", getState());
		FlowDefinitionTag fdt = (FlowDefinitionTag) findAncestorWithClass(FlowDefinitionTag.class);
		DefaultState toState = (DefaultState) fdt.getFlowDefinition().getState(
				getState());
		if (toState == null)
		{
			throw new JellyTagException("State " + state + " not found");
		}
		SyntaxUtils.checkParent(this, TransitionTag.class);
		DefaultTransition transition = ((TransitionTag) getParent())
				.getTransition();
		transition.addToState(toState);
		toState.addInboundTransition(transition);
		invokeBody(output);
	}

	/**
	 * Getter method for property state
	 *
	 * @return Returns the state.
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Setter method for property state
	 *
	 * @param stateId The state to set.
	 */
	public void setState(String stateId)
	{
		state = stateId;
	}

}