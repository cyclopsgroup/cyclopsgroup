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

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Class implemented action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultActionResolver extends AbstractLogEnabled implements
        ActionResolver, Serviceable
{
    private ModuleManager moduleManager;

    private void execute(Object object, UIRuntime runtime, Context context)
            throws Exception
    {
        if (object instanceof Action)
        {
            ((Action) object).execute(runtime);
        }
        if (object instanceof Page)
        {
            ((Page) object).prepare(runtime, context);
        }
    }

    /**
     * Override or implement method of parent class or interface
     * 
     * @see com.cyclopsgroup.waterview.ActionResolver#exists(java.lang.String, java.lang.String)
     */
    public boolean exists(String packageName, String moduleName)
    {
        return moduleManager.getActionModule(packageName, moduleName) != null;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ActionResolver#resolve(java.lang.String, java.lang.String, com.cyclopsgroup.waterview.UIRuntime)
     */
    public void resolve(String packageName, String moduleName, UIRuntime runtime)
            throws Exception
    {
        Action action = moduleManager.getActionModule(packageName, moduleName);
        if (action != null)
        {
            action.equals(runtime);
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager serviceManager) throws ServiceException
    {
        moduleManager = (ModuleManager) serviceManager
                .lookup(ModuleManager.ROLE);
    }
}