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
 * Interface for ui tool
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface UITool
{
    /**
     * Dispose this tool after it's finished
     *
     * @param runtime Current runtime context
     * @throws Exception Throw it out
     */
    void dispose(UIRuntime runtime) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @return Tool name
     */
    String getName();

    /**
     * Initialize this tool with current runtime object
     *
     * @param runtime
     * @throws Exception
     */
    void initialize(UIRuntime runtime) throws Exception;

    /**
     * TODO Add javadoc for this method
     *
     * @param toolName Tool name
     */
    void setName(String toolName);
}