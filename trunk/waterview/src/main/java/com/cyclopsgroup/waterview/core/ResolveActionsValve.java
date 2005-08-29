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
import java.util.List;

import org.apache.avalon.framework.logger.AbstractLogEnabled;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.ActionContext;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.ModuleManager;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to run modules
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ResolveActionsValve extends AbstractLogEnabled implements Valve
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke(RuntimeData data, PipelineContext context)
            throws Exception
    {
        List actions = data.getActions();
        if (actions == null || actions.isEmpty())
        {
            context.invokeNextValve(data);
            return;
        }

        DefaultActionContext actionContext = new DefaultActionContext(data);

        ModuleManager mm = (ModuleManager) data.getServiceManager().lookup(
                ModuleManager.ROLE);
        try
        {
            for (Iterator i = actions.iterator(); i.hasNext();)
            {
                String actionName = (String) i.next();
                Path path = mm.parsePath(actionName);
                String className = path.getPackage()
                        + path.getPathWithoutExtension().replace('/', '.');
                Action action = null;
                try
                {
                    action = (Action) Class.forName(className).newInstance();
                }
                catch (Exception ignored)
                {
                    //do nothing
                }
                if (action != null)
                {
                    action.execute(data, actionContext);
                }
            }
        }
        catch (Exception e)
        {
            actionContext.fail("Action error", e);
        }

        if (actionContext.isFailed())
        {
            data.getRequestContext().put(ActionContext.FAIL_MESSAGE,
                    actionContext.getFailMessage());
            data.getRequestContext().put(ActionContext.FAIL_CAUSE,
                    actionContext.getFailCause());
            data.setPage("/Error.jelly");
            context.invokeNextValve(data);
        }
        else
        {
            data.setRedirectUrl(actionContext.getTargetUrl());
        }
    }
}
