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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.collections.map.ListOrderedMap;

import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.Waterview;

/**
 * Default waterview implementation
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class DefaultWaterview
    extends AbstractLogEnabled
    implements Waterview, Configurable, Initializable, Serviceable
{
    private transient Map pipelineRoles = ListOrderedMap.decorate( new HashMap() );

    private Map pipelines = ListOrderedMap.decorate( new Hashtable() );

    private ServiceManager serviceManager;

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure( Configuration conf )
        throws ConfigurationException
    {
        Configuration[] confs = conf.getChild( "pipelines" ).getChildren( "pipeline" );
        for ( int i = 0; i < confs.length; i++ )
        {
            Configuration c = confs[i];
            String pattern = c.getAttribute( "pattern" );
            String role = c.getAttribute( "role" );
            pipelineRoles.put( pattern, role );
        }
    }

    /**
     * Get registered pipeline
     *
     * @param pattern String
     * @return Pipeline object
     */
    public Pipeline getPipeline( String pattern )
    {
        return (Pipeline) pipelines.get( pattern );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.Waterview#handleRuntime(com.cyclopsgroup.waterview.RunData)
     */
    public void handleRuntime( RunData runtime )
        throws Exception
    {
        Pipeline pipeline = null;
        for ( Iterator i = pipelines.keySet().iterator(); i.hasNext(); )
        {
            String pattern = (String) i.next();
            if ( Pattern.matches( '^' + pattern + '$', runtime.getRequestPath() ) )
            {
                pipeline = getPipeline( pattern );
                break;
            }
        }
        if ( pipeline != null )
        {
            pipeline.handleRuntime( runtime );
        }
        else
        {
            //TODO do something here
        }
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.activity.Initializable#initialize()
     */
    public void initialize()
        throws Exception
    {
        for ( Iterator i = pipelineRoles.keySet().iterator(); i.hasNext(); )
        {
            String pattern = (String) i.next();

            String role = (String) pipelineRoles.get( pattern );
            Pipeline pipeline = (Pipeline) serviceManager.lookup( role );
            registerPipeline( pattern, pipeline );
        }
        pipelineRoles.clear();
    }

    /**
     * Register pipeline with given pattern
     *
     * @param pattern Path pattern
     * @param pipeline Pipeline object
     */
    public void registerPipeline( String pattern, Pipeline pipeline )
    {
        pipelines.put( pattern, pipeline );
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        this.serviceManager = serviceManager;
    }
}
