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
package com.cyclopsgroup.waterview.valves;

import java.util.Iterator;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.ModuleManager;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class RunActionsValve extends AbstractLogEnabled implements Valve
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        ModuleManager mm = (ModuleManager) runtime.getServiceManager().lookup(
                ModuleManager.ROLE);
        for (Iterator i = runtime.getActions().iterator(); i.hasNext();)
        {
            String actionName = (String) i.next();
            //TODO not implemented yet
            Module actionModule = null;
            if (actionModule != null)
            {
                actionModule.execute(runtime, runtime.getPageContext());
            }
        }
        context.invokeNextValve(runtime);
    }
}
