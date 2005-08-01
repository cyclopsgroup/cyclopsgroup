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
package com.cyclopsgroup.waterview;

import java.io.PrintWriter;
import java.util.HashMap;

import com.cyclopsgroup.clib.lang.DefaultContext;
import com.cyclopsgroup.clib.site.avalon.FakeServiceManager;

/**
 * Fake page runtime object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class FakePageRuntime extends AbstractPageRuntime implements RuntimeData
{
    /**
     * Constructor for class FakePageRuntime
     *
     * @param output Output
     */
    public FakePageRuntime(PrintWriter output)
    {
        setOutput(output);
        setApplicationBaseUrl("http://localhost:8080/waterview");
        setPageBaseUrl("http://localhost:8080/waterview/servlet/waterview");
        setRequestContext(new DefaultContext(new HashMap()));
        setRequestParameters(new MapRequestValueParser());
        setRequestPath("/index.html");
        setServiceManager(new FakeServiceManager());
        setSessionContext(new DefaultContext(new HashMap()));
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#getMimeType(java.lang.String)
     */
    public String getMimeType(String fileName)
    {
        return "text/html";
    }

    /**
     * @see com.cyclopsgroup.waterview.RuntimeData#getSessionId()
     */
    public String getSessionId()
    {
        return "";
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.RuntimeData#setOutputContentType(java.lang.String)
     */
    public void setOutputContentType(String contentType)
    {
    }
}
