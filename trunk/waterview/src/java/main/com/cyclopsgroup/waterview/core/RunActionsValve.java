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
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.ModuleResolver;
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

    private ModuleResolver moduleResolver;

    /**
     * Override method process in super class of RunResolverValve
     * 
     * @see com.cyclopsgroup.waterview.Valve#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        String action = runtime.getRequestParameters().getString("do");
        if (StringUtils.isNotEmpty(action))
        {
            moduleResolver.resolve(runtime, action);
        }
    }

    /**
     * Override method service in super class of RunActionsValve
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException
    {
        moduleResolver = (ModuleResolver) manager.lookup(ModuleResolver.ROLE);
    }
}