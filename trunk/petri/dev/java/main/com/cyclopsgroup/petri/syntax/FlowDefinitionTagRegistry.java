/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax;

import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;

import com.cyclopsgroup.gearset.xml.PropertyTagLibraryRegistry;

/**
 * Flow definition tags registry
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public class FlowDefinitionTagRegistry extends PropertyTagLibraryRegistry
{
    private static final String PATH = "META-INF/cyclopsgroup/petri-taglibrary.properties";

    /**
     * Override method getRegistryResources in super class of FlowDefinitionTagRegistry
     * 
     * @see com.cyclopsgroup.gearset.xml.PropertyTagLibraryRegistry#getRegistryResources()
     */
    protected URL[] getRegistryResources() throws Exception
    {
        ArrayList urls = new ArrayList();
        CollectionUtils.addAll(urls, getClass().getClassLoader().getResources(
                PATH));
        return (URL[]) urls.toArray(EMPTY_URL_ARRAY);
    }
}