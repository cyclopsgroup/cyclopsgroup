/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.cyclib.velocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Default implementation of velocity component
 */
public class DefaultVelocityComponent extends VelocityComponent implements
        Initializable, Configurable, Contextualizable
{

    private String basedir;

    private Log logger = LogFactory.getLog(getClass());

    private String logTag;

    private String propertiesFile;

    /**
     * Override method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        String props = conf.getChild("properties").getValue(
                "velocity.properties");
        File file = new File(basedir, props);
        if (!file.isFile())
        {
            file = new File(props);
        }
        propertiesFile = file.getAbsolutePath();

        logTag = conf.getChild("logtag").getValue("runtime");
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(
            org.apache.avalon.framework.context.Context context)
            throws ContextException
    {
        basedir = (String) context.get("basedir");
        if (basedir == null)
        {
            basedir = new File("").getAbsolutePath();
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.cyclib.velocity.VelocityComponent#evaluate(org.apache.velocity.context.Context, java.lang.String)
     */
    public String evaluate(Context context, String template) throws Exception
    {
        StringWriter sw = new StringWriter();
        Velocity.evaluate(context, sw, logTag, template);
        return sw.toString();
    }

    /**
     * Override method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        if (!new File(propertiesFile).isFile())
        {
            throw new FileNotFoundException("Velocity properties file ["
                    + propertiesFile + "] is invalid");
        }
        ExtendedProperties props = new ExtendedProperties(propertiesFile);
        props.setProperty("basedir", new File("").getAbsolutePath());
        setExtendedProperties(props);
        init();
        Velocity.setExtendedProperties(props);
        Velocity.init();
    }
}