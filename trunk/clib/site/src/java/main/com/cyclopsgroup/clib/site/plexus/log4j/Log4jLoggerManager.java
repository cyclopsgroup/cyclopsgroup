/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.clib.site.plexus.log4j;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.plexus.logging.AbstractLoggerManager;
import org.codehaus.plexus.logging.Logger;

/**
 * Log4j implemented Avalon logger
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Log4jLoggerManager extends AbstractLoggerManager implements
        Initializable, Configurable, Disposable
{

    private String log4jFile;

    private Properties log4jProperties = new Properties();

    private Hashtable loggers = new Hashtable();

    private int threshold = 0;

    private Hashtable thresholds = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        setLog4jFile(conf.getChild("properties").getValue(
                "com/cyclopsgroup/clib/site/plexus/log4j/log4j.properties"));
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public void dispose()
    {
        loggers.clear();
        threshold = 0;
        thresholds.clear();
        log4jProperties.clear();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#getActiveLoggerCount()
     */
    public int getActiveLoggerCount()
    {
        return loggers.size();
    }

    /**
     * Getter method for log4jFile
     *
     * @return Returns the log4jFile.
     */
    public String getLog4jFile()
    {
        return log4jFile;
    }

    /**
     * Getter method for log4jProperties
     *
     * @return Returns the log4jProperties.
     */
    public Properties getLog4jProperties()
    {
        return log4jProperties;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#getLoggerForComponent(java.lang.String, java.lang.String)
     */
    public Logger getLoggerForComponent(String role, String hint)
    {
        String s = role + '/' + hint;
        if (loggers.containsKey(s))
        {
            return (Logger) loggers.get(s);
        }
        Logger logger = new Log4jLogger(getThreshold(role, hint), s);
        return logger;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#getThreshold()
     */
    public int getThreshold()
    {
        return threshold;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#getThreshold(java.lang.String, java.lang.String)
     */
    public int getThreshold(String role, String hint)
    {
        String s = role + '/' + hint;
        if (thresholds.containsKey(s))
        {
            Integer i = (Integer) thresholds.get(s);
            return i.intValue();
        }
        return getThreshold();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize() throws Exception
    {
        File file = new File(getLog4jFile());
        if (file.isFile())
        {
            log4jProperties.load(new FileInputStream(getLog4jFile()));
        }
        else
        {
            URL resource = getClass().getClassLoader().getResource(
                    getLog4jFile());
            if (resource == null)
            {
                System.out.println("Get log4j file doesn't exist");
            }
            else
            {
                getLog4jProperties().load(resource.openStream());
            }
        }
        PropertyConfigurator.configure(getLog4jProperties());
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#returnComponentLogger(java.lang.String, java.lang.String)
     */
    public void returnComponentLogger(String category, String key)
    {
        //do nothing
    }

    /**
     * Set log4j file Path
     *
     * @param log4jFile The log4jFile to set.
     */
    public void setLog4jFile(String log4jFile)
    {
        this.log4jFile = log4jFile;
    }

    /**
     * TODO Add javadoc for this method
     *
     * @param key
     * @param value
     */
    public void setLog4jProperty(String key, String value)
    {
        getLog4jProperties().setProperty(key, value);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#setThreshold(int)
     */
    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.LoggerManager#setThreshold(java.lang.String, java.lang.String, int)
     */
    public void setThreshold(String role, String hint, int threshold)
    {
        String s = role + '/' + hint;
        thresholds.put(s, new Integer(threshold));
    }
}
