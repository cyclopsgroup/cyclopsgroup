/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.util.Hashtable;

import com.cyclopsgroup.petri.definition.NetDefinition;
import com.cyclopsgroup.petri.definition.Place;
import com.cyclopsgroup.petri.definition.Transition;

/**
 * Default implementation of flow definition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class DefaultFlowDefinition implements NetDefinition
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
	public void addState(Place state)
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
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getEntrance()
	 */
	public Place getEntrance()
	{
		return getState(entranceName);
	}

	/**
	 * Override method getExit() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getExit()
	 */
	public Place getExit()
	{
		return getState(exitName);
	}

	/**
	 * Override method getId() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getId()
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Override method getState() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getState(java.lang.String)
	 */
	public Place getState(String stateId)
	{
		return (Place) states.get(stateId);
	}

	/**
	 * Override method getStates() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getStates()
	 */
	public Place[] getStates()
	{
		return (Place[]) states.values().toArray(Place.EMPTY_ARRAY);
	}

	/**
	 * Override method getTransition() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getTransition(java.lang.String)
	 */
	public Transition getTransition(String transitionId)
	{
		return (Transition) transitions.get(transitionId);
	}

	/**
	 * Override method getTransitions() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.NetDefinition#getTransitions()
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