/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.cyclib.log4j;

import org.apache.commons.logging.Log;
import org.codehaus.plexus.logging.AbstractLogger;
import org.codehaus.plexus.logging.Logger;

/**
 * Log4j implemented avalon logger
 *
 * @author <a href="mailto:jiaqi.guo@evavi.com">Jiaqi Guo</a>
 */
public class AvalonLoggerAdapter extends AbstractLogger
{
    private Log logger;

    /**
     * Constructor for class AvalonLoggerAdapter
     *
     * @param logger Commons logger object
     */
    public AvalonLoggerAdapter(Log logger)
    {
        super(LEVEL_DEBUG, "internal-logger");
        this.logger = logger;
    }

    /**
     * Override method debug() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    public void debug(String message, Throwable e)
    {
        logger.debug(message, e);
    }

    /**
     * Override method error() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#error(java.lang.String, java.lang.Throwable)
     */
    public void error(String message, Throwable e)
    {
        logger.error(message, e);
    }

    /**
     * Override method fatalError() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#fatalError(java.lang.String, java.lang.Throwable)
     */
    public void fatalError(String message, Throwable e)
    {
        logger.fatal(message, e);
    }

    /**
     * Override method getChildLogger() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#getChildLogger(java.lang.String)
     */
    public Logger getChildLogger(String child)
    {
        return this;
    }

    /**
     * Getter method for logger
     *
     * @return Returns the logger.
     */
    public Log getLogger()
    {
        return logger;
    }

    /**
     * Override method info() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#info(java.lang.String, java.lang.Throwable)
     */
    public void info(String message, Throwable e)
    {
        logger.info(message, e);
    }

    /**
     * Setter method for logger
     *
     * @param logger The logger to set.
     */
    public void setLogger(Log logger)
    {
        this.logger = logger;
    }

    /**
     * Override method warn() in super class
     *
     * @see org.codehaus.plexus.logging.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    public void warn(String message, Throwable e)
    {
        logger.warn(message, e);
    }
}