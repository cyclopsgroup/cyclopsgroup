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

import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Context;
import com.cyclopsgroup.waterview.Module;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.Layout;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
import com.cyclopsgroup.waterview.spi.View;

/**
 * Default implementation of module manager
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultModuleService
    extends AbstractLogEnabled
    implements Configurable, ModuleService, DynaViewFactory, Initializable
{

    private String defaultLayoutName = "waterview.layout.default";

    private String defaultPackageAlias = "waterview";

    private Hashtable dynaViewFactories = new Hashtable();

    private Hashtable layouts = new Hashtable();

    private Hashtable packageNames = new Hashtable();

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        String defaultPackage = conf.getChild( "default-package" ).getValue( null );
        if ( defaultPackage != null )
        {
            defaultPackageAlias = defaultPackage;
        }
        defaultLayoutName = conf.getChild( "default-layout" ).getValue( "waterview.layout.default" );
    }

    /**
     * @see com.cyclopsgroup.waterview.spi.ModuleService#createDefaultPage(com.cyclopsgroup.waterview.spi.RunDataSpi)
     */
    public Page createDefaultPage( RunDataSpi data )
    {
        Page page = new Page();
        data.setPageObject( page );

        return page;
    }

    /**
     * Overwrite or implement method createDynaView()
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleService#createDynaView(java.lang.String)
     */
    public View createDynaView( String viewPath )
        throws Exception
    {
        DynaViewFactory viewFactory = null;
        for ( Iterator i = dynaViewFactories.keySet().iterator(); i.hasNext(); )
        {
            String pattern = (String) i.next();
            if ( Pattern.matches( '^' + pattern + '$', viewPath ) )
            {
                viewFactory = (DynaViewFactory) dynaViewFactories.get( pattern );
                break;
            }
        }
        if ( viewFactory == null )
        {
            return View.DUMMY;
        }
        Path path = parsePath( viewPath );
        View view = viewFactory.createView( path );
        return view == null ? View.DUMMY : view;
    }

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(com.cyclopsgroup.waterview.Path)
     */
    public View createView( final Path path )
        throws Exception
    {
        return new View()
        {
            public void render( RunDataSpi data, Context viewContext )
                throws Exception
            {
                runModule( path, data, viewContext );
            }
        };
    }

    /**
     * @see com.cyclopsgroup.waterview.spi.ModuleService#getDefaultLayout()
     */
    public Layout getDefaultLayout()
    {
        return getLayout( defaultLayoutName );
    }

    /**
     * @see com.cyclopsgroup.waterview.spi.ModuleService#getLayout(java.lang.String)
     */
    public Layout getLayout( String name )
    {
        return (Layout) layouts.get( name );
    }

    /**
     * Overwrite or implement method getPackageAliases()
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleService#getPackageAliases()
     */
    public String[] getPackageAliases()
    {
        return (String[]) packageNames.keySet().toArray( ArrayUtils.EMPTY_STRING_ARRAY );
    }

    /**
     * Overwrite or implement method getPackageName()
     *
     * @see com.cyclopsgroup.waterview.spi.ModuleService#getPackageName(java.lang.String)
     */
    public String getPackageName( String aliasOrPackage )
    {
        if ( StringUtils.isEmpty( aliasOrPackage ) )
        {
            aliasOrPackage = defaultPackageAlias;
        }
        if ( packageNames.containsKey( aliasOrPackage ) )
        {
            return (String) packageNames.get( aliasOrPackage );
        }
        return aliasOrPackage;
    }

    /**
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        registerDynaViewFactory( ".+\\.jm", this );
    }

    /**
     * Overwrite or implement method parsePage()
     * @see com.cyclopsgroup.waterview.spi.ModuleService#parsePath(java.lang.String)
     */
    public Path parsePath( String modulePath )
    {
        String packageAlias = defaultPackageAlias;
        String packageName = (String) packageNames.get( packageAlias );
        if ( StringUtils.isEmpty( modulePath ) )
        {
            return new DefaultPath( packageName, packageAlias, "/Index.jelly" );
        }
        String path = modulePath;
        String[] parts = StringUtils.split( modulePath, '/' );
        for ( Iterator i = packageNames.keySet().iterator(); i.hasNext(); )
        {
            String alias = (String) i.next();
            String name = (String) packageNames.get( alias );
            if ( StringUtils.equals( parts[0], alias ) || StringUtils.equals( parts[0], name ) )
            {
                packageName = name;
                packageAlias = alias;
                path = modulePath.substring( parts[0].length() + 1 );
                break;
            }
        }
        return new DefaultPath( packageName, packageAlias, path );
    }

    /**
     * Overwrite or implement method registerDynaViewFactory()
     * @see com.cyclopsgroup.waterview.spi.ModuleService#registerDynaViewFactory(java.lang.String, com.cyclopsgroup.waterview.spi.DynaViewFactory)
     */
    public void registerDynaViewFactory( String pattern, DynaViewFactory viewFactory )
    {
        dynaViewFactories.put( pattern, viewFactory );
    }

    /**
     * @see com.cyclopsgroup.waterview.spi.ModuleService#registerLayout(java.lang.String, com.cyclopsgroup.waterview.spi.Layout)
     */
    public void registerLayout( String name, Layout layout )
    {
        layouts.put( name, layout );
    }

    /**
     * Overwrite or implement method registerPackageAlias()
     * @see com.cyclopsgroup.waterview.spi.ModuleService#registerPackage(java.lang.String, java.lang.String)
     */
    public void registerPackage( String alias, String packageName )
    {
        packageNames.put( alias, packageName );
    }

    private void runModule( Path modulePath, RunDataSpi data, Context context )
        throws Exception
    {
        String className = modulePath.getPackage() + modulePath.getPathWithoutExtension().replace( '/', '.' );
        Module module = null;
        try
        {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            module = (Module) cl.loadClass( className ).newInstance();
            if ( module instanceof Serviceable )
            {
                ( (Serviceable) module ).service( data.getServiceManager() );
            }
        }
        catch ( Exception ignored )
        {
            //do nothing
        }
        if ( module != null )
        {
            module.execute( data, context );
        }
    }

    /**
     * Overwrite or implement method runModule()
     * @see com.cyclopsgroup.waterview.spi.ModuleService#runModule(java.lang.String, com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.Context)
     */
    public void runModule( String modulePath, RunDataSpi data, Context context )
        throws Exception
    {
        Path path = parsePath( modulePath );
        runModule( path, data, context );
    }
}
