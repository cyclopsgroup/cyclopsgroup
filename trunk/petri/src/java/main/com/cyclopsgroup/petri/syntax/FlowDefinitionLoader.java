/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax;

import java.net.URL;

import org.apache.commons.jelly.JellyContext;

import com.cyclopsgroup.petri.definition.NetDefinition;
import com.evavi.common.syntax.AbstractSyntaxLoader;

/**
 * Flow definition loader
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class FlowDefinitionLoader extends AbstractSyntaxLoader implements
		com.cyclopsgroup.petri.definition.NetDefinitionLoader
{
	/**
	 * Method createEmptyContext() in class FlowDefinitionLoader
	 *
	 * @return
	 */
	protected JellyContext createEmptyContext()
	{
		return new FlowDefinitionTagRegistry().createJellyContext();
	}

	/**
	 * Method getFlowDefinition() in class FlowDefinitionLoader
	 *
	 * @param name
	 * @return
	 */
	public NetDefinition getFlowDefinition(String name)
	{
		return (NetDefinition) getModel(name);
	}

	/**
	 * Override method getResource() in super class
	 *
	 * @see com.evavi.common.syntax.AbstractSyntaxLoader#getResource(java.lang.String)
	 */
	public URL getResource(String name)
	{
		String path = name.replace('.', '/');
		path += ".xml";
		return getClass().getClassLoader().getResource(path);
	}

	/**
	 * Override method getResultKey() in super class
	 *
	 * @see com.evavi.common.syntax.AbstractSyntaxLoader#getResultKey()
	 */
	protected String getResultKey()
	{
		return "result";
	}
}