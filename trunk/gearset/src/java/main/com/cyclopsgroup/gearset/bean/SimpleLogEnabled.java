/* ==========================================================================
 * Copyright 2002-2004 Cyclops Group Community
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
package com.cyclopsgroup.gearset.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple commons logging enabled object
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class SimpleLogEnabled implements LogEnabled
{
    private Log logger;

    /**
     * Getter method for property logger
     * 
     * @return Returns the logger.
     */
    public Log getLogger()
    {
        synchronized (this)
        {
            if (logger == null)
            {
                logger = LogFactory.getLog(getClass());
            }
        }
        return logger;
    }

    /**
     * Setter method for property logger
     * 
     * @param commonsLogger The logger to set.
     */
    public void setLogger(Log commonsLogger)
    {
        logger = commonsLogger;
    }
}