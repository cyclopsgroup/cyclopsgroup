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
package com.cyclopsgroup.waterview.spi;

import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Base module runnable
 */
public abstract class BaseModuleRunnable
{
    private String modulePath;

    /**
     * Constructor for class BaseModuleRunnable
     *
     * @param modulePath Path for module
     */
    protected BaseModuleRunnable( String modulePath )
    {
        this.modulePath = modulePath;
    }

    /**
     * Get module path
     *
     * @return Module path
     */
    public String getModulePath()
    {
        return modulePath;
    }

    /**
     * Get name of module
     * @return Name
     */
    public String getName()
    {
        return modulePath;
    }

    /**
     * Run attached module
     *
     * @param data Runtime data
     * @param context Context
     * @throws Exception Simply throw it out
     */
    protected void runModule( RunDataSpi data, Context context )
        throws Exception
    {
        if ( StringUtils.isNotEmpty( getModulePath() ) )
        {
            ModuleService moduleManager = (ModuleService) data.getServiceManager().lookup( ModuleService.ROLE );
            moduleManager.runModule( modulePath, data, context );
        }
    }
}
