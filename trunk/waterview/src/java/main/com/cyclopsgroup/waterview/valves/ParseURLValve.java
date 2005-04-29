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

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to prepare information contained by URL
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValve extends AbstractLogEnabled implements Valve
{
    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        Context ctx = runtime.getPageContext();
        ctx.put("runtime", runtime);
        ctx.put("context", ctx);
        ctx.put("params", runtime.getRequestParameters());
        ctx.put("applicationBase", runtime.getApplicationBaseUrl());
        ctx.put("pageBase", runtime.getPageBaseUrl());
        String path = runtime.getRequestPath();
        if (path.indexOf('|') != -1)
        {
            String[] parts = StringUtils.split(path, '|');
            for (int i = 0; i < parts.length; i++)
            {
                String part = parts[i];
                if (i == parts.length - 1)
                {
                    path = part;
                }
                else
                {
                    runtime.getActions().add(part);
                }
            }
        }
        runtime.setPage(path);
        ctx.put("page", path);
        context.invokeNextValve(runtime);
    }
}