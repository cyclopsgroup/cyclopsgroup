/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.util.HashSet;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.MissingAttributeException;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.petri.definition.State;
import com.cyclopsgroup.petri.definition.Transition;
import com.evavi.common.syntax.RichTagSupport;

/**
 * Transition tag
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class TransitionTag extends RichTagSupport
{
	private static long innerTransitionId = 0;

	private String id;

	private DefaultTransition transition;

	/**
	 * Method checkFirst() in class TransitionTag
	 */
	private void checkFirst() throws JellyTagException
	{
		State[] froms = getTransition().getFromStates();
		FlowDefinitionTag fdt = (FlowDefinitionTag) findAncestorWithClass(FlowDefinitionTag.class);
		State exit = fdt.getFlowDefinition().getEntrance();
		for (int i = 0; i < froms.length; i++)
		{
			if (froms[i] == exit)
			{
				getTransition().setInitial(true);
				break;
			}
		}
		if (getTransition().isInitial() && froms.length != 1)
		{
			throw new JellyTagException("Last transition " + getId()
					+ " must have only one to state which is the exit state "
					+ exit.getId());
		}
	}

	/**
	 * Check the last transition senario
	 */
	private void checkLast() throws JellyTagException
	{
		State[] tos = getTransition().getToStates();
		FlowDefinitionTag fdt = (FlowDefinitionTag) findAncestorWithClass(FlowDefinitionTag.class);
		State exit = fdt.getFlowDefinition().getExit();
		for (int i = 0; i < tos.length; i++)
		{
			if (tos[i] == exit)
			{
				getTransition().setTerminal(true);
				break;
			}
		}
		if (getTransition().isTerminal() && tos.length != 1)
		{
			throw new JellyTagException("Last transition " + getId()
					+ " must have only one from state which is the exit state "
					+ exit.getId());
		}
	}

	private void checkUnmoved() throws JellyTagException
	{
		State[] froms = getTransition().getFromStates();
		HashSet fromStateIds = new HashSet();
		for (int i = 0; i < froms.length; i++)
		{
			State from = froms[i];
			fromStateIds.add(from.getId());
		}
		State[] tos = getTransition().getToStates();
		for (int i = 0; i < tos.length; i++)
		{
			State to = tos[i];
			if (fromStateIds.contains(to.getId()))
			{
				throw new JellyTagException(
						"Same state <"
								+ to.getId()
								+ "> can not appear in both from list and to list of transition <"
								+ getId() + ">");
			}
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
		setId(getAttributeParser().getString("id"));
		if (StringUtils.isEmpty(getId()))
		{
			innerTransitionId++;
			setId("transition_" + innerTransitionId);
		}

		FlowDefinitionTag fdt = (FlowDefinitionTag) findAncestorWithClass(FlowDefinitionTag.class);
		transition = new DefaultTransition(getId(), fdt.getFlowDefinition());

		String inputPolicy = getAttributeParser().getString("input-policy",
				"or");
		Transition.InputStatesPolicy policy = Transition.InputStatesPolicy
				.valueOf(inputPolicy);
		if (policy == null)
		{
			throw new JellyTagException(
					"input-policy attribute of transition must be or|and");
		}
		transition.setInputStatesPolicy(policy);
		invokeBody(output);

		checkFirst();
		checkLast();
		checkUnmoved();
		fdt.getFlowDefinition().addTransition(transition);
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
	 * Getter method for property transition
	 *
	 * @return Returns the transition.
	 */
	public DefaultTransition getTransition()
	{
		return transition;
	}

	/**
	 * Setter method for property id
	 *
	 * @param transitionId The id to set.
	 */
	public void setId(String transitionId)
	{
		id = transitionId;
	}
}