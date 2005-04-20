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
package com.cyclopsgroup.waterview.ui.body;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.cyclopsgroup.cyclib.Context;
import com.cyclopsgroup.waterview.Page;
import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Error page
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Error implements Page
{

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Page#prepare(com.cyclopsgroup.waterview.UIRuntime, com.cyclopsgroup.cyclib.Context)
     */
    public void prepare(UIRuntime runtime, Context outputContext)
            throws Exception
    {
        Exception e = (Exception) outputContext.get("exception");
        if (e == null)
        {
            return;
        }
        String stackTrace = ExceptionUtils.getStackTrace(e);
        outputContext.put("stackTrace", stackTrace);
    }
}