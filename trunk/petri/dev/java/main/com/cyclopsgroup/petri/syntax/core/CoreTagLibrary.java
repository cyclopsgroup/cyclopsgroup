/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.net.URL;

import com.evavi.common.syntax.PropertyTagLibrary;

/**
 * TODO Add java doc for this class
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class CoreTagLibrary extends PropertyTagLibrary
{

	/**
	 * Override method getTagMappingResource() in super class
	 *
	 * @see com.evavi.common.syntax.PropertyTagLibrary#getTagMappingResource()
	 */
	public URL getTagMappingResource()
	{
		return getClass().getResource("tag-mapping.properties");
	}

}