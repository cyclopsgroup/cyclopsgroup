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

import com.cyclopsgroup.petri.definition.FlowDefinition;

/**
 * Flow definition loader
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class FlowDefinitionLoader extends AbstractSyntaxLoader
{
	private static final FlowDefinitionLoader ONLY_INSTANCE = new FlowDefinitionLoader();

	/**
	 * Method getInstance() in class FlowDefinitionLoader
	 *
	 * @return
	 */
	public static final FlowDefinitionLoader getInstance()
	{
		return ONLY_INSTANCE;
	}

	private FlowDefinitionLoader()
	{

	}

	/**
	 * Override method createEmptyContext() in super class
	 *
	 * @see com.cyclopsgroup.petri.syntax.AbstractSyntaxLoader#createEmptyContext()
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
	public FlowDefinition getFlowDefinition(String name)
	{
		return (FlowDefinition) getModel(name);
	}

	/**
	 * Override method getResource() in super class
	 *
	 * @see com.cyclopsgroup.petri.syntax.AbstractSyntaxLoader#getResource(java.lang.String)
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
	 * @see com.cyclopsgroup.petri.syntax.AbstractSyntaxLoader#getResultKey()
	 */
	protected String getResultKey()
	{
		return "result";
	}
}