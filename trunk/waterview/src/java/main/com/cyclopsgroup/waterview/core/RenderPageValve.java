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
package com.cyclopsgroup.waterview.core;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * TODO Add javadoc for class
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RenderPageValve extends AbstractLogEnabled implements Valve,
        Configurable
{

    /**
     * Override method configure in super class of RenderPageValve
     * 
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Override method process in super class of RenderPageValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public boolean process(UIRuntime runtime) throws Exception
    {
        // TODO Auto-generated method stub
        return false;
    }
}