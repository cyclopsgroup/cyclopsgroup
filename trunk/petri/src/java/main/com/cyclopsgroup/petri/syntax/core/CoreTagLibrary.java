/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax.core;

import java.net.URL;

import com.cyclopsgroup.gearset.xml.PropertyTagLibrary;

/**
 * Tag library for syntax package
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class CoreTagLibrary extends PropertyTagLibrary
{
    /**
     * Override method getLibraryResource in super class of CoreTagLibrary
     * 
     * @see com.cyclopsgroup.gearset.xml.PropertyTagLibrary#getLibraryResource()
     */
    protected URL getLibraryResource()
    {
        return getClass().getResource("tag-mapping.properties");
    }
}