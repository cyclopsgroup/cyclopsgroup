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

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.Resolver;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RunResolverValve implements Valve
{
    /**
     * Override method process in super class of RunResolverValve
     * 
     * @see com.cyclopsgroup.waterview.core.Valve#process(com.cyclopsgroup.waterview.UIRuntime, org.apache.avalon.framework.service.ServiceManager)
     */
    public void process(UIRuntime runtime, ServiceManager serviceManager)
            throws Exception
    {

        for (Iterator i = runtime.getActions().iterator(); i.hasNext();)
        {
            String path = (String) i.next();
            runModule(path, runtime, serviceManager);
        }

    }

    private void runModule(String path, UIRuntime runtime,
            ServiceManager serviceManager) throws Exception
    {
        Waterview waterview = (Waterview) serviceManager.lookup(Waterview.ROLE);
        int lastDotPosition = path.lastIndexOf('.');
        String extension = path.substring(lastDotPosition + 1);
        Resolver resolver = waterview.getResolver(extension);
        if (resolver == null)
        {
            resolver = waterview.getDefaultResolver();
        }
        if (resolver != null)
        {
            resolver.resolve(path, runtime, serviceManager);
        }
    }
}