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
package com.cyclopsgroup.waterview.core.valves;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.RequestValueParser;
import com.cyclopsgroup.waterview.Valve;

/**
 * Valve to prepare information contained by URL
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValve extends AbstractLogEnabled implements Valve
{
    private static final String ACTION_PREFIX = "a:";

    public static final char SEPARATOR = '|';

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Valve#invoke(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.waterview.PipelineContext)
     */
    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        Context ctx = runtime.getPageContext();
        ctx.put(PageRuntime.CONTEXT_RUNTIME_NAME, runtime);
        ctx.put(PageRuntime.CONTEXT_PAGE_CONTEXT_NAME, ctx);
        RequestValueParser params = runtime.getRequestParameters();
        ctx.put(PageRuntime.CONTEXT_PARAMS_NAME, params);
        ctx.put(PageRuntime.CONTEXT_APPLICATION_BASE_NAME, runtime
                .getApplicationBaseUrl());
        ctx.put(PageRuntime.CONTEXT_PAGE_BASE_NAME, runtime.getPageBaseUrl());
        String requestPath = runtime.getRequestPath();
        if (requestPath.startsWith("/"))
        {
            requestPath = requestPath.substring(1);
        }
        String[] parts = null;
        if (requestPath.indexOf(SEPARATOR) == -1)
        {
            parts = new String[] { requestPath };
        }
        else
        {
            parts = StringUtils.split(requestPath, SEPARATOR);
        }
        String pagePath = null;
        for (int i = 0; i < parts.length; i++)
        {
            String part = parts[i];
            if (part.startsWith(ACTION_PREFIX))
            {
                String action = part.substring(ACTION_PREFIX.length());
                runtime.getActions().add(action);
            }
            else
            {
                pagePath = part;
            }
        }
        if (StringUtils.isNotEmpty(pagePath))
        {
            runtime.setPage(pagePath);
        }
        context.invokeNextValve(runtime);
    }
}