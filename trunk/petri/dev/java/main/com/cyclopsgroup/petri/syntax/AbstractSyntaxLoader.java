/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.petri.syntax;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.gearset.beans.SimpleLogEnabled;

/**
 * Base class for syntax loader
 * 
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo </a>
 */
public abstract class AbstractSyntaxLoader extends SimpleLogEnabled
{

    /** Inner entry class */
    private class Entry
    {
        /** Last modified timestamp */
        long lastModified = -1;

        /** Model object */
        Object model;

        /**
         * Constructor of class Entry
         * 
         * @param modelObject Model object
         * @param lastModifiedTimestamp Last modified time of resource
         */
        private Entry(Object modelObject, long lastModifiedTimestamp)
        {
            model = modelObject;
            lastModified = lastModifiedTimestamp;
        }
    }

    private Hashtable cachedEntries = new Hashtable();

    private JellyContext initialContext;

    /**
     * Create empty jelly context
     * 
     * @return
     */
    protected abstract JellyContext createEmptyContext();

    /**
     * Get initial context
     * 
     * @return Initial jelly context
     */
    public JellyContext getInitialContext()
    {
        synchronized (this)
        {
            if (initialContext == null)
            {
                initialContext = createEmptyContext();
                initialContext.getVariables().putAll(System.getProperties());
                initialContext.setVariable("basedir", new File("")
                        .getAbsolutePath());
            }
        }
        return initialContext;
    }

    /**
     * Get model object
     * 
     * @param name
     * @return
     */
    public Object getModel(String name)
    {
        URL resource = getResource(name);
        if (resource == null)
        {
            throw new NullPointerException("Resource for flow definition "
                    + name + " can not be found");
        }
        Object ret = null;
        File resourceFile = new File(resource.getPath());
        if (cachedEntries.containsKey(name) && resourceFile.isFile())
        {
            long resourceTimestamp = resourceFile.lastModified();
            Entry entry = (Entry) cachedEntries.get(name);
            if (resourceTimestamp <= entry.lastModified)
            {
                ret = entry.model;
            }
        }
        if (ret == null)
        {
            JellyContext jc = new JellyContext(getInitialContext());
            try
            {
                JellyContext result = jc.runScript(resource, XMLOutput
                        .createDummyXMLOutput());
                ret = result.getVariable(getResultKey());
            }
            catch (Exception e)
            {
                getLogger().error(e);
            }
        }
        return ret;
    }

    /**
     * Get resource of loaded url
     * 
     * @param name Name of object requested
     * @return URL of resource
     */
    public abstract URL getResource(String name);

    /**
     * Get the name of result model in context
     * 
     * @return Name of the model
     */
    protected abstract String getResultKey();
}