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
package com.cyclopsgroup.waterview.ui.action.sample;

import com.cyclopsgroup.waterview.Action;
import com.cyclopsgroup.waterview.UIRuntime;
import com.cyclopsgroup.waterview.ValueParser;

/**
 * Create report action
 * 
 * @author <a href="mailto:jiiaqi@yahoo.com">Jiaqi Guo </a>
 */
public class CreateReport extends Action
{
    /**
     * Override method execute in super class of CreateReport
     * 
     * @see com.cyclopsgroup.waterview.Action#execute(com.cyclopsgroup.waterview.UIRuntime)
     */
    protected void execute(UIRuntime runtime) throws Exception
    {
        ValueParser params = runtime.getRequestParameters();
        System.out.println(params);
    }
}