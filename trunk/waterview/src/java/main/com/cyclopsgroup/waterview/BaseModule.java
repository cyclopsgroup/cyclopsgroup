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

import com.cyclopsgroup.clib.lang.Context;

/**
 * Base modularized implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class BaseModule implements Module
{
    private transient Module module = EMPTY_MODULE;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Module#execute(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.clib.lang.Context)
     */
    public void execute(RuntimeData pageRuntime, Context context)
            throws Exception
    {
        if (module != null)
        {
            module.execute(pageRuntime, context);
        }
    }

    /**
     * Get attached module
     *
     * @return Attached module
     */
    protected Module getModule()
    {
        return module;
    }

    /**
     * Set attached module
     *
     * @param module Attached module
     */
    public void setModule(Module module)
    {
        this.module = module;
    }
}