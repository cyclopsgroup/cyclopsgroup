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
 * Default implementation of flow definition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultFlowDefinition implements FlowDefinition
{
	private static final String DEFAULT_ENTRANCE_NAME = "in";

	private static final String DEFAULT_EXIT_NAME = "out";

	private String entranceName = DEFAULT_ENTRANCE_NAME;

	private String exitName = DEFAULT_EXIT_NAME;

	private String id;

	private Hashtable states = new Hashtable();

	private Hashtable transitions = new Hashtable();

	/**
	 * Constructor of class DefaultFlowDefinition
	 *
	 * @param flowId Id of this flow
	 */
	public DefaultFlowDefinition(String flowId)
	{
		id = flowId;
	}

	/**
	 * Method addState() in class DefaultFlowDefinition
	 *
	 * @param state
	 */
	public void addState(State state)
	{
		states.put(state.getId(), state);
	}

	/**
	 * Add transition to definition
	 *
	 * @param transition Transition object
	 */
	public void addTransition(Transition transition)
	{
		transitions.put(transition.getId(), transition);
	}

	/**
	 * Override method getEntrance() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getEntrance()
	 */
	public State getEntrance()
	{
		return getState(entranceName);
	}

	/**
	 * Override method getExit() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getExit()
	 */
	public State getExit()
	{
		return getState(exitName);
	}

	/**
	 * Override method getId() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getId()
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Override method getState() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getState(java.lang.String)
	 */
	public State getState(String stateId)
	{
		return (State) states.get(stateId);
	}

	/**
	 * Override method getStates() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getStates()
	 */
	public State[] getStates()
	{
		return (State[]) states.values().toArray(State.EMPTY_ARRAY);
	}

	/**
	 * Override method getTransition() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getTransition(java.lang.String)
	 */
	public Transition getTransition(String transitionId)
	{
		return (Transition) transitions.get(transitionId);
	}

	/**
	 * Override method getTransitions() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.FlowDefinition#getTransitions()
	 */
	public Transition[] getTransitions()
	{
		return (Transition[]) transitions.values().toArray(
				Transition.EMPTY_ARRAY);
	}

	/**
	 * Setter method for property entranceName
	 *
	 * @param name The entranceName to set.
	 */
	public void setEntrance(String name)
	{
		entranceName = name;
	}

	/**
	 * Setter method for property exitName
	 *
	 * @param name The exitName to set.
	 */
	public void setExit(String name)
	{
		exitName = name;
	}
}