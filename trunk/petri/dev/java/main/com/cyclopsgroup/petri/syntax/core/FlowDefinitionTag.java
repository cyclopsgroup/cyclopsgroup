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

import com.cyclopsgroup.petri.definition.AutomaticTrigger;
import com.cyclopsgroup.petri.definition.MessageTrigger;
import com.cyclopsgroup.petri.definition.Transition;
import com.evavi.common.syntax.RichTagSupport;
import com.evavi.common.syntax.SyntaxUtils;

/**
 * flow-definition tag
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class FlowDefinitionTag extends RichTagSupport
{
	private DefaultFlowDefinition flowDefinition;

	private void checkIntegrity() throws JellyTagException
	{
		if (getFlowDefinition().getEntrance().getInboundTransitions().length > 0)
		{
			throw new JellyTagException(
					"There should not be transitions to entrance");
		}
		if (getFlowDefinition().getExit().getOutboundTransitions().length > 0)
		{
			throw new JellyTagException(
					"There should not be transitions from exit");
		}
		Transition[] initialTransitions = getFlowDefinition().getEntrance()
				.getOutboundTransitions();
		if (initialTransitions.length == 0)
		{
			throw new JellyTagException(
					"Flow should at least have one initial transition");
		}
		int autoTriggerCount = 0;
		for (int i = 0; i < initialTransitions.length; i++)
		{
			Transition transition = initialTransitions[i];
			if (transition.getTrigger() instanceof AutomaticTrigger)
			{
				autoTriggerCount++;
			}
			else if (!(transition.getTrigger() instanceof MessageTrigger))
			{
				throw new JellyTagException(
						"Initial transition must be auto triggered or message triggered");
			}
		}
		if (autoTriggerCount > 1)
		{
			throw new JellyTagException(
					"There should be zero or one auto triggered initial transition");
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
		String name = getAttributeParser().getString("name");
		SyntaxUtils.checkAttribute("name", name);
		String flowId = name;
		String packageName = getAttributeParser().getString("package");
		if (StringUtils.isNotEmpty(packageName))
		{
			flowId = packageName + "." + name;
		}
		flowDefinition = new DefaultFlowDefinition(flowId);
		String entranceName = getAttributeParser().getString("entrance");
		if (StringUtils.isNotEmpty(entranceName))
		{
			flowDefinition.setEntrance(entranceName);
		}
		String exitName = getAttributeParser().getString("exit");
		if (StringUtils.isNotEmpty(exitName))
		{
			flowDefinition.setExit(exitName);
		}
		invokeBody(output);

		checkIntegrity();
		getContext().setVariable("result", flowDefinition);
	}

	/**
	 * Getter method for property flowDefinition
	 *
	 * @return Returns the flowDefinition.
	 */
	public DefaultFlowDefinition getFlowDefinition()
	{
		return flowDefinition;
	}
}