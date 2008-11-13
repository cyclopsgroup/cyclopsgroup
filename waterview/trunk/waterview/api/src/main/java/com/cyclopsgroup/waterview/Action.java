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
package com.cyclopsgroup.waterview;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * An action to run
 */
public abstract class Action
{
    private ServiceManager serviceManager;

    /**
     * Run action
     *
     * @param data Runtime data
     * @param context Action context interface
     * @throws Exception Throw it out
     */
    public abstract void execute( RunData data, ActionContext context )
        throws Exception;

    public ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    public void setServiceManager( ServiceManager serviceManager )
    {
        this.serviceManager = serviceManager;
    }
}
