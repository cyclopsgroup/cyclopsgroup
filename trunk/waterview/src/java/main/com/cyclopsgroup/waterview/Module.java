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
 * UI module for each model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface Module
{
    /** Empty module instance */
    Module EMPTY_MODULE = new Module()
    {
        public void execute(RuntimeData pageRuntime, Context context)
                throws Exception
        {
            //do nothing
        }
    };

    /**
     * Execute module with given runtime and context
     *
     * @param pageRuntime Page runtime
     * @param context Given context
     * @throws Exception Throw it out to handler
     */
    void execute(RuntimeData pageRuntime, Context context) throws Exception;
}
