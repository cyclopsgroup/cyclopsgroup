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
package com.cyclopsgroup.waterview;

/**
 * A runnable module
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public abstract class Action implements UIModule
{

    /**
     * Process given runtime object
     * 
     * @param runtime Runtime data object
     * @throws Exception Throw it to container
     */
    protected abstract void execute(UIRuntime runtime) throws Exception;

    /**
     * Override method process in super class of Action
     * 
     * @see com.cyclopsgroup.waterview.UIModule#process(com.cyclopsgroup.waterview.UIRuntime)
     */
    public void process(UIRuntime runtime) throws Exception
    {
        execute(runtime);
    }
}