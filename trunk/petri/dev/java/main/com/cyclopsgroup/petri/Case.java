/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri;

import java.util.Map;

/**
 * Case model
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Case
{
	/** Empty case array */
	Case[] EMPTY_ARRAY = new Case[0];

	/**
	 * Get modifiable attribute map attached to this case
	 *
	 * @return Attribute map
	 */
	Map getAttributes();

	/**
	 * Get current states
	 *
	 * @return Array of state ids
	 */
	String[] getCurrentStates();

	/**
	 * Get flow definition this case belongs to
	 *
	 * @return Full name of state machine
	 */
	String getFlowDefinitionName();

	/**
	 * Method getId() in class Case
	 *
	 * @return Id of this case
	 */
	String getId();
}