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
package com.cyclopsgroup.waterview.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

/**
 * Console log system
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityLogSystem implements LogSystem
{
    private Log logger = LogFactory
            .getLog("com.cyclops.waterview.core.velocity");

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.runtime.log.LogSystem#init(org.apache.velocity.runtime.RuntimeServices)
     */
    public void init(RuntimeServices runtimeServices) throws Exception
    {
        //do nothing
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.runtime.log.LogSystem#logVelocityMessage(int, java.lang.String)
     */
    public void logVelocityMessage(int level, String message)
    {
        logger.info("level " + level + ": " + message);
    }
}