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
package com.cyclopsgroup.waterview.spi;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Path;

/**
 * Module manager
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface ModuleService
{
    /** Role name of the component */
    String ROLE = ModuleService.class.getName();

    /**
     * Create default intial page
     *
     * @return Create new default page
     */
    Page createDefaultPage();

    /**
     * Create view dynamically
     *
     * @param viewPath String view path
     * @return View object
     * @throws Exception Throw it out
     */
    View createDynaView( String viewPath )
        throws Exception;

    /**
     * Get package aliases
     *
     * @return Package aliases
     */
    String[] getPackageAliases();

    /**
     * Get full package name
     *
     * @param aliasOrPackage Package alias or package name
     * @return Full package name
     */
    String getPackageName( String aliasOrPackage );

    /**
     * @param page
     * @return Path model
     */
    Path parsePath( String page );

    /**
     * @param pattern
     * @param viewFactory
     */
    void registerDynaViewFactory( String pattern, DynaViewFactory viewFactory );

    /**
     * @param alias
     * @param packageName
     */
    void registerPackage( String alias, String packageName );

    /**
     * Run module
     *
     * @param modulePath
     * @param data
     * @param context
     * @throws Exception
     */
    void runModule( String modulePath, RunDataSpi data, Context context )
        throws Exception;
}
