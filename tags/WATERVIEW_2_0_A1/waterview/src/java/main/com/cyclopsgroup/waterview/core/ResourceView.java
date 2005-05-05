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
package com.cyclopsgroup.waterview.core;

import java.net.URL;

import org.apache.commons.io.CopyUtils;

import com.cyclopsgroup.clib.lang.Context;
import com.cyclopsgroup.waterview.BaseModule;
import com.cyclopsgroup.waterview.PageRuntime;
import com.cyclopsgroup.waterview.View;

/**
 * Just display a given resource
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ResourceView extends BaseModule implements View
{
    private URL resource;

    /**
     * Constructor for class ResourceView
     *
     * @param resource Resource object
     */
    public ResourceView(URL resource)
    {
        this.resource = resource;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.View#render(com.cyclopsgroup.waterview.PageRuntime, com.cyclopsgroup.clib.lang.Context)
     */
    public void render(PageRuntime runtime, Context viewContext)
            throws Exception
    {
        CopyUtils.copy(resource.openStream(), runtime.getOutput());
    }
}
