/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * Out arc between transition and state
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface OutputArc
{
	/** Empty output arc array */
	OutputArc[] EMPTY_ARRAY = new OutputArc[0];

	/**
	 * Get from transition
	 *
	 * @return Transition this arc comes from
	 */
	public Transition getFrom();

	/**
	 * Get guard of this arc
	 *
	 * @return Guard of arc
	 */
	public Guard getGuard();

	/**
	 * Get to state
	 *
	 * @return To state
	 */
	public Place getTo();

	/**
	 * If this arc is default arc
	 *
	 * @return If it's default arc
	 */
	public boolean isDefault();
}