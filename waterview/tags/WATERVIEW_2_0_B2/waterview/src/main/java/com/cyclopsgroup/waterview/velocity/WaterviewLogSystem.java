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
package com.cyclopsgroup.waterview.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

/**
 * Internally implemented log system
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class WaterviewLogSystem
    implements LogSystem
{
    private Log logger;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.runtime.log.LogSystem#init(org.apache.velocity.runtime.RuntimeServices)
     */
    public void init( RuntimeServices services )
        throws Exception
    {
        String category = services.getString( "runtime.log.category", "com.cyclopsgroup.clib.site.velocity" );
        logger = LogFactory.getLog( category );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.velocity.runtime.log.LogSystem#logVelocityMessage(int, java.lang.String)
     */
    public void logVelocityMessage( int level, String value )
    {
        switch ( level )
        {
            case DEBUG_ID:
                logger.debug( value );
                break;
            case WARN_ID:
                logger.warn( value );
                break;
            case INFO_ID:
                logger.info( value );
                break;
            case ERROR_ID:
                logger.error( value );
                break;
            default:
                logger.debug( value );
        }
    }
}
