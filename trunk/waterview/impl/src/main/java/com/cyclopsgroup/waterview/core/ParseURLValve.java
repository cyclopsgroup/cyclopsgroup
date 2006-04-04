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
package com.cyclopsgroup.waterview.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.StringUtils;

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.RuntimeData;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.Page;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.Valve;

/**
 * Valve to prepare information contained by URL
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class ParseURLValve
    extends AbstractLogEnabled
    implements Valve, Serviceable
{

    private static HashSet instructors;

    private ModuleService moduleService;

    private static HashSet getInstructors()
    {
        if ( instructors == null )
        {
            instructors = new HashSet();
            instructors.add( Link.ACTION_INSTRUCTOR );
            instructors.add( Link.PAGE_INSTRUCTOR );
        }
        return instructors;
    }

    /**
     * @param requestPath
     * @return list of parts
     */
    static List parseRequestPath( String requestPath )
    {
        List ret = new ArrayList();
        String[] parts = StringUtils.split( requestPath, '/' );
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < parts.length; i++ )
        {
            String part = parts[i];
            if ( getInstructors().contains( part ) && sb.length() != 0 )
            {
                ret.add( sb.toString() );
                sb = new StringBuffer();
            }
            sb.append( '/' ).append( part );
        }
        ret.add( sb.toString() );
        return ret;
    }

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RuntimeData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RuntimeData data, PipelineContext context )
        throws Exception
    {
        List behaviors = parseRequestPath( data.getRequestPath() );
        for ( Iterator i = behaviors.iterator(); i.hasNext(); )
        {
            String behavior = (String) i.next();
            if ( behavior.startsWith( '/' + Link.ACTION_INSTRUCTOR ) )
            {
                String action = behavior.substring( Link.ACTION_INSTRUCTOR.length() + 1 );
                data.getActions().add( action );
            }
            else if ( behavior.startsWith( '/' + Link.PAGE_INSTRUCTOR ) )
            {
                String page = behavior.substring( Link.PAGE_INSTRUCTOR.length() + 1 );
                data.setPage( page );
            }
            else
            {
                data.setPage( behavior );
            }
        }

        Locale locale = (Locale) data.getSessionContext().get( RuntimeData.LOCALE_NAME );
        if ( locale != null )
        {
            data.setLocale( locale );
        }

        data.getRequestContext().put(Page.NAME, moduleService.createDefaultPage());
        context.invokeNextValve( data );
    }

    /**
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        moduleService = (ModuleService) serviceManager.lookup(ModuleService.ROLE);
    }
}