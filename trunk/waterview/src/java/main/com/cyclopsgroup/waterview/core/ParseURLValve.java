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

import org.apache.avalon.framework.service.ServiceManager;

import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Valve to parse URL
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class ParseURLValve implements Valve
{

    /**
     * Override method process in super class of ParseURLValve
     * 
     * @see com.cyclopsgroup.waterview.core.Valve#process(com.cyclopsgroup.waterview.UIRuntime, org.apache.avalon.framework.service.ServiceManager)
     */
    public void process(UIRuntime runtime, ServiceManager serviceManager)
            throws Exception
    {
        String path = runtime.getHttpServletRequest().getPathInfo();
        System.out.println(path);
    }
}