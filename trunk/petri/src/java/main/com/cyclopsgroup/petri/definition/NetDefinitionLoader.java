/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * Interface to load a flow definition
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface NetDefinitionLoader
{
	/** Role name in container */
	String ROLE = NetDefinitionLoader.class.getName();

	/**
	 * Get flow definition with given id
	 *
	 * @param flowDefinitionId Flow definition id
	 * @return Flow definition object
	 */
	NetDefinition getFlowDefinition(String flowDefinitionId);
}