/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import com.cyclopsgroup.petri.definition.Context;

/**
 * Adapter class
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class CommonContextAdapter implements com.evavi.common.syntax.Context
{
	private Context context;

	/**
	 * Constructor of class CommonContextAdapter
	 *
	 * @param nestedContext Nested bpm context
	 */
	public CommonContextAdapter(Context nestedContext)
	{
		context = nestedContext;
	}

	/**
	 * Override method get() in super class
	 *
	 * @see com.evavi.common.syntax.Context#get(java.lang.String)
	 */
	public Object get(String name)
	{
		return context.get(name);
	}

	/**
	 * Override method getNames() in super class
	 *
	 * @see com.evavi.common.syntax.Context#getNames()
	 */
	public String[] getNames()
	{
		return context.getNames();
	}
}