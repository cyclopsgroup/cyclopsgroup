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

import java.util.Iterator;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import com.cyclopsgroup.waterview.UIModuleResolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RunActionsValve extends AbstractLogEnabled implements Valve,
        Serviceable
{

    private UIModuleResolver moduleResolver;

    /**
     * Override method process in super class of RunResolverValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        for (Iterator i = runtime.getActions().iterator(); i.hasNext();)
        {
            String path = "action/" + (String) i.next();
            runModule(path, runtime);
        }
    }

    private void runModule(String path, UIRuntime runtime) throws Exception
    {
        if (moduleResolver == null)
        {
            return;
        }
        moduleResolver.resolve(runtime, path);
    }

    /**
     * Override method service in super class of RunActionsValve
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        moduleResolver = (UIModuleResolver) manager
                .lookup(UIModuleResolver.ROLE);
    }
}