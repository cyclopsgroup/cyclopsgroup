/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * State model in finite state machine
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface State
{
	/** Empty array */
	State[] EMPTY_ARRAY = new State[0];

	/**
	 * Get flow definition this state belongs to
	 *
	 * @return State machine object
	 */
	FlowDefinition getFlowDefinition();

	/**
	 * Get Id of this state
	 *
	 * @return Id
	 */
	String getId();

	/**
	 * Get inbound transition models attached to this state
	 *
	 * @return Array of inbound transitions
	 */
	Transition[] getInboundTransitions();

	/**
	 * Get outbound transition models attached to this state
	 *
	 * @return Array of outbound transitions
	 */
	Transition[] getOutboundTransitions();
}