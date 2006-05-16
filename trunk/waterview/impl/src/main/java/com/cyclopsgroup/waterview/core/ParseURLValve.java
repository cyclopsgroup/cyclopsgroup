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

import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.commons.lang.NotImplementedException;

import com.cyclopsgroup.waterview.Link;
import com.cyclopsgroup.waterview.Path;
import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.spi.ModuleService;
import com.cyclopsgroup.waterview.spi.PipelineContext;
import com.cyclopsgroup.waterview.spi.RunDataSpi;
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

    static class Part
    {
        private String instruction;

        private String path;

        private Part( String instruction, String path )
        {
            this.instruction = instruction;
            this.path = path;
        }

        public String getInstruction()
        {
            return instruction;
        }

        public String getPath()
        {
            return path;
        }

        public String toString()
        {
            return instruction + ':' + path;
        }
    }

    static class PartIterator
        implements Iterator
    {
        private static final Pattern INSTRUCTION_PATTERN = Pattern.compile( "\\/\\![a-zA-Z0-9]+\\!" );

        private boolean hasNext;

        private Matcher matcher;

        private String requestPath;

        PartIterator( String requestPath )
        {
            this.requestPath = requestPath;
            matcher = INSTRUCTION_PATTERN.matcher( requestPath );
            hasNext = matcher.find();
        }

        public boolean hasNext()
        {
            return hasNext;
        }

        public Object next()
        {
            if ( !hasNext )
            {
                throw new NoSuchElementException();
            }

            String instruction = requestPath.substring( matcher.start() + 2, matcher.end() - 1 );
            int pathStart = matcher.end();
            hasNext = matcher.find();
            int pathEnd = hasNext ? matcher.start() : requestPath.length();
            String path = requestPath.substring( pathStart, pathEnd );
            return new Part( instruction, path );
        }

        public void remove()
        {
            throw new NotImplementedException();
        }
    }

    private ModuleService moduleService;

    /**
     * Override or implement method of parent class or interface
     *
     * @see com.cyclopsgroup.waterview.spi.Valve#invoke(com.cyclopsgroup.waterview.RunData, com.cyclopsgroup.waterview.spi.PipelineContext)
     */
    public void invoke( RunDataSpi data, PipelineContext context )
        throws Exception
    {
        Iterator parts = new PartIterator( data.getRequestPath() );
        for ( Iterator i = parts; i.hasNext(); )
        {
            Part part = (Part) i.next();
            Path path = moduleService.parsePath( part.getPath() );
            data.setPath( part.instruction, path );
        }

        Path pagePath = data.getPath( Link.INSTRUCTION_DISPLAY );
        String page = pagePath == null ? "/waterview/Index.jelly" : pagePath.getFullPath();
        data.setPage( page );

        Locale locale = (Locale) data.getSessionContext().get( RunData.LOCALE_NAME );
        if ( locale != null )
        {
            data.setLocale( locale );
        }

        data.setPageObject( moduleService.createDefaultPage( data ) );
        context.invokeNextValve( data );
    }

    /**
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service( ServiceManager serviceManager )
        throws ServiceException
    {
        moduleService = (ModuleService) serviceManager.lookup( ModuleService.ROLE );
    }
}