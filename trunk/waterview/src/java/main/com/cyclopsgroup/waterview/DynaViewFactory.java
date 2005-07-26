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

/**
 * Dynamic view factory
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface DynaViewFactory
{
    /** Name of this component */
    String NAME = DynaViewFactory.class.getName();

    /**
     * Dynamically create View with view path
     *
     * @param packageName package of ui module
     * @param viewPath Path of the view
     * @param runtime Current runtime
     * @return View object
     * @throws Exception Throw it out
     */
    View createView(String packageName, String viewPath, PageRuntime runtime)
            throws Exception;
}
