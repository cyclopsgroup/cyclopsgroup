/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

import org.apache.commons.lang.enum.Enum;

/**
 * Transition model in finite state machine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Transition
{
	/** Multi input states policy class */
	public static final class InputPolicy extends Enum
	{
		/**
		 * Get policy object associated to a string
		 *
		 * @param value String value, and or or
		 * @return Policy object or null
		 */
		public static InputPolicy valueOf(String value)
		{
			return (InputPolicy) getEnum(InputPolicy.class, value);
		}

		private InputPolicy(String value)
		{
			super(value);
		}
	}

	/** Multi output states policy class */
	public static final class OutputPolicy extends Enum
	{

		/**
		 * Get policy object associated to a string
		 *
		 * @param value String value, and or or
		 * @return Policy object or null
		 */
		public static OutputPolicy valueOf(String value)
		{
			return (OutputPolicy) getEnum(OutputPolicy.class, value);
		}

		private OutputPolicy(String value)
		{
			super(value);
		}
	}

	/** And policy for multipal input states */
	public static final InputPolicy AND = new InputPolicy("and");

	/** Or policy for multipal input states */
	public static final InputPolicy OR = new InputPolicy("or");

	/** Parallel output policy */
	public static final OutputPolicy PARALLEL = new OutputPolicy("parallel");

	/** Sequential output policy */
	public static final OutputPolicy SEQUENTIAL = new OutputPolicy("sequential");

	/** Empty transition array*/
	Transition[] EMPTY_ARRAY = new Transition[0];

	/**
	 * Get flow definition this transition belongs to
	 *
	 * @return State machine object
	 */
	NetDefinition getFlowDefinition();

	/**
	 * Get guard
	 *
	 * @return Guard object to monitor this transition
	 */
	Guard getGuard();

	/**
	 * Get id of transition
	 *
	 * @return Strng id
	 */
	String getId();

	/**
	 * Get arcs to this transtion
	 *
	 * @return Array of arcs
	 */
	InputArc[] getInputArcs();

	/**
	 * Get input states policy
	 *
	 * @return Input states policy
	 */
	InputPolicy getInputPolicy();

	/**
	 * Get arcs from this transition
	 *
	 * @return Array of arcs
	 */
	OutputArc[] getOutputArcs();

	/**
	 * Get output policy
	 *
	 * @return Output policy
	 */
	OutputPolicy getOutputPolicy();

	/**
	 * Get task attached to this transition
	 *
	 * @return Task object
	 */
	Task getTask();

	/**
	 * Get the trigger of this transition
	 *
	 * @return Transition trigger
	 */
	Trigger getTrigger();

	/**
	 * If this transition is initial transition
	 *
	 * @return Initial transition flag
	 */
	boolean isInitial();

	/**
	 * If this transition is terminal transition
	 *
	 * @return Terminal transition flat
	 */
	boolean isTerminal();
}