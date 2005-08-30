/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.tornado.utils;

import java.util.Properties;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi@evavi.com">Jiaqi Guo</a>
 *
 * Avalon utils
 */
public final class ConfigurationUtils
{
    /**
     * Get properties from given configuration node
     *
     * @param root Configuration node root
     * @return Properties object
     * @throws ConfigurationException Illegal format of configuration
     */
    public static Properties getProperties(Configuration root)
            throws ConfigurationException
    {
        Properties p = new Properties();
        Configuration[] confs = root.getChildren("property");
        for (int i = 0; i < confs.length; i++)
        {
            Configuration conf = confs[i];
            String name = conf.getAttribute("name");
            String value = conf.getValue(StringUtils.EMPTY);
            if (StringUtils.isNotEmpty(name))
            {
                p.setProperty(name, value);
            }
        }
        return p;
    }
}
