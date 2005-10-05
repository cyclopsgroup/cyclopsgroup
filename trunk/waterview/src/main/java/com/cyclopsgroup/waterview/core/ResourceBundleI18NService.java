/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.core;

import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.spi.I18NService;
import com.cyclopsgroup.waterview.spi.ModuleManager;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Implementation of I18N service
 */
public class ResourceBundleI18NService
    extends AbstractLogEnabled
    implements I18NService, Configurable, Initializable, Serviceable
{
    private Vector resourceKeys = new Vector();

    private ModuleManager modules;

    /**
     * Overwrite or implement method translate()
     *
     * @see com.cyclopsgroup.waterview.spi.I18NService#translate(java.lang.String, java.util.Locale)
     */
    public String translate( String key, Locale locale )
        throws Exception
    {
        for ( Iterator i = resourceKeys.iterator(); i.hasNext(); )
        {
            String resourceKey = (String) i.next();
            try
            {
                ResourceBundle rb = ResourceBundle.getBundle( resourceKey, locale );
                String s = rb.getString( key );
                if ( StringUtils.isNotEmpty( s ) )
                {
                    return s;
                }
                continue;
            }
            catch ( Exception e )
            {
                continue;
            }
        }
        return "%" + key;
    }

    /**
     * Overwrite or implement method initialize()
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        String[] packageAiases = modules.getPackageAliases();
        for ( int i = 0; i < packageAiases.length; i++ )
        {
            String packageAlias = packageAiases[i];
            String packageName = modules.getPackageName( packageAlias );
            resourceKeys.add( packageName + ".ResourceBundle" );
        }
    }

    /**
     * Overwrite or implement method configure()
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        resourceKeys.clear();
        Configuration[] bundles = conf.getChild( "bundles" ).getChildren( "bundle" );
        for ( int i = 0; i < bundles.length; i++ )
        {
            String bundleName = bundles[i].getValue();
            resourceKeys.add( bundleName );
        }
    }

    /**
     * Overwrite or implement method service()
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        modules = (ModuleManager) serviceManager.lookup( ModuleManager.ROLE );
    }
}
