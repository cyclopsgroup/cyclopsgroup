/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.engine;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.cyclopsgroup.petri.definition.Context;

/**
 * Simple context implementation
 * Not thread safe
 * Just for runtime usage
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class SimpleContext implements Context
{

	private HashMap content = new HashMap();

	private Context parent;

	/**
	 * Constructor of class SimpleContext
	 *
	 * @param parentContext Parent  context object
	 */
	public SimpleContext(Context parentContext)
	{
		parent = parentContext;
	}

	/**
	 * Override method get() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.Context#get(java.lang.String)
	 */
	public Object get(String name)
	{
		if (content.containsKey(name))
		{
			return content.get(name);
		}
		else if (parent != null)
		{
			return parent.get(name);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Getter method for property content
	 *
	 * @return Returns the content.
	 */
	public HashMap getContent()
	{
		return content;
	}

	/**
	 * Override method getNames() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.Context#getNames()
	 */
	public String[] getNames()
	{
		HashSet names = new HashSet(content.keySet());
		if (parent != null)
		{
			CollectionUtils.addAll(names, parent.getNames());
		}
		return (String[]) names.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}

	/**
	 * Override method put() in super class
	 *
	 * @see com.cyclopsgroup.petri.definition.Context#put(java.lang.String, java.lang.Object)
	 */
	public void put(String name, Object object)
	{
		if (object == null)
		{
			content.remove(name);
		}
		else
		{
			content.put(name, object);
		}
	}
}