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
 * TODO Add java doc for this class
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class StateTag extends TagSupport
{
	private String id;

	private DefaultState state;

	/**
	 * Override method doTag() in super class
	 *
	 * @see org.apache.commons.jelly.Tag#doTag(org.apache.commons.jelly.XMLOutput)
	 */
	public void doTag(XMLOutput output) throws MissingAttributeException,
			JellyTagException
	{
		SyntaxUtils.checkAttribute("id", getId());
		FlowDefinitionTag fdt = (FlowDefinitionTag) findAncestorWithClass(FlowDefinitionTag.class);
		state = new DefaultState(getId(), fdt.getFlowDefinition());
		fdt.getFlowDefinition().addState(state);
		invokeBody(output);
	}

	/**
	 * Getter method for property id
	 *
	 * @return Returns the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Setter method for property id
	 *
	 * @param stateId The id to set.
	 */
	public void setId(String stateId)
	{
		id = stateId;
	}
}