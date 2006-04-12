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
package com.cyclopsgroup.waterview.velocity;

import java.util.Iterator;
import java.util.Properties;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;

import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.spi.DynaViewFactory;
import com.cyclopsgroup.waterview.spi.MessageView;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.View;
import com.cyclopsgroup.waterview.spi.Waterview;

/**
 * Velocity engine object
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class VelocityEngine
    extends AbstractLogEnabled
    implements Serviceable, DynaViewFactory, Initializable, Contextualizable
{
    /** Role name of this component */
    public static final String ROLE = VelocityEngine.class.getName();

    private org.apache.velocity.app.VelocityEngine engine;

    private Properties initProperties;

    private ModuleService moduleManager;

    /**
     * Overwrite or implement method contextualize()
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize( Context context )
        throws ContextException
    {
        initProperties = (Properties) context.get( Waterview.INIT_PROPERTIES );
    }

    /**
     * Overwrite or implement method createView()
     *
     * @see com.cyclopsgroup.waterview.spi.DynaViewFactory#createView(com.cyclopsgroup.waterview.Path)
     */
    public View createView( Path path )
        throws Exception
    {
        Template template = getTemplate( path.getPackage(), path.getPath() );
        if ( template != null )
        {
            return new VelocityView( template, path.getFullPath() );
        }
        return new MessageView( "Velocity template for " + path + " doesn't exist" );
    }

    /**
     * Get template with given full template path
     *
     * @param templatePath Full template path
     * @return Template object or null
     * @throws Exception Throw it out
     */
    public Template getTemplate( String templatePath )
        throws Exception
    {
        if ( templatePath.charAt( 0 ) == '/' )
        {
            templatePath = templatePath.substring( 1 );
        }
        if ( engine.templateExists( templatePath ) )
        {
            return engine.getTemplate( templatePath );
        }
        return null;
    }

    /**
     * Get velocity template with path and package
     *
     * @param templatePath Template path
     * @param packageName Package name
     * @return Velocity template object
     * @throws Exception Throw it out
     */
    public Template getTemplate( String packageName, String templatePath )
        throws Exception
    {
        String path = templatePath;
        if ( StringUtils.isNotEmpty( packageName ) )
        {
            path = packageName.replace( '.', '/' ) + templatePath;
        }
        return getTemplate( path );
    }

    /**
     * Overwrite or implement method initialize()
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        ExtendedProperties props = new ExtendedProperties();
        if ( initProperties != null )
        {
            for ( Iterator i = initProperties.keySet().iterator(); i.hasNext(); )
            {
                String name = (String) i.next();
                props.addProperty( name, initProperties.getProperty( name ) );
            }
        }
        props.load( getClass().getResourceAsStream( "basevelocity.properties" ) );

        engine = new org.apache.velocity.app.VelocityEngine();
        engine.setExtendedProperties( props );
        engine.init();
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        moduleManager = (ModuleService) serviceManager.lookup( ModuleService.ROLE );

        String pattern = ".+\\.vm";
        moduleManager.registerDynaViewFactory( pattern, this );
    }
}
