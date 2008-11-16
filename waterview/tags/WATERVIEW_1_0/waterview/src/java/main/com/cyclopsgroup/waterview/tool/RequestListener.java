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
package com.cyclopsgroup.waterview.tool;

import com.cyclopsgroup.waterview.UIRuntime;

/**
 * Listener to listen each request
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface RequestListener
{
    /**
     * Called after the request
     *
     * @param runtime
     * @throws Exception
     */
    void disposeForRequest(UIRuntime runtime) throws Exception;

    /**
     * Called before the request
     *
     * @param runtime
     * @throws Exception
     */
    void prepareForRequest(UIRuntime runtime) throws Exception;
}