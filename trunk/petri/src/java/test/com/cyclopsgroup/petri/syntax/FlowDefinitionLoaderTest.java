/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax;

import junit.framework.TestCase;

import com.cyclopsgroup.petri.definition.FlowDefinition;
import com.cyclopsgroup.petri.syntax.FlowDefinitionLoader;

/**
 * First test case
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class FlowDefinitionLoaderTest extends TestCase
{
	/**
	 * Method testGetDefinitionLoader() in class FlowDefinitionLoaderTest
	 * 
	 */
	public void testGetDefinitionLoader()
	{
		FlowDefinition fd = FlowDefinitionLoader.getInstance()
				.getFlowDefinition("com.cyclopsgroup.petri.syntax.SimpleFlow1");
		System.out.println(fd);
	}
}