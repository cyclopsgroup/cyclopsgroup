/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.util.Hashtable;

import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.definition.State;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Default implementation of default state
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultState implements State
{
	private FlowDefinition flowDefinition;

	private String id;

	private Hashtable inboundTransitions = new Hashtable();

	private Hashtable outboundTransitions = new Hashtable();

	/**
	 * Constructor of class DefaultState
	 *
	 * @param stateId
	 * @param definition
	 */
	public DefaultState(String stateId, FlowDefinition definition)
	{
		id = stateId;
		flowDefinition = definition;
	}

	/**
	 * Method addInboundTransition() in class DefaultState
	 *
	 * @param transition
	 */
	public void addInboundTransition(Transition transition)
	{
		inboundTransitions.put(transition.getId(), transition);
	}

	/**
	 * Method addOutboundTransition() in class DefaultState
	 *
	 * @param transition
	 */
	public void addOutboundTransition(Transition transition)
	{
		outboundTransitions.put(transition.getId(), transition);
	}

	/**
	 * Override method getFlowDefinition() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.State#getFlowDefinition()
	 */
	public FlowDefinition getFlowDefinition()
	{
		return flowDefinition;
	}

	/**
	 * Override method getId() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.State#getId()
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Override method getInboundTransitions() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.State#getInboundTransitions()
	 */
	public Transition[] getInboundTransitions()
	{
		return (Transition[]) inboundTransitions.values().toArray(
				Transition.EMPTY_ARRAY);
	}

	/**
	 * Override method getOutboundTransitions() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.State#getOutboundTransitions()
	 */
	public Transition[] getOutboundTransitions()
	{
		return (Transition[]) outboundTransitions.values().toArray(
				Transition.EMPTY_ARRAY);
	}
}