/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax;

import com.evavi.common.syntax.AbstractTagRegistry;

/**
 * Flow definition tags registry
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class FlowDefinitionTagRegistry extends AbstractTagRegistry
{
	private static final String PATH = "META-INF/evavi/bpm-taglibrary.properties";

	/**
	 * Override method getResourcePath() in super class
	 *
	 * @see com.evavi.common.syntax.AbstractTagRegistry#getResourcePath()
	 */
	protected String getResourcePath()
	{
		return PATH;
	}
}