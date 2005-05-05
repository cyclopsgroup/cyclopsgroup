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
package com.cyclopsgroup.waterview.core;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.ActionResolver;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.PageRuntime;

/**
 * Module based action resolver
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ModuleActionResolver extends AbstractLogEnabled implements
        Serviceable, ActionResolver
{
    private ModuleManager moduleManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.ActionResolver#resolveAction(java.lang.String, com.cyclopsgroup.waterview.PageRuntime)
     */
    public void resolveAction(String action, PageRuntime runtime) throws Exception
    {
        Module module = moduleManager.getModule("action/" + action);
        if (module != null)
        {
            module.execute(runtime, runtime.getPageContext());
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
