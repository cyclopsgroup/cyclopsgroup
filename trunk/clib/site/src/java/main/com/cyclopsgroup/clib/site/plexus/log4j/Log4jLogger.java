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

import org.codehaus.plexus.logging.AbstractLogger;
import org.codehaus.plexus.logging.Logger;

/**
 * Log4j implemented logger
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Log4jLogger extends AbstractLogger
{

    private String category;

    private org.apache.log4j.Logger logger;

    private int threshold;

    /**
     * Constructor for class Log4jLogger
     *
     * @param threshold Threshold of logger
     * @param category Category of logger
     */
    public Log4jLogger(int threshold, String category)
    {
        super(threshold, category);
        this.logger = org.apache.log4j.Logger.getLogger(category);
        this.category = category;
        this.threshold = threshold;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    public void debug(String msg, Throwable e)
    {
        logger.debug(msg, e);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#error(java.lang.String, java.lang.Throwable)
     */
    public void error(String msg, Throwable e)
    {
        logger.error(msg, e);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#fatalError(java.lang.String, java.lang.Throwable)
     */
    public void fatalError(String msg, Throwable e)
    {
        logger.fatal(msg, e);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#getChildLogger(java.lang.String)
     */
    public Logger getChildLogger(String childName)
    {
        String c = category + "." + childName;
        return new Log4jLogger(threshold, c);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#info(java.lang.String, java.lang.Throwable)
     */
    public void info(String msg, Throwable e)
    {
        logger.info(msg, e);
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.codehaus.plexus.logging.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    public void warn(String msg, Throwable e)
    {
        logger.warn(msg, e);
    }
}
