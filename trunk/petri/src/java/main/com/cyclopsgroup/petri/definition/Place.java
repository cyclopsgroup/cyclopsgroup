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
public interface Place
{
	/** Empty array */
	Place[] EMPTY_ARRAY = new Place[0];

	/**
	 * Get flow definition this state belongs to
	 *
	 * @return State machine object
	 */
	NetDefinition getFlowDefinition();

	/**
	 * Get Id of this state
	 *
	 * @return Id
	 */
	String getId();

	/**
	 * Get inbound arcs
	 *
	 * @return Array of arcs
	 */
	OutputArc[] getInboundArcs();

	/**
	 * Get outbound arcs
	 *
	 * @return Array of arcs
	 */
	InputArc[] getOutboundArcs();
}