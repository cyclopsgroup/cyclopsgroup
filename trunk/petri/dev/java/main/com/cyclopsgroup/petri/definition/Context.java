/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.definition;

/**
 * Runtime context interface
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public interface Context
{

	/**
	 * Get variable value
	 *
	 * @param name Name of the variable
	 * @return Object value
	 */
	Object get(String name);

	/**
	 * Get variable names
	 *
	 * @return Name of the variables
	 */
	String[] getNames();

	/**
	 * Add object into context
	 *
	 * @param name Name of object
	 * @param object Value of object
	 */
	void put(String name, Object object);
}