/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * Input arc for transition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface InputArc
{
	/** Empty input arc array */
	InputArc[] EMPTY_ARRAY = new InputArc[0];

	/**
	 * Get from state
	 *
	 * @return From state
	 */
	Place getFrom();

	/**
	 * Get to transition
	 *
	 * @return To transition object
	 */
	Transition getTo();

	//TODO I guess we will need it soon
	//Guard getGuard();
}