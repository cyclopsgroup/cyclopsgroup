/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.cyclib.velocity;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo </a>
 * 
 * Default implementation of velocity component
 */
public class DefaultVelocityComponent extends VelocityComponent implements
        Initializable, Configurable, Contextualizable
{
    private org.apache.avalon.framework.context.Context context;

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
        propertiesFile = conf.getChild("properties").getValue(
                "com/cyclopsgroup/cyclib/velocity/velocity.properties");
        logTag = conf.getChild("logtag").getValue("runtime");
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(org.apache.avalon.framework.context.Context ctx)
            throws ContextException
    {
        this.context = ctx;
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.cyclib.velocity.VelocityComponent#evaluate(org.apache.velocity.context.Context, java.lang.String)
     */
    public String evaluate(Context ctx, String template) throws Exception
    {
        StringWriter sw = new StringWriter();
        Velocity.evaluate(ctx, sw, logTag, template);
        return sw.toString();
    }

    /**
     * Override method initialize()
     * 
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        File file = new File(propertiesFile);
        ExtendedProperties props = null;

        if (file.isFile())
        {
            props = new ExtendedProperties(file.getAbsolutePath());
        }
        else
        {
            URL resource = getClass().getClassLoader().getResource(
                    propertiesFile);
            if (resource == null)
            {
                logger.error("Can not load velocity properties at ["
                        + propertiesFile + "], default will be used");
                resource = getClass().getResource("velocity.properties");
            }
            props.load(resource.openStream());
        }
        String basedir = (String) context.get("basedir");
        if (StringUtils.isNotEmpty(basedir))
        {
            props.setProperty("basedir", basedir);
        }
        setExtendedProperties(props);
        init();
        Velocity.setExtendedProperties(props);
        Velocity.init();
    }
}