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
package com.cyclopsgroup.tornado.components.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

import com.cyclopsgroup.tornado.core.security.PageAsset;
import com.cyclopsgroup.tornado.core.security.RuntimeUser;
import com.cyclopsgroup.tornado.core.security.SecurityManager;
import com.cyclopsgroup.waterview.AppendablePageRedirector;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.PipelineContext;
import com.cyclopsgroup.waterview.Valve;

public class CheckSecurityValve implements Valve, Configurable
{
    private static final String DEFAULT_LOGIN_PAGE = "pub/Login.jelly";

    private String loginPage = DEFAULT_LOGIN_PAGE;

    /**
     * Overwrite or implement method configure()
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration conf) throws ConfigurationException
    {
        loginPage = conf.getChild("login-page").getValue(DEFAULT_LOGIN_PAGE);
    }

    public void invoke(PageRuntime runtime, PipelineContext context)
            throws Exception
    {
        SecurityManager securityManager = (SecurityManager) runtime
                .getServiceManager().lookup(SecurityManager.ROLE);
        RuntimeUser runtimeUser = securityManager.getRuntimeUser(runtime
                .getSessionId());
        boolean authorized = runtimeUser.getACL().isAuthorized(
                new PageAsset(runtime.getPage()));
        if (authorized)
        {
            context.invokeNextValve(runtime);
        }
        else
        {
            AppendablePageRedirector redirector = new AppendablePageRedirector(
                    loginPage);
            //TODO get current requested URL
            HttpServletRequest request = (HttpServletRequest) runtime
                    .getPageContext().get("request");
            String url = request.getRequestURL().toString();
            redirector.addQueryData("redirect_to", url);
            runtime.setRedirector(redirector);
        }
    }
}
